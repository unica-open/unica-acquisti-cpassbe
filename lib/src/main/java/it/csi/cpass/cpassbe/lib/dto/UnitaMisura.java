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
import java.util.Date;


public class UnitaMisura  extends BaseDto<Integer> implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/** Default constructor */
	public UnitaMisura() {}

	/**
	 * Constructor
	 * @param id the id
	 */
	public UnitaMisura(Integer id) {
		super(id);
	}

	private String codice;

	private String descrizione;

	private String ambitoUtilizzo;

	/** The data modifica. */
	private Date dataValiditaFine;

	/** The data modifica. */
	private Date dataValiditaInizio;

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
	 * @return the ambitoUtilizzo
	 */
	public String getAmbitoUtilizzo() {
		return ambitoUtilizzo;
	}

	/**
	 * @param ambitoUtilizzo the ambitoUtilizzo to set
	 */
	public void setAmbitoUtilizzo(String ambitoUtilizzo) {
		this.ambitoUtilizzo = ambitoUtilizzo;
	}


	/**
	 * @return the dataValiditaFine
	 */
	public Date getDataValiditaFine() {
		return dataValiditaFine;
	}

	/**
	 * @param dataValiditaFine the dataValiditaFine to set
	 */
	public void setDataValiditaFine(Date dataValiditaFine) {
		this.dataValiditaFine = dataValiditaFine;
	}

	/**
	 * @return the dataValiditaInizio
	 */
	public Date getDataValiditaInizio() {
		return dataValiditaInizio;
	}

	/**
	 * @param dataValiditaInizio the dataValiditaInizio to set
	 */
	public void setDataValiditaInizio(Date dataValiditaInizio) {
		this.dataValiditaInizio = dataValiditaInizio;
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append("UnitaMisura [codice=").append(codice)
			.append(", descrizione=").append(descrizione)
			.append(", ambitoUtilizzo=").append(ambitoUtilizzo)
			.append(", id=").append(id)
			.append("]")
			.toString();
	}
}
