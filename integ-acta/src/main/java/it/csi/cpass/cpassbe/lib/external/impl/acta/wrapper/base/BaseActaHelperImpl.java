/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - ACTA
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.external.impl.acta.wrapper.base;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.xml.ws.Service;

import org.apache.logging.log4j.Level;

import it.csi.cpass.cpassbe.lib.external.impl.BaseHelperImpl;
import it.csi.cpass.cpassbe.lib.util.oauth.OAuth2HandlerResolver;
import it.csi.cpass.cpassbe.lib.util.oauth.OAuth2Helper;
import it.csi.cpass.cpassbe.lib.util.soap.DefaultHandlerResolver;
import it.csi.cpass.cpassbe.lib.utils.ActaConfigurationParams;

/**
 * Base helper for ACTA implementations
 */
public abstract class BaseActaHelperImpl extends BaseHelperImpl {

	protected boolean readAttachment = false;
	

	/**
	 * Checks the baseparameters for ACTA integration
	 * @param params the params
	 */
	
	protected void checkBaseParameters(Map<String, String> params) {
		checkParameters(
				params
				,ActaConfigurationParams.REPOSITORY_NAME
				,ActaConfigurationParams.SISTEMA_DOCUMENTALE
				,ActaConfigurationParams.TITOLARIO
				//,ActaConfigurationParams.OAUTH2_URL
				//,ActaConfigurationParams.CONSUMER_KEY
				//,ActaConfigurationParams.CONSUMER_SECRET
				//,ActaConfigurationParams.CODICE_ENTE
				//,ActaConfigurationParams.CODICE_APPLICATIVO
				);
		/*
		if(isOauth2Required(params)) {
			// Check OAuth2 params
			checkParameters(
					params,
					ActaConfigurationParams.OAUTH2_URL,
					ActaConfigurationParams.CONSUMER_KEY,
					ActaConfigurationParams.CONSUMER_SECRET);
		}
		*/
	}

	protected boolean isOauth2Required(Map<String, String> params) {
		String param = getParameter(params, ActaConfigurationParams.USE_OAUTH2);
		return Boolean.parseBoolean(param);
	}
	
	/**
	 * Retrieves the WSDL URL
	 * @param params the params
	 * @return the WSDL URL
	 */
	protected URL getWSDLUrl(Map<String, String> params) {
		final String methodName = "getWSDLUrl";
		URL wsdlUrl = null;
		if(params.containsKey(ActaConfigurationParams.WSDL_LOCATION.getParamName())) {
			String wsdlLocation = params.get(ActaConfigurationParams.WSDL_LOCATION.getParamName());
			try {
				wsdlUrl = new URL(wsdlLocation);
			} catch(MalformedURLException e) {
				log.warn(methodName, "Malformed WSDL url: " + wsdlLocation, e);
			}
		}
		return wsdlUrl;
	}
	
	@Override
	protected void addHandlerResolver(Map<String, String> params, Service service) {
		final Level level = Level.DEBUG;
		if(isOauth2Required(params)) {
			service.setHandlerResolver(new OAuth2HandlerResolver(level, initOAuth2Helper(params)));
			return;
		}
		service.setHandlerResolver(new DefaultHandlerResolver(level));
	}

	/**
	 * Initialization of the OAuth2 helper
	 * @param params the params
	 * @return the helper
	 */
	protected OAuth2Helper initOAuth2Helper(Map<String, String> params) {
		return new OAuth2Helper.Builder()
				.oAuthURL(params.get(ActaConfigurationParams.OAUTH2_URL.getParamName()))
				.consumerKey(params.get(ActaConfigurationParams.CONSUMER_KEY.getParamName()))
				.consumerSecret(params.get(ActaConfigurationParams.CONSUMER_SECRET.getParamName()))
				.type(OAuth2Helper.Type.BODY_GRANT)
				.reuseInstance(true)
				.build();
	}
	

	
	/**
	 * Initializes the response wrapper
	 * @param <R> the response wrapper
	 * @param methodName the methodName
	 * @param baseResponse the base response
	 * @return the response wrapper
	 */
/*
	protected <R> ExternalServiceResponseWrapper<R> initResponse(String methodName, BaseResponse baseResponse) {
		ExternalServiceResponseWrapper<R> response = new ExternalServiceResponseWrapper<>();
		if(baseResponse == null) {
			response.addError("No response given");
			return response;
		}
		response.setSuccess(Esito.SUCCESSO.equals(baseResponse.getEsito()));
		if(!response.isSuccess()) {
			log.warn(methodName, "Esito: " + baseResponse.getEsito());
		}
		
		if(baseResponse.getErrori() != null) {
			for(Errore error : baseResponse.getErrori()) {
				log.warn(methodName, "Errore: " + error.getCodice() + " - " + error.getDescrizione());
				response.addError(error.getDescrizione());
			}
		}
		if(baseResponse.getMessaggi() != null) {
			for(Messaggio message : baseResponse.getMessaggi()) {
				log.warn(methodName, "Messaggio: " + message.getCodice() + " - " + message.getDescrizione());
				response.addMessage(message.getDescrizione());
			}
		}

		return response;
	}
	*/
	//addHandlerResolver(params, ricercaServiceService);
}
