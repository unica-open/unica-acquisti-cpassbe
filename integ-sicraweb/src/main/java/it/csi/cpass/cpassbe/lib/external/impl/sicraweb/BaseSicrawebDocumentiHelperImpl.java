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

import fdewsappjdocumentigateway.ElaboraDocumentoResponse;
import fdewsappjdocumentigateway.Errore;
import fdewsappjdocumentigateway.Esito;
import fdewsappjdocumentigateway.Messaggio;
import it.csi.cpass.cpassbe.lib.external.res.ExternalServiceResponseWrapper;

/**
 * Base helper for SIAC implementations
 */
public abstract class BaseSicrawebDocumentiHelperImpl extends BaseSicrawebHelperImpl {

	/**
	 * Initializes the response wrapper
	 * @param <R> the response wrapper
	 * @param methodName the methodName
	 * @param baseResponse the base response
	 * @return the response wrapper
	 */
	protected <R> ExternalServiceResponseWrapper<R> initResponse( ElaboraDocumentoResponse baseResponse) {
		String methodName ="initResponse";
		ExternalServiceResponseWrapper<R> response = new ExternalServiceResponseWrapper<>();
		if(baseResponse == null) {
			response.addError("No response given");
			return response;
		}
		response.setSuccess(Esito.SUCCESSO.equals(baseResponse.getEsito()));
		if(!response.isSuccess()) {
			log.warn(methodName, "Esito: " + baseResponse.getEsito());
		}
		//Integer numAF = -1;
		//TODO il messaggio non è nella lista messaggi c'è da capire come intercettarlo 
		/* NON BUTTARE
		if(baseResponse!=null && baseResponse.getMessaggi().size()>0) {
			numAF = numeroAssegnazioniFinanziarie (baseResponse.getMessaggi().get(0).getDescrizione());
			if(numAF == 0 ) {
				log.warn(methodName, "Errore: 0 assegnazioni finanziarie" );
				response.addError("Errore: 0 assegnazioni finanziarie");
				response.addMessage("Errore: 0 assegnazioni finanziarie");
			}		
		}
		*/
		
		Integer numAF = numeroAssegnazioniFinanziarie (baseResponse.getResponseElaborazione());
		
		if(numAF == 0 ) {
			log.warn(methodName, "Errore: 0 assegnazioni finanziarie" );
			response.addError("Errore: 0 assegnazioni finanziarie");
			response.addMessage("Errore: 0 assegnazioni finanziarie");
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
	
	private  Integer numeroAssegnazioniFinanziarie (String value) {
		/*
		 * ENDPOINT: http://api-ent.ecosis.csi.it:80/api/SIMEL2_FdeWSAppjDocumentiGateway/1.0
			<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
			<soapenv:Header/><soapenv:Body><elaboraDocumentoResponse xmlns="urn:FdeWSAppjDocumentiGateway">
			<ente xmlns="">
			<codice>COTO</codice>
			<descrizione>COMUNE DI TORINO</descrizione>
			</ente><esito xmlns="">SUCCESSO</esito>
			<responseElaborazione xmlns="">Sono state inserite 0 assegnazioni finanziarie.</responseElaborazione>
			</elaboraDocumentoResponse>
			</soapenv:Body></soapenv:Envelope>
		 */
		//String value = "COTOCOMUNE DI TORINOSUCCESSOSono state inserite 0 assegnazioni finanziarie.";
		//baseResponse.getResponseElaborazione();
		Integer ris = -1;
		//int posStart = value.indexOf("Sono state inserite");
		//int posStop = value.indexOf("assegnazioni");
		//System.out.println("value " + value.trim());
		try {
			//value = value.substring(posStart,posStop);
			int posStart = value.indexOf("Sono state inserite");
			int posStop = value.indexOf("assegnazioni");
			value = value.substring(posStart,posStop);
			//System.out.println("value " + value.trim());
			ris = Integer.parseInt(value.trim());
		}catch(Exception e){
			ris = -1;
			log.error("numeroAssegnazioniFinanziarie", "Da rivedere l'algoritmo per il calcolo del numero AssegnazioniFinanziarie value " + value);
		}
		return ris;
	}

}
