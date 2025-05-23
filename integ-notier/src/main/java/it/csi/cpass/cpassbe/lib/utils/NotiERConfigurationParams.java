/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - NotiER
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
public enum NotiERConfigurationParams implements ConfigurationParam {

	NSO_CUSTOMIZATION_ID("NSO_CUSTOMIZATION_ID"),
	NSO_PROFILE_ID("NSO_PROFILE_ID"),
	NSO_DOCUMENT_ID("NSO_DOCUMENT_ID"),
	NSO_RECIPIENT_ID("NSO_RECIPIENT_ID"),
	NSO_SENDER_IDENTIFIER("NSO_SENDER_IDENTIFIER"),
	
	PROTOCOL_NOTIER("PROTOCOL_NOTIER"),
	HOST_NOTIER("HOST_NOTIER"),
	PORTA_NOTIER("PORTA_NOTIER"),
	
	KEYSTORE_PATH_NOTIER("KEYSTORE_PATH_NOTIER"),
	KEYSTORE_PASS_NOTIER("KEYSTORE_PASS_NOTIER"),
	KEY_PASS_NOTIER("KEY_PASS_NOTIER"),
	NSO_UNICO_DESTINATARIO("NSO_UNICO_DESTINATARIO"),
	
	PROXY_HOSTNAME_NOTIER("PROXY_HOSTNAME_NOTIER"),
	PROXY_PORT_NOTIER("PROXY_PORT_NOTIER"),

	
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
