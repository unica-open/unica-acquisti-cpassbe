/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.util.report;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * Invoker for the report servlet
 */
public class ReportServletInvoker {

	/**
	 * Invoca la servlet di birt
	 *
	 * @param endpoint the endpoint
	 * @param reportName the report name
	 * @param formatFile the file format
	 * @param formData the data
	 * @return the received bytes
	 * @throws ClientProtocolException in case of an exception from the HTTP client
	 * @throws IOException in case of an IO exception
	 */
	public byte[] invoke(String endpoint, String reportName, String formatFile, List<NameValuePair> params) throws ClientProtocolException, IOException {
		/*CloseableHttpClient httpclient = HttpClients.createDefault();

		String urlpost = endpoint + "?__report=" + reportName + "&__format=" + formatFile + "&__asattachment=true";
		HttpPost httpPost = new HttpPost(urlpost);
		httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		try (CloseableHttpResponse httpResponse = httpclient.execute(httpPost)) {
			return IOUtils.toByteArray(httpResponse.getEntity().getContent());
		}
		}*/

		final int timeout = 120;
		final RequestConfig config = RequestConfig.custom()
				.setConnectTimeout(timeout * 1000)
				.setConnectionRequestTimeout(timeout * 1000)
				.setSocketTimeout(timeout * 1000).build();
		try(CloseableHttpClient httpclient = HttpClientBuilder.create().setDefaultRequestConfig(config).build()){
			//try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
			final String urlpost = endpoint + "?__report=" + reportName + "&__format=" + formatFile + "&__asattachment=true";
			final HttpPost httpPost = new HttpPost(urlpost);
			httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			try (CloseableHttpResponse httpResponse = httpclient.execute(httpPost)) {
				final byte[] responseBody = IOUtils.toByteArray(httpResponse.getEntity().getContent());
				return responseBody;
			} catch (final Exception e) {
				//System.out.println("invoke " + e);
				return null;
			}finally {
				httpclient.close();
			}
		}
	}
	/**
	 *
	 * @param endpoint
	 * @param reportName
	 * @param formatFile
	 * @param params
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	//public byte[] invoke(String endpoint, String reportName, String formatFile, String objParams) throws ClientProtocolException, IOException {
	public byte[] invoke(String endpoint, String objParams,String fileNameWithoutExtension) throws ClientProtocolException, IOException {
		/*CloseableHttpClient httpclient = HttpClients.createDefault();

		String urlpost = endpoint ;
		HttpPost httpPost = new HttpPost(urlpost);
		httpPost.setEntity(new StringEntity(objParams));
		try (CloseableHttpResponse httpResponse = httpclient.execute(httpPost)) {
			return IOUtils.toByteArray(httpResponse.getEntity().getContent());
		}*/
		//CloseableHttpClient httpclient = HttpClients.createDefault();
		//CloseableHttpClient httpclient = HttpClients.custom().setConnectionTimeToLive(10, TimeUnit.SECONDS).build();

		final int timeout = 120;
		final RequestConfig config = RequestConfig.custom()
				.setConnectTimeout(timeout * 1000)
				.setConnectionRequestTimeout(timeout * 1000)
				.setSocketTimeout(timeout * 1000).build();
		final CloseableHttpClient httpclient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();






		try (httpclient) {
			final String urlpost = endpoint ;
			//System.out.println("endpoint " + endpoint);
			final HttpPost httpPost = new HttpPost(urlpost);
			httpPost.setEntity(new StringEntity(objParams));
			//httpPost.set
			//System.out.println("objParams "+ objParams );

			final CloseableHttpResponse httpResponse = httpclient.execute(httpPost);
			//System.out.println("fileNameWithoutExtension "+ fileNameWithoutExtension );
			return IOUtils.toByteArray(httpResponse.getEntity().getContent());
		}catch (final Exception e) {
			//System.out.println("invoke " + e);
			return null;
		}
	}
}
