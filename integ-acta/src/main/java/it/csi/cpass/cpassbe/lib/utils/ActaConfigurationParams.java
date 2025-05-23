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
package it.csi.cpass.cpassbe.lib.utils;

import it.csi.cpass.cpassbe.lib.external.itf.ConfigurationParam;

/**
 * SIAC configuration params
 */
public enum ActaConfigurationParams implements ConfigurationParam {

	/** Whether to use OAuth2 */
	USE_OAUTH2("USE_OAUTH2"),
	
	/** OAuth2 endpoint URL */
	OAUTH2_URL("OAUTH2_URL"),
	
	/** OAuth2 consumer key */
	CONSUMER_KEY("CONSUMER_KEY"),
	
	/** OAuth2 consumer secret */
	CONSUMER_SECRET("CONSUMER_SECRET"),
	
	/** Identifier for the ente */
	CODICE_ENTE("CODICE_ENTE"),
	
	/** Identifier for the application */
	CODICE_APPLICATIVO("CODICE_APPLICATIVO"),
	
	/** WSDL location */
	WSDL_LOCATION("WSDL_LOCATION"), 
	
	REPOSITORY_NAME("REPOSITORY_NAME"), 
	
	SISTEMA_DOCUMENTALE("SISTEMA_DOCUMENTALE"), 
	
	APPKEY("APPKEY"), 
	
	NODO_RESPONSABILE("NODO_RESPONSABILE"), 
	
	CODICE_FISCALE_UTENTE("CODICE_FISCALE_UTENTE"), 
	
	ID_AOO	("ID_AOO"), 
	// DA MAPPARE
	FORMA_DOC_ORDINI("FORMA_DOC_ORDINI"), 
	
	STATO_EFFICACIA("STATO_EFFICACIA"), 
	
	VITAL_RECORD_CODE("VITAL_RECORD_CODE"), 
	
	RICERCA_SOGGETTO_FONTE_ESTERNA("RICERCA_SOGGETTO_FONTE_ESTERNA"), 
	
	TITOLARIO("TITOLARIO_ID"), 
	;
	
	private final String paramName;
	
	private ActaConfigurationParams(String paramName) {
		this.paramName = paramName;
	}

	@Override
	public String getParamName() {
		return paramName;
	}
}
