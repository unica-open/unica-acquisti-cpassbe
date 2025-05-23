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
 * The Class Ruolo.
 */
public class Ruolo extends BaseDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/** The codice. */
	private String codice;
	/** The descrizione. */
	private String descrizione;

	private String selezionabileDaProcedura;

	/** Default constructor */
	public Ruolo() {}

	/**
	 * Constructor
	 * @param id the id
	 */
	public Ruolo(Integer id) {
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
	 * @return the selezionabileDaprocedura
	 */
	public String getSelezionabileDaProcedura() {
		return selezionabileDaProcedura;
	}

	/**
	 * @param selezionabileDaprocedura the selezionabileDaprocedura to set
	 */
	public void setSelezionabileDaProcedura(String selezionabileDaProcedura) {
		this.selezionabileDaProcedura = selezionabileDaProcedura;
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append("Ruolo [codice=").append(codice)
			.append(", descrizione=").append(descrizione)
			.append(", id=").append(id)
			.append("]")
			.toString();
	}

}
