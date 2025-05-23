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
 * The Class Stato.
 */
public class TipoSettore extends BaseDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The codice. */
	private String codice;
	/** The descrizione. */
	private String descrizione;
	private Boolean flagUtilizzabile;
    private Boolean flagDirezione;
	private Ente ente;
	private Integer posizione;


	/** Default constructor */
	public TipoSettore() {}

	/**
	 * Constructor
	 * @param id the id
	 */
	public TipoSettore(Integer id) {
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
	 * @return the flagUtilizzabile
	 */
	public Boolean getFlagUtilizzabile() {
		return flagUtilizzabile;
	}

	/**
	 * @param flagUtilizzabile the flagUtilizzabile to set
	 */
	public void setFlagUtilizzabile(Boolean flagUtilizzabile) {
		this.flagUtilizzabile = flagUtilizzabile;
	}

	/**
	 * @return the flagDirezione
	 */
	public Boolean getFlagDirezione() {
		return flagDirezione;
	}

	/**
	 * @param flagDirezione the flagDirezione to set
	 */
	public void setFlagDirezione(Boolean flagDirezione) {
		this.flagDirezione = flagDirezione;
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

	/**
	 * @return the posizione
	 */
	public Integer getPosizione() {
		return posizione;
	}

	/**
	 * @param posizione the posizione to set
	 */
	public void setPosizione(Integer posizione) {
		this.posizione = posizione;
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append("TipoSettore [codice=").append(codice)
			.append(", descrizione=").append(descrizione)
			.append(", id=").append(id)
			.append("]")
			.toString();
	}

}
