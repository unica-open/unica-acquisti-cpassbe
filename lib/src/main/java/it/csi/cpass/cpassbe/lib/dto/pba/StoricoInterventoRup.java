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
import java.util.Date;

import it.csi.cpass.cpassbe.lib.dto.BaseDto;
import it.csi.cpass.cpassbe.lib.dto.Utente;


/**
 * The Class InterventoStoricoRup.
 */
public class StoricoInterventoRup extends BaseDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private Intervento intervento;

	private Utente utenteRup;

	private Utente utente;

	private Date dataStoricizzazione;


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

	/**
	 * @return the dataStoricizzazione
	 */
	public Date getDataStoricizzazione() {
		return dataStoricizzazione;
	}

	/**
	 * @param dataStoricizzazione the dataStoricizzazione to set
	 */
	public void setDataStoricizzazione(Date dataStoricizzazione) {
		this.dataStoricizzazione = dataStoricizzazione;
	}


}
