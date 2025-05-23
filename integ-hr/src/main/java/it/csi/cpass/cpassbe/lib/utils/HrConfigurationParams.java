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
public enum HrConfigurationParams implements ConfigurationParam {

	/** Identifier for the pw */
	PW("PW"),

	/** Identifier for the username */
	USER("USER"),

	/** Identifier for the ente */
	CODICE_ENTE("CODICE_ENTE"),
	
	/** Identifier for the application */
	CODICE_APPLICATIVO("CODICE_APPLICATIVO"),
	
	/** WSDL location */
	WSDL_LOCATION("WSDL_LOCATION"),
	
	;
	
	private final String paramName;
	
	private HrConfigurationParams(String paramName) {
		this.paramName = paramName;
	}

	@Override
	public String getParamName() {
		return paramName;
	}
}
