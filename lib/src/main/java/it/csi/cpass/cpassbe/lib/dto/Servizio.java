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

package it.csi.cpass.cpassbe.lib.dto;

import java.io.Serializable;

/**
 * The Class Servizio.
 */
public class Servizio extends BaseDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The codcie. */
	private String codice;
	/** The descrizione. */
	private String descrizione;

	/** Default constructor */
	public Servizio() {}

	/**
	 * Constructor
	 * @param id the id
	 */
	public Servizio(Integer id) {
		super(id);
	}

	/**
	 * @return the codice
	 */
	public String getCodice() {
		return codice;
	}

	/**
	 * @param codice the codice to set
	 */
	public void setCodice(String codice) {
		this.codice = codice;
	}

	/**
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * @param descrizione the descrizione to set
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return new StringBuilder()
			.append("Fruitore [codice=").append(codice)
			.append(", descrizione=").append(descrizione)
			.append("]")
			.toString();
	}

}
