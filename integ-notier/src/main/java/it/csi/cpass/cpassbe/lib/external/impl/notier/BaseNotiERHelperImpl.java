/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - NotiER
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.external.impl.notier;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.commons.codec.Charsets;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import it.csi.cpass.cpassbe.lib.dto.ord.nso.DocumentoRichiesto;
import it.csi.cpass.cpassbe.lib.dto.ord.nso.EsitoRecuperoListaDocumenti;
import it.csi.cpass.cpassbe.lib.external.dto.Errore;
import it.csi.cpass.cpassbe.lib.external.impl.BaseHelperImpl;
import it.csi.cpass.cpassbe.lib.external.res.ExternalServiceResponseWrapper;
import it.csi.cpass.cpassbe.lib.util.serialization.JAXBUtility;
import it.csi.cpass.cpassbe.lib.utils.NotiERConfigurationParams;
import it.rer.intercenter.notier.services._1_0.esito.response.DocumentoType;
import it.rer.intercenter.notier.services._1_0.esito.response.EsitoRichiesta;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.EndpointIDType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.IDType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.LineIDType;
import un.unece.uncefact.data.specification.corecomponenttypeschemamodule._2.CodeType;
import un.unece.uncefact.data.specification.corecomponenttypeschemamodule._2.QuantityType;
import un.unece.uncefact.data.specification.corecomponenttypeschemamodule._2.TextType;

/**
 * Base helper for NotiER implementations
 */
public abstract class BaseNotiERHelperImpl extends BaseHelperImpl {
	
	protected String protocolNoTIER;
	protected String hostNoTIER;
	protected String portaNoTIER;
	protected String hostnameProxy;
	protected String strPortProxy;


	/**
	 * Checks the baseparameters for NotiER integration
	 * 
	 * @param params the params
	 */
	protected void checkBaseParameters(Map<String, String> params) {
		
		checkParameters(params, NotiERConfigurationParams.HOST_NOTIER, NotiERConfigurationParams.PORTA_NOTIER);
		checkParameters(params, NotiERConfigurationParams.PORTA_NOTIER, NotiERConfigurationParams.PORTA_NOTIER);		
		checkParameters(params, NotiERConfigurationParams.NSO_CUSTOMIZATION_ID, NotiERConfigurationParams.NSO_CUSTOMIZATION_ID);
		checkParameters(params, NotiERConfigurationParams.NSO_PROFILE_ID, NotiERConfigurationParams.NSO_PROFILE_ID);
		checkParameters(params, NotiERConfigurationParams.PROTOCOL_NOTIER, NotiERConfigurationParams.PROTOCOL_NOTIER);
		checkParameters(params, NotiERConfigurationParams.KEYSTORE_PATH_NOTIER, NotiERConfigurationParams.KEYSTORE_PATH_NOTIER);
		checkParameters(params, NotiERConfigurationParams.KEYSTORE_PASS_NOTIER, NotiERConfigurationParams.KEYSTORE_PASS_NOTIER);
		checkParameters(params, NotiERConfigurationParams.KEY_PASS_NOTIER, NotiERConfigurationParams.KEY_PASS_NOTIER);
		checkParameters(params, NotiERConfigurationParams.NSO_UNICO_DESTINATARIO, NotiERConfigurationParams.NSO_UNICO_DESTINATARIO);
		checkParameters(params, NotiERConfigurationParams.PROXY_HOSTNAME_NOTIER, NotiERConfigurationParams.PROXY_HOSTNAME_NOTIER);
		checkParameters(params, NotiERConfigurationParams.PROXY_PORT_NOTIER, NotiERConfigurationParams.PROXY_PORT_NOTIER);
		
	}
	/**
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	protected KeyStore readStore(Map<String, String> params) throws Exception {
		final String methodName = "readStore";
		InputStream keyStoreStream = null;
		try {
			String keystorePath = getParameter(params, NotiERConfigurationParams.KEYSTORE_PATH_NOTIER);
			String keystorePass = getParameter(params, NotiERConfigurationParams.KEYSTORE_PASS_NOTIER);

			if (keystorePath == null || keystorePath.trim().equals("")) {
				log.error(methodName ," controllare la mapparura delle property KEYSTORE_PATH_NOTIER KEYSTORE_PASS_NOTIER su db e la presenza del certificato");
			} else {
				File fileKeyStore = new File(keystorePath);
				if (!fileKeyStore.exists()) {
					log.error(methodName , "keystorePath: " + keystorePath + " non esiste");
				} else {
					keyStoreStream = new FileInputStream(fileKeyStore);
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

	/**
	 * @param params
	 * @param builder
	 * @param keyStore
	 * @return
	 * @throws Exception 
	 */
	protected CloseableHttpClient initHttpClient(Map<String, String> params) throws Exception {		
		//String methodName = "initHttpClient";
		checkBaseParameters(params);
		// set timeout
		int timeout = 60000; // 60s
		RequestConfig.Builder requestBuilder = RequestConfig.custom();
		requestBuilder = requestBuilder.setConnectTimeout(timeout);
		requestBuilder = requestBuilder.setConnectionRequestTimeout(timeout);
		HttpClientBuilder builder = HttpClientBuilder.create();
		builder.setDefaultRequestConfig(requestBuilder.build());
		hostnameProxy = getParameter(params, NotiERConfigurationParams.PROXY_HOSTNAME_NOTIER);
		strPortProxy = getParameter(params, NotiERConfigurationParams.PROXY_PORT_NOTIER);
		protocolNoTIER = getParameter(params, NotiERConfigurationParams.PROTOCOL_NOTIER);
		hostNoTIER = getParameter(params, NotiERConfigurationParams.HOST_NOTIER);
		portaNoTIER = getParameter(params, NotiERConfigurationParams.PORTA_NOTIER);
		String keyPass = getParameter(params, NotiERConfigurationParams.KEY_PASS_NOTIER);	
		KeyStore keyStore = null;
		keyStore = readStore(params);
		SSLContext sslContext = null;
		sslContext = SSLContexts.custom().loadKeyMaterial(keyStore, keyPass.toCharArray()).build();
		builder.setSSLContext(sslContext);
		SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext, new String[] { "TLSv1.2" }, null,SSLConnectionSocketFactory.getDefaultHostnameVerifier());
		builder.setSSLSocketFactory(sslConnectionSocketFactory);
		builder.setRedirectStrategy(new DefaultRedirectStrategy());
		builder = setProxy(builder, hostnameProxy, strPortProxy);
		CloseableHttpClient httpclient = builder.build();	
		return httpclient;
	}
	
	private HttpClientBuilder setProxy(HttpClientBuilder builder, String hostnameProxy,String strPortProxy) {
		String methodName="setProxy";
		log.info(methodName, "hostnameProxy " + hostnameProxy);
		log.info(methodName, "strPortProxy " + strPortProxy);
		if (!StringUtils.isEmpty(hostnameProxy) && !StringUtils.isEmpty(strPortProxy)) {
			int portProxy = Integer.parseInt(strPortProxy);
			HttpHost proxy = new HttpHost(hostnameProxy,portProxy);
			builder.setProxy(proxy);
			log.debug(methodName, "proxy settato ");
		}else {
			log.debug(methodName, "proxy NON settato ");
		}
		return builder;
	}

	/**
	 * 
	 * @param params
	 * @param urn
	 * @return String
	 */
	protected String recuperoDocumentoXml(Map<String, String> params, String urn) {
		String methodName = "recuperoDocumentoXml";
		String xmlResponse = null;
		try {
			CloseableHttpClient httpclient = initHttpClient(params);
			String url = protocolNoTIER + "://" + hostNoTIER + ":" + portaNoTIER + "/notier/rest/v1.0/documenti/recupero/"+urn;
			log.debug(methodName,  "url: " + url);			
			HttpGet httpGet = new HttpGet(url);
			CloseableHttpResponse httpResponse = httpclient.execute(httpGet);
			HttpEntity resEntity = httpResponse.getEntity();
			xmlResponse = EntityUtils.toString(resEntity, Charsets.UTF_8);
			log.debug(methodName, "xmlResponse: " + xmlResponse);
			return xmlResponse;
		}catch (Exception e) {
			log.error(methodName, e);
		}
		return xmlResponse;
	}
	
	public ExternalServiceResponseWrapper<EsitoRecuperoListaDocumenti> recuperoListaDocumenti(Map<String, String> params, String idnotier, String parametri )   {
		final String methodName = "recuperoListaDocumenti";
		ExternalServiceResponseWrapper<EsitoRecuperoListaDocumenti> response = new ExternalServiceResponseWrapper<>();

		EsitoRecuperoListaDocumenti ris = new EsitoRecuperoListaDocumenti();
		List<DocumentoRichiesto> listaDocumentoRicevuto = new ArrayList<DocumentoRichiesto>();

		try {
			CloseableHttpClient httpclient = initHttpClient(params);
			String url = protocolNoTIER + "://" + hostNoTIER + ":" + portaNoTIER + "/notier/rest/v1.0/documenti/recupero?idnotier="+idnotier+parametri;
			log.info(methodName,  "url: " + url);
			HttpGet httpGet = new HttpGet(url);
			CloseableHttpResponse httpResponse = httpclient.execute(httpGet);			
			log.info(methodName, "----------------------------------------");
			log.info(methodName, "statusLine: " + httpResponse.getStatusLine());
			HttpEntity resEntity = httpResponse.getEntity();
			String xmlResponse = EntityUtils.toString(resEntity, Charsets.UTF_8);
			log.debug(methodName, "xmlResponse: " + xmlResponse);		
			EsitoRichiesta esitoRichiesta = JAXBUtility.unmarshall(xmlResponse, EsitoRichiesta.class);
			// N.B. non farsi ingannare esitoRichiesta.getListaDocumenti().getDocumenti().getDocumento()
			// restituisce una lista di DocumentoType e non un solo DocumentoType come sarebbe lecito pensare dal nome singolare
			// per ogni urn restituito va chiamato il secondo servizio Notier
			//log.info(methodName, "comparazione " + (esitoRichiesta.getListaDocumenti().getTotaleDocumentiScaricabili().compareTo(BigInteger.ZERO) ));
			if(esitoRichiesta.getListaDocumenti() != null && esitoRichiesta.getListaDocumenti().getTotaleDocumentiScaricabili().compareTo(BigInteger.ZERO) == 1) {
				for(DocumentoType doc : esitoRichiesta.getListaDocumenti().getDocumenti().getDocumento()) {
					//listaUrn.add(doc.getUrn());
					DocumentoRichiesto documentoRicevuto = new DocumentoRichiesto();
					documentoRicevuto.setUrn(doc.getUrn());
					documentoRicevuto.setUrnCollegato(doc.getUrnCollegato());
					listaDocumentoRicevuto.add(documentoRicevuto);
				}
			}
			if(esitoRichiesta.getEsito() != null && esitoRichiesta.getEsito().getDescrizioneErrore()!= null ) {
				List<Errore> errors = new ArrayList<Errore>();
				Errore errore  = new Errore ();
				errore.setCodice(esitoRichiesta.getEsito().getCodiceErrore().toString());
				errore.setDescrizione(esitoRichiesta.getEsito().getDescrizioneErrore());
				errors.add(errore);
				response.setErrori(errors);
			}
			response.setSuccess(true);				
			ris.setListaDocumentoRicevuto(listaDocumentoRicevuto);
			response.setResponse(ris);		
		}catch (Exception e) {
			response.setSuccess(false);
			ris.setListaDocumentoRicevuto(listaDocumentoRicevuto);
			log.error(methodName, e);
		}
		return response;
	}
	
	protected String replaceNull(CodeType nt) {
		return nt == null || nt.getValue()==null ? "" :  nt.getValue();
	}
	
	protected String replaceNull(TextType nt) {
		return nt == null || nt.getValue()==null ? "" :  nt.getValue();
	}

	protected String replaceNull(IDType nt) {
		return nt == null || nt.getValue()==null ? "" :  nt.getValue();
	}

	protected String replaceNull(EndpointIDType nt) {
		return nt == null || nt.getValue()==null ? "" :  nt.getValue();
	}

	protected String replaceNull(LineIDType nt) {
		return nt == null || nt.getValue()==null ? "" :  nt.getValue();
	}

	protected BigDecimal replaceNull(QuantityType nt) {
		return nt == null || nt.getValue()==null ? BigDecimal.ZERO :  nt.getValue();
	}
}
