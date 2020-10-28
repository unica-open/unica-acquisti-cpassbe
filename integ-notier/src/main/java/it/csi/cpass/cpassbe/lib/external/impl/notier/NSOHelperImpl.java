/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - NotiER
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.external.impl.notier;

import java.util.Map;

import org.apache.commons.codec.Charsets;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.EsitoInvioDocumento;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.external.NSOHelper;
import it.csi.cpass.cpassbe.lib.external.NSOListener;
import it.csi.cpass.cpassbe.lib.external.dto.RigheOrdineConTotali;
import it.csi.cpass.cpassbe.lib.external.res.ExternalServiceResponseWrapper;
import it.csi.cpass.cpassbe.lib.util.serialization.JAXBUtility;
import it.csi.cpass.cpassbe.lib.utils.NotiERConfigurationParams;
import it.rer.intercenter.notier.services._1_0.esito.response.EsitoRichiesta;

/**
 * Example POJO helper impl
 */
public class NSOHelperImpl extends BaseNotiERHelperImpl implements NSOHelper {

	public enum TIPO_ORDINE {
		INIZIALE, SOSTITUTIVO, REVOCA
	};

	@Override
	public ExternalServiceResponseWrapper<EsitoInvioDocumento> invioDocumentoNSO(Map<String, String> params, TestataOrdine testataOrdine,
			Destinatario destinatarioOrdine, RigheOrdineConTotali righeOrdineConTotali, NSOListener nsoListener) {
		final String methodName = "invioDocumento";

		ExternalServiceResponseWrapper<EsitoInvioDocumento> response = new ExternalServiceResponseWrapper<>();
		EsitoInvioDocumento esitoInvioDocumento = new EsitoInvioDocumento();
		response.setResponse(esitoInvioDocumento);

		try {
			checkBaseParameters(params);
			String hostNoTIER = getParameter(params, NotiERConfigurationParams.HOST_NOTIER);
			String portaNoTIER = getParameter(params, NotiERConfigurationParams.PORTA_NOTIER);
//			String url = "https://" + hostNoTIER + ":" + portaNoTIER + "/notier/rest/v1.0/documenti/invio";
			String url = "http://localhost:7080/testNSO/debug/" + hostNoTIER + "-" + portaNoTIER; // per test locale

			TIPO_ORDINE tipoOrdine = TIPO_ORDINE.INIZIALE;

			String xmlMetadati = PeppolMetadati.getXmlMetadati(testataOrdine, destinatarioOrdine);
			log.info(methodName, "xmlMetadati: " + xmlMetadati);

			String xmlDocumento = PeppolDocumento.getXmlDocumento(tipoOrdine, testataOrdine, destinatarioOrdine, righeOrdineConTotali,
					nsoListener.getMaxProgressivoInvio(), params);
			log.info(methodName, "xmlDocumento: " + xmlDocumento);

			// set timeout
			int timeout = 30000; // 30s
			RequestConfig.Builder requestBuilder = RequestConfig.custom();
			requestBuilder = requestBuilder.setConnectTimeout(timeout);
			requestBuilder = requestBuilder.setConnectionRequestTimeout(timeout);

			HttpClientBuilder builder = HttpClientBuilder.create();
			builder.setDefaultRequestConfig(requestBuilder.build());
			// builder.setSSLSocketFactory(SSLUtil.getInsecureSSLConnectionSocketFactory());
			builder.setRedirectStrategy(new DefaultRedirectStrategy());

			CloseableHttpClient httpclient = builder.build();
			try {
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

				log.info(methodName, "executing request " + httpPost.getRequestLine());
				CloseableHttpResponse httpResponse = httpclient.execute(httpPost);
				try {
					log.info(methodName, "----------------------------------------");
					log.info(methodName, "statusLine: " + httpResponse.getStatusLine());
					HttpEntity resEntity = httpResponse.getEntity();
					if (resEntity != null) {
						log.info(methodName, "Response content length: " + resEntity.getContentLength());
					}
					String xmlResponse = EntityUtils.toString(resEntity, Charsets.UTF_8);
					log.info(methodName, "xmlResponse: " + xmlResponse);
					EsitoRichiesta esitoRichiesta = JAXBUtility.unmarshall(xmlResponse, EsitoRichiesta.class);

					if (esitoRichiesta != null && esitoRichiesta.getDocumento() != null) {
						String urnDocumento = esitoRichiesta.getDocumento().getUrn();
						esitoInvioDocumento.setUrnDocumento(urnDocumento);

						response.setSuccess(true);
					}

				} finally {
					httpResponse.close();
				}
			} finally {
				httpclient.close();
			}

		} catch (Exception e) {
			log.error(methodName, e.getMessage(), e);

			response.setSuccess(false);
			response.addError(e.getMessage());
		}

		return response;
	}

}
