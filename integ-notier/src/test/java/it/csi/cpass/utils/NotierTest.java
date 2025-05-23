/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - NotiER
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.Hashtable;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.commons.codec.Charsets;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContexts;

import it.csi.cpass.cpassbe.lib.external.impl.notier.PeppolContentBody;
import it.csi.cpass.cpassbe.lib.external.itf.ConfigurationParam;
import it.csi.cpass.cpassbe.lib.utils.NotiERConfigurationParams;

public class NotierTest {

	private static final String SRC_TEST_RESOURCES = "C:/Eclipse/workspace/cpass/cpassbe/integ-notier/src/test/resources";
	private static final String KEYSTORE_FILE = "/ACSI.p12";

	//protected final LogUtil log = new LogUtil(getClass());
	//final Log log = LogFactory.getLog(this.getClass());

	public static void main(String[] args) {
		try {
			Map<String, String> params = new Hashtable<String, String>();

			params.put(NotiERConfigurationParams.PROTOCOL_NOTIER.getParamName(), "https");
			params.put(NotiERConfigurationParams.HOST_NOTIER.getParamName(), "test-notier.regione.emilia-romagna.it");
			params.put(NotiERConfigurationParams.PORTA_NOTIER.getParamName(), "8443");

			params.put(NotiERConfigurationParams.KEYSTORE_PATH_NOTIER.getParamName(), SRC_TEST_RESOURCES + KEYSTORE_FILE);
			params.put(NotiERConfigurationParams.KEYSTORE_PASS_NOTIER.getParamName(), "ekrG$x+e3^");
			params.put(NotiERConfigurationParams.KEY_PASS_NOTIER.getParamName(), "ekrG$x+e3^");

			new NotierTest().go(params);
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	private KeyStore readStore(Map<String, String> params) throws Exception {
		//final String methodName = "readStore";
		InputStream keyStoreStream = null;
		try {
			String keystorePath = getParameter(params, NotiERConfigurationParams.KEYSTORE_PATH_NOTIER);
			String keystorePass = getParameter(params, NotiERConfigurationParams.KEYSTORE_PASS_NOTIER);

			if (keystorePath == null || keystorePath.trim().equals("")) {
				//System.out.println(methodName +"keystorePath empty");
			} else {
				File fileKeyStore = new File(keystorePath);
				if (!fileKeyStore.exists()) {
					//System.out.println(methodName + "keystorePath: " + keystorePath + " non esiste");
				} else {
					keyStoreStream = new FileInputStream(fileKeyStore);
					// keyStoreStream = this.getClass().getResourceAsStream(KEYSTORE_FILE);

					// KeyStore keyStore = KeyStore.getInstance("JKS"); // or "PKCS12"
					KeyStore keyStore = KeyStore.getInstance("PKCS12");
					keyStore.load(keyStoreStream, keystorePass.toCharArray());
					return keyStore;
				}
			}

		} finally {
			if (keyStoreStream != null) {
				keyStoreStream.close();
			}
		}
		return null;
	}

	private void go(Map<String, String> params) throws KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, Exception {
		//final String methodName = "go - ";
		//System.out.println(methodName + new Date());

		String xmlMetadati = readFile(SRC_TEST_RESOURCES + "/xmlMetadati.xml");
		String xmlDocumento = readFile(SRC_TEST_RESOURCES + "/xmlDocumento.xml");
//		String xmlDocumento = readFile(SRC_TEST_RESOURCES + "/documento.xml");
		
		//System.out.println(methodName + "xmlMetadati: " + xmlMetadati);
		//System.out.println(methodName + "xmlDocumento: " + xmlDocumento);

		// set timeout
		int timeout = 30000; // 30s
		RequestConfig.Builder requestBuilder = RequestConfig.custom();
		requestBuilder = requestBuilder.setConnectTimeout(timeout);
		requestBuilder = requestBuilder.setConnectionRequestTimeout(timeout);

		HttpClientBuilder builder = HttpClientBuilder.create();
		builder.setDefaultRequestConfig(requestBuilder.build());

		// use null as second param if you don't have a separate key password
		KeyStore keyStore = readStore(params);
		if (keyStore != null) {
			String keyPass = getParameter(params, NotiERConfigurationParams.KEY_PASS_NOTIER);
			SSLContext sslContext = SSLContexts.custom().loadKeyMaterial(keyStore, keyPass.toCharArray()).build();
			// .loadKeyMaterial(new File(KEYSTOREPATH), KEYSTOREPASS.toCharArray(), KEYSTOREPASS.toCharArray())
			builder.setSSLContext(sslContext);

			SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext, new String[] { "TLSv1.2" }, null,
					SSLConnectionSocketFactory.getDefaultHostnameVerifier());
			builder.setSSLSocketFactory(sslConnectionSocketFactory);
		}

		builder.setRedirectStrategy(new DefaultRedirectStrategy());

		CloseableHttpClient httpclient = builder.build();
		try {
			String protocolNoTIER = getParameter(params, NotiERConfigurationParams.PROTOCOL_NOTIER);
			String hostNoTIER = getParameter(params, NotiERConfigurationParams.HOST_NOTIER);
			String portaNoTIER = getParameter(params, NotiERConfigurationParams.PORTA_NOTIER);
			String url = protocolNoTIER + "://" + hostNoTIER + ":" + portaNoTIER + "/notier/rest/v1.0/documenti/invio";
			//System.out.println(methodName + "url: " + url);
			
			HttpPost httpPost = new HttpPost(url);
			httpPost.addHeader("Content-Type", "multipart/form-data");

			byte[] byteXmlMetadati = xmlMetadati.getBytes(Charsets.UTF_8);
			byte[] byteXmlDocumento = xmlDocumento.getBytes(Charsets.UTF_8);

			HttpEntity httpEntity = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
					
					// .addTextBody("metadati", xmlMetadati, ContentType.APPLICATION_XML)
					// .addTextBody("documento", xmlDocumento, ContentType.APPLICATION_XML)

					.addPart("metadati", new PeppolContentBody(byteXmlMetadati, "metadati.xml"))
					.addPart("documento", new PeppolContentBody(byteXmlDocumento, "documento.xml")).build();

			httpPost.setEntity(httpEntity);
			
			// fix Content-Type con boundary - multipart/form-data; boundary=--------------------------062059736886472513323683
			// altrimenti otteniamo pagina di errore
			httpPost.setHeader(httpEntity.getContentType());
			//System.out.println(methodName + "-------------getRequestLine-----------------");
			//System.out.println(methodName + "executing request " + httpPost.getRequestLine());
			//System.out.println(methodName + "----------fine getRequestLine----------------");

			CloseableHttpResponse httpResponse = httpclient.execute(httpPost);
			try {
				//System.out.println(methodName + "----------------------------------------");
				//System.out.println(methodName + "statusLine: " + httpResponse.getStatusLine());
				HttpEntity resEntity = httpResponse.getEntity();
				if (resEntity != null) {
					//System.out.println(methodName + "Response content length: " + resEntity.getContentLength());
				}
				//String xmlResponse = EntityUtils.toString(resEntity, Charsets.UTF_8);
				//System.out.println(methodName + "xmlResponse: " + xmlResponse);

			} finally {
				httpResponse.close();
			}
		} finally {
			httpclient.close();
		}
	}

	protected String getParameter(Map<String, String> params, ConfigurationParam param) {
		return params.get(param.getParamName());
	}

	private String readFile(String filename) throws IOException {
		File file = new File(filename);
		FileInputStream fis = new FileInputStream(file);
		byte[] b = new byte[(int) file.length()];
		fis.read(b);
		fis.close();
		fis = null;
		return new String(b);
	}

}
