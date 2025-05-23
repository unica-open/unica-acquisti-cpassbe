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
package it.csi.cpass.cpassbe.lib.dto.pba;

import java.io.Serializable;

import it.csi.cpass.cpassbe.lib.dto.BaseDto;

/**
 * The Class SettoreInterventi.
 */
public class SettoreInterventi extends BaseDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The descrizione. */
	private String descrizione;
	/** The descrizione. */
	private String codice;

	/** Default constructor */
	public SettoreInterventi() {}

	/**
	 * Constructor
	 * @param id the id
	 */
	public SettoreInterventi(Integer id) {
		super(id);
	}

	/**
	 * Gets the descrizione.
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * Sets the descrizione.
	 * @param descrizione the new descrizione
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	/**
	 * Gets the codice.
	 * @return the codice
	 */
	public String getCodice() {
		return codice;
	}

	/**
	 * Sets the codice.
	 * @param codice the new codice
	 */
	public void setCodice(String codice) {
		this.codice = codice;
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append("SettoreInterventi [codice=").append(codice)
			.append(", descrizione=").append(descrizione)
			.append(", id=").append(id)
			.append("]")
			.toString();
	}

}
