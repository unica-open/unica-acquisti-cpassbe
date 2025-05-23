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
package it.csi.cpass.cpassbe.lib.utils;

import it.csi.cpass.cpassbe.lib.external.itf.ConfigurationParam;

/**
 * SIAC configuration params
 */
public enum StiloConfigurationParams implements ConfigurationParam {

	CODICE_ENTE("CODICE_ENTE"),
	/** Identifier for the application */
	CODICE_APPLICATIVO("CODICE_APPLICATIVO"),
	/** WSDL location */
	WSDL_LOCATION_RICERCA_DOC("WSDL_LOCATION_RICERCA_DOC"),
	/** WSDL location */	
	WSDL_LOCATION("WSDL_LOCATION"),
	/** */
	USER("USER"),
	/** */
	PW("PW"),
	/** */
	AMBIENTE("AMBIENTE"),
	/** */
	AD("AD"),
	/** */
	DD("DD"),
	/** */
	DG("DG")
	;
	
	private final String paramName;
	
	private StiloConfigurationParams(String paramName) {
		this.paramName = paramName;
	}

	@Override
	public String getParamName() {
		return paramName;
	}
}
