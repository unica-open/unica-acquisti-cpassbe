/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - WAR submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.web.dto;

import it.csi.cpass.cpassbe.lib.dto.Ruolo;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Utente;

/**
 * Ricerca Utenti
 */
public class RicercaUtenti {

	private Utente utente;
	private Ruolo ruolo;
	private Settore settore;
	Boolean dirigente;
	Boolean checkDataValiditaFineRUteSett;

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
	 * @return the settore
	 */
	public Settore getSettore() {
		return settore;
	}
	/**
	 * @param settore the settore to set
	 */
	public void setSettore(Settore settore) {
		this.settore = settore;
	}
	/**
	 * @return the dirigente
	 */
	public Boolean getDirigente() {
		return dirigente;
	}
	/**
	 * @param dirigente the dirigente to set
	 */
	public void setDirigente(Boolean dirigente) {
		this.dirigente = dirigente;
	}
	/**
	 * @return the checkDataValiditaFineRUteSett
	 */
	public Boolean getCheckDataValiditaFineRUteSett() {
		return checkDataValiditaFineRUteSett;
	}
	/**
	 * @param checkDataValiditaFineRUteSett the checkDataValiditaFineRUteSett to set
	 */
	public void setCheckDataValiditaFineRUteSett(Boolean checkDataValiditaFineRUteSett) {
		this.checkDataValiditaFineRUteSett = checkDataValiditaFineRUteSett;
	}


}
