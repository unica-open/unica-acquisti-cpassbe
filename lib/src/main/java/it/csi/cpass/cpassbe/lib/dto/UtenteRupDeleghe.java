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
 * The Class InterventoStoricoRup.
 */
public class UtenteRupDeleghe extends BaseDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private Utente utenteRup;

	private Utente utenteRupDelegato;

	private Date dataValiditaFine;

	private Date dataValiditaInizio;

	/**
	 * @return the utenteRup
	 */
	public Utente getUtenteRup() {
		return utenteRup;
	}

	/**
	 * @param utenteRup the utenteRup to set
	 */
	public void setUtenteRup(Utente utenteRup) {
		this.utenteRup = utenteRup;
	}

	/**
	 * @return the utenteRupDelegato
	 */
	public Utente getUtenteRupDelegato() {
		return utenteRupDelegato;
	}

	/**
	 * @param utenteRupDelegato the utenteRupDelegato to set
	 */
	public void setUtenteRupDelegato(Utente utenteRupDelegato) {
		this.utenteRupDelegato = utenteRupDelegato;
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


}
