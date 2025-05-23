/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - STILO
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.external.impl.stilo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.apache.logging.log4j.Level;

import it.csi.cpass.cpassbe.lib.external.impl.BaseHelperImpl;
import it.csi.cpass.cpassbe.lib.util.convert.StringHelper;
import it.csi.cpass.cpassbe.lib.util.serialization.JAXBUtility;
import it.csi.cpass.cpassbe.lib.util.soap.AllegatoHandlerResolver;
import it.csi.cpass.cpassbe.lib.util.soap.DefaultHandlerResolver;
import it.csi.cpass.cpassbe.lib.utils.StiloConfigurationParams;
import it.eng.auriga.repository2.model.searchoutput.RequestGetAttoPerAcquisti;
import it.eng.auriga.repository2.webservices.getmetadataud.Service;

/**
 * Base helper for SIAC implementations
 */
public abstract class BaseStiloHelperImpl extends BaseHelperImpl {

	protected boolean readAttachment = false;
	//protected String descAD ;
	//protected String descDD ;
	//protected String descDG ;
	
	/**
	 * Checks the baseparameters for SIAC integraition
	 * @param params the params
	 */
	protected void checkBaseParameters(Map<String, String> params) {
		
		checkParameters(
				params,
				StiloConfigurationParams.CODICE_ENTE,
				StiloConfigurationParams.CODICE_APPLICATIVO,
				StiloConfigurationParams.AMBIENTE,
				//StiloConfigurationParams.WSDL_LOCATION,
				StiloConfigurationParams.USER,
				StiloConfigurationParams.PW
				//StiloConfigurationParams.AD,
				//StiloConfigurationParams.DD,
				//StiloConfigurationParams.DG
				);
		//descAD = params.get(StiloConfigurationParams.AD.getParamName());
		//descDD = params.get(StiloConfigurationParams.DD.getParamName());
		//descDG = params.get(StiloConfigurationParams.DG.getParamName());
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
	 * Retrieves the WSDL URL
	 * @param params the params
	 * @return the WSDL URL
	 */
	protected URL getWSDLUrlRicercaDoc(Map<String, String> params) {
		final String methodName = "getWSDLUrlRicercaDoc";
		URL wsdlUrl = null;
		if(params.containsKey(StiloConfigurationParams.WSDL_LOCATION_RICERCA_DOC.getParamName())) {
			String wsdlLocation = params.get(StiloConfigurationParams.WSDL_LOCATION_RICERCA_DOC.getParamName());
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
	
	/*
	codica applicazione                CPASS
	codice istanza applicazione        TEST
	username                           user-cpass
	password                           null 
	*/
	protected it.eng.auriga.repository2.webservices.trovadocfolder.Service composeTrovaDocFolderRequest(Map<String, String> params, Object obj) {
		it.eng.auriga.repository2.webservices.trovadocfolder.Service req = new it.eng.auriga.repository2.webservices.trovadocfolder.Service();
		req.setCodApplicazione(getParameter(params, StiloConfigurationParams.CODICE_APPLICATIVO));
		req.setIstanzaApplicazione(getParameter(params, StiloConfigurationParams.AMBIENTE));
		req.setUserName(getParameter(params, StiloConfigurationParams.USER));
		req.setPassword(getParameter(params, StiloConfigurationParams.PW));
		String xml = JAXBUtility.marshall(obj);
		req.setXml(xml);
		req.setHash(StringHelper.base64SHA1(xml));	
		log.info("composeRequest", req);
		return req;
	}
	
	protected it.eng.auriga.repository2.webservices.getattoperacquisti.Service composeAttoPerAcquistiRequest(Map<String, String> params, RequestGetAttoPerAcquisti requestGetAttoPerAcquisti) {
		it.eng.auriga.repository2.webservices.getattoperacquisti.Service req = new it.eng.auriga.repository2.webservices.getattoperacquisti.Service();
		req.setCodApplicazione(getParameter(params, StiloConfigurationParams.CODICE_APPLICATIVO));
		req.setIstanzaApplicazione(getParameter(params, StiloConfigurationParams.AMBIENTE));
		req.setUserName(getParameter(params, StiloConfigurationParams.USER));
		req.setPassword(getParameter(params, StiloConfigurationParams.PW));	
		String xml = JAXBUtility.marshall(requestGetAttoPerAcquisti);	
		//N.B. Questo replace e da farsi per baypassare un controllo lato fornitore 
		//che vuole il tag requestGetAttoPerAcquisti con la prima lettera grande
		//xsd di questo oggetto e' probabilmente non conforme all'ultima versione per vui va trattato dopo la sua generazione con maven rinominandolo in 
		xml =  xml.replace("requestGetAttoPerAcquisti", "RequestGetAttoPerAcquisti");
		//xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><RequestGetAttoPerAcquisti><SiglaRegistro>DD</SiglaRegistro><Anno>2022</Anno><Nro>1879</Nro></RequestGetAttoPerAcquisti>";
		req.setXml(xml);
		req.setHash(StringHelper.base64SHA1(xml));	
		log.info("composeRequest", req);
		return req;
	}
	/*
	<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<RequestGetAttoPerAcquisti>
    <SiglaRegistro>DD</SiglaRegistro>
    <Anno>2022</Anno>
    <Nro>1</Nro>
</RequestGetAttoPerAcquisti>
*/
	
	
	
/*	
	<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
	<requestGetAttoPerAcquisti>
	    <SiglaRegistro>DD</SiglaRegistro>
	    <Anno>2022</Anno>
	    <Nro>1874</Nro>
	</requestGetAttoPerAcquisti>
*/
	
	
	/*
	codica applicazione                CPASS
	codice istanza applicazione        TEST
	username                           user-cpass
	password                           null 
	*/
	protected Service composeRequestMetadataUd(Map<String, String> params, Object obj) {
		Service req = new Service();
		req.setCodApplicazione(getParameter(params, StiloConfigurationParams.CODICE_APPLICATIVO));
		req.setIstanzaApplicazione(getParameter(params, StiloConfigurationParams.AMBIENTE));
		req.setUserName(getParameter(params, StiloConfigurationParams.USER));
		req.setPassword(getParameter(params, StiloConfigurationParams.PW));
		String xml = JAXBUtility.marshall(obj);
		req.setXml(xml);
		req.setHash(StringHelper.base64SHA1(xml));	
		log.info("composeRequest", req);
		return req;
	}
	@Override
	protected void addHandlerResolver(Map<String, String> params, javax.xml.ws.Service service) {
		final Level level = Level.INFO;
		DefaultHandlerResolver dhr = new DefaultHandlerResolver(level);
		if(readAttachment) {
			service.setHandlerResolver(new AllegatoHandlerResolver(level));
			return;
		}
		service.setHandlerResolver(dhr);		
	}
}
