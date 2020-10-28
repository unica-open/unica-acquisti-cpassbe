/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - SIAC
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.external.impl.siac;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.xml.ws.Service;

import org.apache.logging.log4j.Level;

import it.csi.cpass.cpassbe.lib.external.impl.BaseHelperImpl;
import it.csi.cpass.cpassbe.lib.external.res.ExternalServiceResponseWrapper;
import it.csi.cpass.cpassbe.lib.util.oauth.OAuth2HandlerResolver;
import it.csi.cpass.cpassbe.lib.util.oauth.OAuth2Helper;
import it.csi.cpass.cpassbe.lib.util.soap.DefaultHandlerResolver;
import it.csi.cpass.cpassbe.lib.utils.SiacConfigurationParams;
import it.csi.siac.integ.data._1.BaseResponse;
import it.csi.siac.integ.data._1.Errore;
import it.csi.siac.integ.data._1.Esito;
import it.csi.siac.integ.data._1.Messaggio;

/**
 * Base helper for SIAC implementations
 */
public abstract class BaseSiacHelperImpl extends BaseHelperImpl {

	/**
	 * Checks the baseparameters for SIAC integraition
	 * @param params the params
	 */
	protected void checkBaseParameters(Map<String, String> params) {
		checkParameters(
				params,
				SiacConfigurationParams.OAUTH2_URL,
				SiacConfigurationParams.CONSUMER_KEY,
				SiacConfigurationParams.CONSUMER_SECRET,
				SiacConfigurationParams.CODICE_ENTE,
				SiacConfigurationParams.CODICE_APPLICATIVO);
		if(isOauth2Required(params)) {
			// Check OAuth2 params
			checkParameters(
					params,
					SiacConfigurationParams.OAUTH2_URL,
					SiacConfigurationParams.CONSUMER_KEY,
					SiacConfigurationParams.CONSUMER_SECRET);
		}
	}
	
	/**
	 * Checks whether the OAuth2 authentication is required
	 * @param params the parameters
	 * @return whether the authentication is required
	 */
	protected boolean isOauth2Required(Map<String, String> params) {
		String param = getParameter(params, SiacConfigurationParams.USE_OAUTH2);
		return Boolean.parseBoolean(param);
	}
	
	@Override
	protected void addHandlerResolver(Map<String, String> params, Service service) {
		final Level level = Level.INFO;
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
				.oAuthURL(params.get(SiacConfigurationParams.OAUTH2_URL.getParamName()))
				.consumerKey(params.get(SiacConfigurationParams.CONSUMER_KEY.getParamName()))
				.consumerSecret(params.get(SiacConfigurationParams.CONSUMER_SECRET.getParamName()))
				.type(OAuth2Helper.Type.BODY_GRANT)
				.reuseInstance(true)
				.build();
	}
	
	/**
	 * Retrieves the WSDL URL
	 * @param params the params
	 * @return the WSDL URL
	 */
	protected URL getWSDLUrl(Map<String, String> params) {
		final String methodName = "getWSDLUrl";
		URL wsdlUrl = null;
		if(params.containsKey(SiacConfigurationParams.WSDL_LOCATION.getParamName())) {
			String wsdlLocation = params.get(SiacConfigurationParams.WSDL_LOCATION.getParamName());
			try {
				wsdlUrl = new URL(wsdlLocation);
			} catch(MalformedURLException e) {
				log.warn(methodName, "Malformed WSDL url: " + wsdlLocation, e);
			}
		}
		return wsdlUrl;
	}
	
	/**
	 * Initializes the response wrapper
	 * @param <R> the response wrapper
	 * @param methodName the methodName
	 * @param baseResponse the base response
	 * @return the response wrapper
	 */
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
				// fix CPASS-8 ORD-INTEGRAZIONE SIAC: messaggi di errore - concatenare solo la descrizione
				// response.addError(error.getCodice() + " - " + error.getDescrizione());
				response.addError(error.getDescrizione());
			}
		}
		if(baseResponse.getMessaggi() != null) {
			for(Messaggio message : baseResponse.getMessaggi()) {
				log.warn(methodName, "Messaggio: " + message.getCodice() + " - " + message.getDescrizione());
				// fix CPASS-8 ORD-INTEGRAZIONE SIAC: messaggi di errore - concatenare solo la descrizione
				// response.addMessage(message.getCodice() + " - " + message.getDescrizione());
				response.addMessage(message.getDescrizione());
			}
		}
		return response;
	}
	
}
