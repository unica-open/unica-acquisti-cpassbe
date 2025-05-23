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

/**
 * The Class RuoloUtenteSettore.
 */
public class RuoloUtenteSettore extends BaseDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private Ruolo ruolo;

	private UtenteSettore utenteSettore;

	private Date dataValiditaFine;

	private Date dataValiditaInizio;

	//private Boolean valido;
	/**
	 * Constructor
	 * @param id the id
	 */
	public RuoloUtenteSettore(Integer id) {
		super(id);
	}

	public RuoloUtenteSettore() {
	}

	/**
	 * @return the ruolo
	 */
	public Ruolo getRuolo() {
		return ruolo;
	}


	/**
	 * @param ruolo the ruolo to set
	 */
	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}


	/**
	 * @return the utenteSettore
	 */
	public UtenteSettore getUtenteSettore() {
		return utenteSettore;
	}


	/**
	 * @param utenteSettore the utenteSettore to set
	 */
	public void setUtenteSettore(UtenteSettore utenteSettore) {
		this.utenteSettore = utenteSettore;
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


	/**
	 * @return the valido
	 */
	public Boolean getValido() {
		if(dataValiditaFine != null && new Date().compareTo(dataValiditaFine) >= 0) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}




	@Override
	public String toString() {
		return new StringBuilder()
			.append("SettoreRuoliPermessi [ruolo=").append(ruolo)
			.append(", utenteSettore=").append(utenteSettore)
			.append("]")
			.toString();
	}

}
