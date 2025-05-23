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
public enum SicrawebConfigurationParams implements ConfigurationParam {

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
	
	/** STATO_FATTURA_RIPARTIBILE*/
	STATO_FATTURA_RIPARTIBILE("STATO_FATTURA_RIPARTIBILE"),
	
	ANNO_AVVIO_STILO("ANNO_AVVIO_STILO"),
	
	CODICE_TIPO_PROVV_STILO("CODICE_TIPO_PROVV_STILO"),
	
	CODICE_TIPO_PROVV_ANTE_STILO("CODICE_TIPO_PROVV_ANTE_STILO"),
	
	NUMERAZIONE_ATTI_UNIVOCA("NUMERAZIONE_ATTI_UNIVOCA"),
	
	CODICE_TIPO_DOCUMENTO("CODICE_TIPO_DOCUMENTO")
	
	;
	
	private final String paramName;
	
	private SicrawebConfigurationParams(String paramName) {
		this.paramName = paramName;
	}

	@Override
	public String getParamName() {
		return paramName;
	}
}
