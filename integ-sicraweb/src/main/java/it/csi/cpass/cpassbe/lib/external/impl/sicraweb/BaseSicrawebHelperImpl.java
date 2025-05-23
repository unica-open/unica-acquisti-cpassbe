/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - SIAC
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.external.impl.sicraweb;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.xml.ws.Service;

import org.apache.logging.log4j.Level;

import it.csi.cpass.cpassbe.lib.external.impl.BaseHelperImpl;
import it.csi.cpass.cpassbe.lib.util.oauth.OAuth2HandlerResolver;
import it.csi.cpass.cpassbe.lib.util.oauth.OAuth2Helper;
import it.csi.cpass.cpassbe.lib.util.soap.DefaultHandlerResolver;
import it.csi.cpass.cpassbe.lib.utils.SicrawebConfigurationParams;

/**
 * Base helper for SIAC implementations
 */
public abstract class BaseSicrawebHelperImpl extends BaseHelperImpl {

	/**
	 * Checks the baseparameters for SIAC integraition
	 * @param params the params
	 */
	protected void checkBaseParameters(Map<String, String> params) {
		checkParameters(
				params,
				SicrawebConfigurationParams.OAUTH2_URL,
				SicrawebConfigurationParams.CONSUMER_KEY,
				SicrawebConfigurationParams.CONSUMER_SECRET,
				SicrawebConfigurationParams.CODICE_ENTE,
				SicrawebConfigurationParams.CODICE_APPLICATIVO
				);
		if(isOauth2Required(params)) {
			// Check OAuth2 params
			checkParameters(
					params,
					SicrawebConfigurationParams.OAUTH2_URL,
					SicrawebConfigurationParams.CONSUMER_KEY,
					SicrawebConfigurationParams.CONSUMER_SECRET);
		}
	}
	
	/**
	 * Checks whether the OAuth2 authentication is required
	 * @param params the parameters
	 * @return whether the authentication is required
	 */
	protected boolean isOauth2Required(Map<String, String> params) {
		String param = getParameter(params, SicrawebConfigurationParams.USE_OAUTH2);
		return Boolean.parseBoolean(param);
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
		
		log.debug("initOAuth2Helper","OAUTH2_URL --> "     + params.get(SicrawebConfigurationParams.OAUTH2_URL.getParamName()));
		log.debug("initOAuth2Helper","CONSUMER_KEY --> "   + params.get(SicrawebConfigurationParams.CONSUMER_KEY.getParamName()));
		log.debug("initOAuth2Helper","CONSUMER_SECRET --> "+ params.get(SicrawebConfigurationParams.CONSUMER_SECRET.getParamName()));
		
		/*
		return new OAuth2Helper.Type.BODY_GRANT.initOAuth2Helper(params.get(SicrawebConfigurationParams.OAUTH2_URL.getParamName())
				, params.get(SicrawebConfigurationParams.CONSUMER_KEY.getParamName())
				, params.get(SicrawebConfigurationParams.CONSUMER_SECRET.getParamName())
				, 2000, 10);
		*/
		
		
		return new OAuth2Helper.Builder()
				.oAuthURL(params.get(SicrawebConfigurationParams.OAUTH2_URL.getParamName()))
				.consumerKey(params.get(SicrawebConfigurationParams.CONSUMER_KEY.getParamName()))
				.consumerSecret(params.get(SicrawebConfigurationParams.CONSUMER_SECRET.getParamName()))
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
		if(params.containsKey(SicrawebConfigurationParams.WSDL_LOCATION.getParamName())) {
			String wsdlLocation = params.get(SicrawebConfigurationParams.WSDL_LOCATION.getParamName());
			try {
				wsdlUrl = new URL(wsdlLocation);
			} catch(MalformedURLException e) {
				log.warn(methodName, "Malformed WSDL url: " + wsdlLocation, e);
			}
		}
		return wsdlUrl;
	}

}
