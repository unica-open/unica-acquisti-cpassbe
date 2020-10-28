/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - STILO
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.external.impl.stilo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import it.csi.cpass.cpassbe.lib.external.impl.BaseHelperImpl;
import it.csi.cpass.cpassbe.lib.util.convert.StringHelper;
import it.csi.cpass.cpassbe.lib.util.serialization.JAXBUtility;
import it.csi.cpass.cpassbe.lib.utils.StiloConfigurationParams;
import it.eng.auriga.repository2.webservices.getmetadataud.Service;

/**
 * Base helper for SIAC implementations
 */
public abstract class BaseStiloHelperImpl extends BaseHelperImpl {

	/**
	 * Checks the baseparameters for SIAC integraition
	 * @param params the params
	 */
	protected void checkBaseParameters(Map<String, String> params) {
		
		checkParameters(
				params,
				StiloConfigurationParams.CODICE_ENTE,
				StiloConfigurationParams.CODICE_APPLICATIVO,
				//StiloConfigurationParams.WSDL_LOCATION,
				StiloConfigurationParams.USER,
				StiloConfigurationParams.PW);
	}
	
	/**
	 * Retrieves the WSDL URL
	 * @param params the params
	 * @return the WSDL URL
	 */
	protected URL getWSDLUrl(Map<String, String> params) {
		final String methodName = "getWSDLUrl";
		URL wsdlUrl = null;
		if(params.containsKey(StiloConfigurationParams.WSDL_LOCATION.getParamName())) {
			String wsdlLocation = params.get(StiloConfigurationParams.WSDL_LOCATION.getParamName());
			try {
				wsdlUrl = new URL(wsdlLocation);
			} catch(MalformedURLException e) {
				log.warn(methodName, "Malformed WSDL url: " + wsdlLocation, e);
			}
		}
		return wsdlUrl;
	}
	
	/**
	 * Composes the request
	 * @param params the params
	 * @param obj the object to send
	 * @return the servire request
	 */
	protected Service composeRequest(Map<String, String> params, Object obj) {
		Service req = new Service();
		
		// FIXME: set data from params
		req.setCodApplicazione("CPASS");
		req.setIstanzaApplicazione(null);
		req.setUserName("USER-CPASS");
		req.setPassword(null);
		
		String xml = JAXBUtility.marshall(obj);
		req.setXml(xml);
		req.setHash(StringHelper.base64SHA1(xml));
		
		return req;
	}
}
