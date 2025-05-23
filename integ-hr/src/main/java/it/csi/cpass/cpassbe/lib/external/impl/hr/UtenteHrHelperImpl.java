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

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.namespace.QName;

import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.external.UtenteHrHelper;
import it.csi.cpass.cpassbe.lib.external.res.ExternalServiceResponseWrapper;
import it.csi.cpass.cpassbe.lib.utils.CommonUtility;

/**
 * Example CDI UtentehrImpl helper
 */
public class UtenteHrHelperImpl extends BaseHrHelperImpl implements UtenteHrHelper {

	@Override
	public ExternalServiceResponseWrapper<Utente> getUtenteHrByCf(Map<String, String> params, String cf,String codiceEnte) {		
		ExternalServiceResponseWrapper<Utente> response = new ExternalServiceResponseWrapper<>();
		if(codiceEnte.equalsIgnoreCase("COTO")) {
			response= getutenteHrCotoByCf(params, cf);
		}
		if(codiceEnte.equalsIgnoreCase("CMTO")) {
			response= getutenteHrCmtoByCf(params, cf);
		}
		if(codiceEnte.equalsIgnoreCase("REGP")) {
			response= getutenteHrRegpByCf(params, cf);
		}
		return response;
	}

	/**
	 * @param params
	 * @param cf
	 * @return
	 */
	protected ExternalServiceResponseWrapper<Utente> getutenteHrCotoByCf(Map<String, String> params, String cf) {
		log.info("UtenteHrHelperImpl", "*************getUtenteHrByCf*******************");
		Utente utenteRicercato = new Utente();
		checkBaseParameters(params);
		ExternalServiceResponseWrapper<Utente> response = new ExternalServiceResponseWrapper<>();	
		try {

			URL wsdlUrl = getWSDLUrl(params);
			com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_comune_torino.CUSCSISOACOMUNETORINOService service = wsdlUrl != null ? new com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_comune_torino.CUSCSISOACOMUNETORINOService(wsdlUrl) : new com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_comune_torino.CUSCSISOACOMUNETORINOService();
			addHandlerResolver(params, service);
			
			com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_comune_torino.CUSCSISOACOMUNETORINOPortType portType = service.getCUSCSISOACOMUNETORINOPort();			
			
			com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_comune_torino.get_dati_anagrafici.InputParameters body = new com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_comune_torino.get_dati_anagrafici.InputParameters();
			
			XmlElementRef nameSpaceCodFisc = com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_comune_torino.get_dati_anagrafici.InputParameters.class.getDeclaredField("pcodicefiscale").getAnnotation(XmlElementRef.class);
			JAXBElement<String> codicefisc = new JAXBElement<String>(new QName(nameSpaceCodFisc.namespace(),nameSpaceCodFisc.name()), String.class , cf);
			codicefisc.setValue(cf);
			body.setPCODICEFISCALE(codicefisc );			
			String dt = CommonUtility.dateToString(new Date(), "yyyyMMdd");			

			XmlElementRef nameSpaceData = com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_comune_torino.get_dati_anagrafici.InputParameters.class.getDeclaredField("pdataestrazione").getAnnotation(XmlElementRef.class);
			JAXBElement<String> dataEstrazione= new JAXBElement<String>(new QName(nameSpaceData.namespace(),nameSpaceData.name()), String.class , dt );
			body.setPDATAESTRAZIONE(dataEstrazione);
			
			com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_comune_torino.SOAHeader header = new com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_comune_torino.SOAHeader();
			com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_comune_torino.get_dati_anagrafici.OutputParameters res = portType.getDATIANAGRAFICI(header , body);
			if(res!=null && res.getOPCODICEFISCALE()!= null && res.getOPCODICEFISCALE().getValue() != null && !res.getOPCODICEFISCALE().getValue().trim().equals("")) {			
				utenteRicercato.setMsgData(res.getOPMSGDATA().getValue());
				utenteRicercato.setNome(res.getOPNOME().getValue());
				utenteRicercato.setCognome(res.getOPCOGNOME().getValue());
				utenteRicercato.setEmail(res.getOPEMAIL().getValue());
				utenteRicercato.setTelefono("");
				utenteRicercato.setPresenteSuHr(Boolean.TRUE);
				//TODO eventualmente da arricchire
			}else {
				utenteRicercato.setPresenteSuHr(Boolean.FALSE);
			}
			response.setSuccess(Boolean.TRUE);
			response.setResponse(utenteRicercato);
			log.info("UtenteHrHelperImpl", "****** Fine getUtenteHR *******************");
			return response;
		}catch(Exception e) {
			response.setSuccess(Boolean.FALSE);
			List<String> errors = new ArrayList<String>();
			errors.add("Servizio HR non disponibile");
			response.setErrors(errors);
			log.error("getUtenteByHr", "Desc error --> "+e.getMessage());
			return response;
		}
	}

	//CMTO
	protected ExternalServiceResponseWrapper<Utente> getutenteHrCmtoByCf(Map<String, String> params, String cf) {
		log.info("UtenteHrHelperImpl", "*************CMTO getUtenteHrByCf*******************");
		Utente utenteRicercato = new Utente();
		checkBaseParameters(params);
		ExternalServiceResponseWrapper<Utente> response = new ExternalServiceResponseWrapper<>();	
		try {

			URL wsdlUrl = getWSDLUrl(params);
			com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_citta_metro_torino.CUSCSISOACITTAMETROTORINOService service = wsdlUrl != null ? new com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_citta_metro_torino.CUSCSISOACITTAMETROTORINOService(wsdlUrl) : new com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_citta_metro_torino.CUSCSISOACITTAMETROTORINOService();
			addHandlerResolver(params, service);
			
			com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_citta_metro_torino.CUSCSISOACITTAMETROTORINOPortType portType = service.getCUSCSISOACITTAMETROTORINOPort();			
			
			com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_citta_metro_torino.get_dati_anagrafici.InputParameters body = new com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_citta_metro_torino.get_dati_anagrafici.InputParameters();
			
			XmlElementRef nameSpaceCodFisc = com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_citta_metro_torino.get_dati_anagrafici.InputParameters.class.getDeclaredField("pcodicefiscale").getAnnotation(XmlElementRef.class);
			JAXBElement<String> codicefisc = new JAXBElement<String>(new QName(nameSpaceCodFisc.namespace(),nameSpaceCodFisc.name()), String.class , cf);
			codicefisc.setValue(cf);
			body.setPCODICEFISCALE(codicefisc );			
			String dt = CommonUtility.dateToString(new Date(), "yyyyMMdd");			

			XmlElementRef nameSpaceData = com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_citta_metro_torino.get_dati_anagrafici.InputParameters.class.getDeclaredField("pdataestrazione").getAnnotation(XmlElementRef.class);
			JAXBElement<String> dataEstrazione= new JAXBElement<String>(new QName(nameSpaceData.namespace(),nameSpaceData.name()), String.class , dt );
			body.setPDATAESTRAZIONE(dataEstrazione);
			
			com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_citta_metro_torino.SOAHeader header = new com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_citta_metro_torino.SOAHeader();
			com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_citta_metro_torino.get_dati_anagrafici.OutputParameters res = portType.getDATIANAGRAFICI(header , body);
			
			if(res!=null && res.getOPCODICEFISCALE()!= null && res.getOPCODICEFISCALE().getValue() != null && !res.getOPCODICEFISCALE().getValue().trim().equals("")) {			
				utenteRicercato.setMsgData(res.getOPMSGDATA().getValue());
				utenteRicercato.setNome(res.getOPNOME().getValue());
				utenteRicercato.setCognome(res.getOPCOGNOME().getValue());
				utenteRicercato.setPresenteSuHr(Boolean.TRUE);
				utenteRicercato.setEmail(res.getOPEMAIL().getValue());
				utenteRicercato.setTelefono("");

				//TODO eventualmente da arricchire
			}else {
				utenteRicercato.setPresenteSuHr(Boolean.FALSE);
			}
			response.setSuccess(Boolean.TRUE);
			response.setResponse(utenteRicercato);
			log.info("UtenteHrHelperImpl", "****** Fine CMTO getUtenteHR *******************");
			return response;
		}catch(Exception e) {
			response.setSuccess(false);
			List<String> errors = new ArrayList<String>();
			errors.add("Servizio HR non disponibile");
			response.setErrors(errors);
			log.error("getUtenteByHr", "Desc error --> "+e.getMessage());
			return response;
		}
	}
		
	protected ExternalServiceResponseWrapper<Utente> getutenteHrRegpByCf(Map<String, String> params, String cf) {
		log.info("UtenteHrHelperImpl", "*************REGP getUtenteHrByCf*******************");
		Utente utenteRicercato = new Utente();
		checkBaseParameters(params);
		ExternalServiceResponseWrapper<Utente> response = new ExternalServiceResponseWrapper<>();	
		try {

			URL wsdlUrl = getWSDLUrl(params);
			com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_giunta_regionale.CUSCSISOAGIUNTAREGIONALEService service = wsdlUrl != null ? new com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_giunta_regionale.CUSCSISOAGIUNTAREGIONALEService(wsdlUrl) : new com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_giunta_regionale.CUSCSISOAGIUNTAREGIONALEService();
			//aggiungo attributi nell'header
			addHandlerResolver(params, service);
			
			com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_giunta_regionale.CUSCSISOAGIUNTAREGIONALEPortType portType = service.getCUSCSISOAGIUNTAREGIONALEPort();			
			
			com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_giunta_regionale.get_dati_anagrafici.InputParameters body = new com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_giunta_regionale.get_dati_anagrafici.InputParameters();
			
			XmlElementRef nameSpaceCodFisc = com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_giunta_regionale.get_dati_anagrafici.InputParameters.class.getDeclaredField("pcodicefiscale").getAnnotation(XmlElementRef.class);
			JAXBElement<String> codicefisc = new JAXBElement<String>(new QName(nameSpaceCodFisc.namespace(),nameSpaceCodFisc.name()), String.class , cf);
			codicefisc.setValue(cf);
			body.setPCODICEFISCALE(codicefisc );			
			String dt = CommonUtility.dateToString(new Date(), "yyyyMMdd");			

			XmlElementRef nameSpaceData = com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_giunta_regionale.get_dati_anagrafici.InputParameters.class.getDeclaredField("pdataestrazione").getAnnotation(XmlElementRef.class);
			JAXBElement<String> dataEstrazione= new JAXBElement<String>(new QName(nameSpaceData.namespace(),nameSpaceData.name()), String.class , dt );
			body.setPDATAESTRAZIONE(dataEstrazione);
			
			com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_giunta_regionale.SOAHeader header = new com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_giunta_regionale.SOAHeader();
			com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_giunta_regionale.get_dati_anagrafici.OutputParameters res = portType.getDATIANAGRAFICI(header , body);
			
			if(res!=null && res.getOPCODICEFISCALE()!= null && res.getOPCODICEFISCALE().getValue() != null && !res.getOPCODICEFISCALE().getValue().trim().equals("")) {			
				utenteRicercato.setMsgData(res.getOPMSGDATA().getValue());
				utenteRicercato.setNome(res.getOPNOME().getValue());
				utenteRicercato.setCognome(res.getOPCOGNOME().getValue());
				utenteRicercato.setPresenteSuHr(Boolean.TRUE);
				utenteRicercato.setEmail(res.getOPEMAIL().getValue());
				utenteRicercato.setTelefono("");
				//TODO eventualmente da arricchire
			}else {
				utenteRicercato.setPresenteSuHr(Boolean.FALSE);
			}
			response.setSuccess(Boolean.TRUE);
			response.setResponse(utenteRicercato);
			log.info("UtenteHrHelperImpl", "****** Fine REGP getUtenteHR *******************");
			return response;
		}catch(Exception e) {
			response.setSuccess(false);
			List<String> errors = new ArrayList<String>();
			errors.add("Servizio HR non disponibile");
			response.setErrors(errors);
			log.error("getUtenteByHr", "Desc error --> "+e.getMessage());
			return response;
		}
	}

}