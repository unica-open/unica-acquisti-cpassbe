/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto.error;

/**
 * Core error types
 */
public enum CoreError implements ErrorCreator {
	/** Generic error */
	GENERIC_ERROR("SYS-SYS-E-0001", "{error}"),
	/** Internal system error */
	SYSTEM_ERROR("SYS-SYS-E-0002", "System error: {error}"),
	/** Required parameter not passed to the invocation */
	REQUIRED_PARAMETER_OMITTED("SYS-SYS-E-0003", "Required parameter omitted: {parameter}"),
	/** Required parameter not passed to the invocation, or empty if passed */
	REQUIRED_PARAMETER_OMITTED_EMPTY("SYS-SYS-E-0004", "Required parameter omitted or empty: {parameter}"),
	/** Required parameter not passed to the invocation, or empty if passed */
	STRING_PARAMETER_LENGTH_CHECK_FAILED("SYS-SYS-E-0005", "{fieldName} must be {operator} {value}"),
	/** Unmapped entity */
	UNMAPPED_ENTITY("SYS-SYS-E-0006", "{entity} not mapped on system"),
	/** Mapped entity */
	MAPPED_ENTITY("SYS-SYS-E-0006_BIS", "{entity} already  mapped on system"),
	/** Access forbidden */
	FORBIDDEN("SYS-SYS-E-0007", "Access forbidden"),
	/** Invalid Optlock */
	INVALID_OPTLOOK("SYS-SYS-E-0008", "Invalid optlook : {optlock}"),
	/** Wrapper error */
	SYSSYSE0009("SYS-SYS-E-0009", "The following errors were found: {errori}"),
	/** Elaboration wrapper error */
	SYSSYSE0010("SYS-SYS-E-0010", "The following errors were found during elaboration {elaborazione_id}"),
	/** Elaboration wrapper error */
	SYSSYSE0011("SYS-SYS-E-0011", "Codice fiscale non valido"),
	/** Elaboration wrapper error */
	SYSSYSE0016("SYS-SYS-E-0016", "Servizio esterno {servizio} non raggiungibile"),
	;

	private final String code;
	private final String type;
	private final String group;
	private final String message;

	/**
	 * Private constructor
	 * @param code the code
	 * @param message the message
	 */
	private CoreError(String code, String group, String message) {
		this.code = code;
		this.type = "ERROR";
		this.group = group;
		this.message = message;
	}

	/**
	 * Private constructor
	 * @param code the code
	 * @param message the message
	 */
	private CoreError(String code, String message) {
		this.code = code;
		this.type = "ERROR";
		this.group = null;
		this.message = message;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public String getGroup() {
		return group;
	}

}
