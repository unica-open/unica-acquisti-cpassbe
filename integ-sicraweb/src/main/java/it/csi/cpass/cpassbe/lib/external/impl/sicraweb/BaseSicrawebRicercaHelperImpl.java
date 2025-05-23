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

import fdewsappjricercagateway.BaseResponse;
import fdewsappjricercagateway.Errore;
import fdewsappjricercagateway.Esito;
import fdewsappjricercagateway.Messaggio;
import it.csi.cpass.cpassbe.lib.external.res.ExternalServiceResponseWrapper;

/**
 * Base helper for SIAC implementations
 */
public abstract class BaseSicrawebRicercaHelperImpl extends BaseSicrawebHelperImpl {

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
