/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - NotiER
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
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
public enum NotiERConfigurationParams implements ConfigurationParam {

	NSO_CUSTOMIZATION_ID("NSO_CUSTOMIZATION_ID"),
	NSO_PROFILE_ID("NSO_PROFILE_ID"),

	HOST_NOTIER("HOST_NOTIER"),
	PORTA_NOTIER("PORTA_NOTIER"),
	
//	USER("USER"),
//	PW("PW"),
	;
	
	private final String paramName;
	
	private NotiERConfigurationParams(String paramName) {
		this.paramName = paramName;
	}

	@Override
	public String getParamName() {
		return paramName;
	}
}
