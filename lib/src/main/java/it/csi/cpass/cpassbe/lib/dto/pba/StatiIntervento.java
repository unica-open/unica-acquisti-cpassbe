/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2023 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2023 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto.pba;

import java.io.Serializable;
import java.util.Date;

import it.csi.cpass.cpassbe.lib.dto.BaseDto;
import it.csi.cpass.cpassbe.lib.dto.Utente;


public class StatiIntervento extends BaseDto<Integer> implements Serializable {
	private static final long serialVersionUID = 1L;

	private Date data;

	private String motivazione;

	private String stato;

	private Intervento intervento;

	private Utente utente;

	public StatiIntervento() {
	}

	/**
	 * @return the data
	 */
	public Date getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Date data) {
		this.data = data;
	}

	/**
	 * @return the motivazione
	 */
	public String getMotivazione() {
		return motivazione;
	}

	/**
	 * @param motivazione the motivazione to set
	 */
	public void setMotivazione(String motivazione) {
		this.motivazione = motivazione;
	}

	/**
	 * @return the stato
	 */
	public String getStato() {
		return stato;
	}

	/**
	 * @param stato the stato to set
	 */
	public void setStato(String stato) {
		this.stato = stato;
	}

	/**
	 * @return the intervento
	 */
	public Intervento getIntervento() {
		return intervento;
	}

	/**
	 * @param intervento the intervento to set
	 */
	public void setIntervento(Intervento intervento) {
		this.intervento = intervento;
	}

	/**
	 * @return the utente
	 */
	public Utente getUtente() {
		return utente;
	}

	/**
	 * @param utente the utente to set
	 */
	public void setUtente(Utente utente) {
		this.utente = utente;
	}

}
