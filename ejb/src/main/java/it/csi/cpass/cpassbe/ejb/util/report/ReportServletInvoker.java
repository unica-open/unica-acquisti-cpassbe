/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
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
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

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
		CloseableHttpClient httpclient = HttpClients.createDefault();

		String urlpost = endpoint + "?__report=" + reportName + "&__format=" + formatFile + "&__asattachment=true";
		HttpPost httpPost = new HttpPost(urlpost);
		httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		try (CloseableHttpResponse httpResponse = httpclient.execute(httpPost)) {
			httpResponse.getFirstHeader("Content-Disposition");
			httpResponse.getStatusLine().getStatusCode();
			return IOUtils.toByteArray(httpResponse.getEntity().getContent());
		}
	}
}
