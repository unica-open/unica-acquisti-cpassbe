/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto;

import java.io.Serializable;

/**
 * The Class Ufficio.
 */
public class Ufficio extends BaseDto<Long> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/** The codice. */
	private String codice;
	/** The descrizione. */
	private String descrizione;

	/** Default constructor */
	public Ufficio() {
	}

	/**
	 * Constructor
	 * 
	 * @param id the id
	 */
	public Ufficio(Long id) {
		super(id);
	}

	/**
	 * Gets the codice.
	 * 
	 * @return the codice
	 */
	public String getCodice() {
		return codice;
	}

	/**
	 * Sets the codice.
	 * 
	 * @param codice the new codice
	 */
	public void setCodice(String codice) {
		this.codice = codice;
	}

	/**
	 * Gets the descrizione.
	 * 
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * Sets the descrizione.
	 * 
	 * @param descrizione the new descrizione
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	public String toString() {
		return new StringBuilder()
				.append("Ufficio [codice=").append(codice)
				.append(", descrizione=").append(descrizione)
				.append(", id=").append(id)
				.append("]")
				.toString();
	}

}
