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
package it.csi.cpass.cpassbe.lib.dto.ord;

import java.io.Serializable;

import it.csi.cpass.cpassbe.lib.dto.BaseDto;
import it.csi.cpass.cpassbe.lib.dto.Ente;

/**
 * The Class TipoProcedura.
 */
public class TipoProceduraOrd extends BaseDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/** The codice. */
	private String codice;
	/** The descrizione. */
	private String descrizione;
	/** The ente. */
	private Ente ente;
	/** Default constructor */
	public TipoProceduraOrd() {
	}

	/**
	 * Constructor
	 *
	 * @param id the id
	 */
	public TipoProceduraOrd(Integer id) {
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


	/**
	 * @return the ente
	 */
	public Ente getEnte() {
		return ente;
	}

	/**
	 * @param ente the ente to set
	 */
	public void setEnte(Ente ente) {
		this.ente = ente;
	}

	@Override
	public String toString() {
		return new StringBuilder()
				.append("TipoProcedura [codice=").append(codice)
				.append(", descrizione=").append(descrizione)
				.append(", id=").append(id)
				.append("]").toString();
	}

}
