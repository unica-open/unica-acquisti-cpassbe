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


public class SettoreStorico extends BaseDto<Integer> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String note;

	private String settoreCodiceAttuale;

	private String settoreCodiceStorico;

	private Settore settoreAttuale;

	private Settore settoreStorico;

	private Date dataValiditaInizio;

	private Date dataValiditaFine;


	private Ente ente;

	public SettoreStorico() {
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getSettoreCodiceAttuale() {
		return this.settoreCodiceAttuale;
	}

	public void setSettoreCodiceAttuale(String settoreCodiceAttuale) {
		this.settoreCodiceAttuale = settoreCodiceAttuale;
	}

	public String getSettoreCodiceStorico() {
		return this.settoreCodiceStorico;
	}

	public void setSettoreCodiceStorico(String settoreCodiceStorico) {
		this.settoreCodiceStorico = settoreCodiceStorico;
	}



	/**
	 * @return the settoreAttuale
	 */
	public Settore getSettoreAttuale() {
		return settoreAttuale;
	}

	/**
	 * @param settoreAttuale the settoreAttuale to set
	 */
	public void setSettoreAttuale(Settore settoreAttuale) {
		this.settoreAttuale = settoreAttuale;
	}

	/**
	 * @return the settoreStorico
	 */
	public Settore getSettoreStorico() {
		return settoreStorico;
	}

	/**
	 * @param settoreStorico the settoreStorico to set
	 */
	public void setSettoreStorico(Settore settoreStorico) {
		this.settoreStorico = settoreStorico;
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


}
