/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.util.jpa;

/**
 * The comparison types.
 */
public enum CompareTypes {

	/** The equals. */
	LIKE("LIKE"),

	/** The equals. */
	EQUALS("="),

	/** The notequals. */
	NOT_EQUALS("!="),

	/** The less. */
	LESS("<"),

	/** The less or equals. */
	LESS_OR_EQUALS("<="),

	/** The more. */
	MORE(">"),

	/** The more or equal. */
	MORE_OR_EQUAL(">="),
	;

	/** The value. */
	private final String value;

	/**
	 * Constructor.
	 *
	 * @param value the value
	 */
	private CompareTypes(String value) {
		this.value = value;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
}
