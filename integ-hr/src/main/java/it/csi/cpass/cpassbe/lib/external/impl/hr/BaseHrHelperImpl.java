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
package it.csi.cpass.cpassbe.lib.external.impl.hr;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.xml.ws.Service;

import org.apache.logging.log4j.Level;

import it.csi.cpass.cpassbe.lib.external.impl.BaseHelperImpl;
import it.csi.cpass.cpassbe.lib.utils.HrConfigurationParams;
import it.csi.cpass.cpassbe.lib.utils.HrHandlerResolver;

/**
 * Base helper for SIAC implementations
 */
public abstract class BaseHrHelperImpl extends BaseHelperImpl {

	/**
	 * Checks the baseparameters for SIAC integraition
	 * @param params the params
	 */
	protected void checkBaseParameters(Map<String, String> params) {
		checkParameters(
				params
				,HrConfigurationParams.PW
				,HrConfigurationParams.USER
				);
		/*
		if(isOauth2Required(params)) {
			// Check OAuth2 params
			checkParameters(
					params,
					HrConfigurationParams.OAUTH2_URL,
					HrConfigurationParams.CONSUMER_KEY,
					HrConfigurationParams.CONSUMER_SECRET);
		}
		*/
	}
	
	
	@Override
	protected void addHandlerResolver(Map<String, String> params, Service service) {
		final Level level = Level.DEBUG;
		service.setHandlerResolver(new HrHandlerResolver(level,params.get(HrConfigurationParams.USER.getParamName()),params.get(HrConfigurationParams.PW.getParamName())));
	}


	
	/**
	 * Retrieves the WSDL URL
	 * @param params the params
	 * @return the WSDL URL
	 */
	protected URL getWSDLUrl(Map<String, String> params) {
		final String methodName = "getWSDLUrl";
		URL wsdlUrl = null;
		if(params.containsKey(HrConfigurationParams.WSDL_LOCATION.getParamName())) {
			String wsdlLocation = params.get(HrConfigurationParams.WSDL_LOCATION.getParamName());
			try {
				wsdlUrl = new URL(wsdlLocation);
			} catch(MalformedURLException e) {
				log.warn(methodName, "Malformed WSDL url: " + wsdlLocation, e);
			}
		}
		return wsdlUrl;
	}
	

	
}
