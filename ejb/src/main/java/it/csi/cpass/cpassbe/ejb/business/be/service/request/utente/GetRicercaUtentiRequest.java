/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.service.request.utente;


import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BasePagedRequest;
import it.csi.cpass.cpassbe.lib.dto.Ruolo;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Utente;

/**
 * Request for reading the Interventos paginated
 */
public class GetRicercaUtentiRequest extends BasePagedRequest {

	private final Boolean dirigente;
	private Ruolo ruolo;
	private final Settore settore;
	private final Utente utente;
	private final Boolean checkDataValiditaFineRUteSett;

	/**
	 * Constructor
	 * @param size the size
	 * @param page the page
	 * @param sort the sort
	 * @param direction the direction
	 * @param intervento the intervento
	 */

	public GetRicercaUtentiRequest(Integer page, Integer size, String sort, String direction, Boolean dirigente,Ruolo ruolo,Settore settore,Utente utente, Boolean checkDataValiditaFineRUteSett) {
		super(size, page, sort, direction);
		this.dirigente = dirigente;
		this.ruolo = ruolo != null ? ruolo : new Ruolo();
		this.settore = settore != null ? settore : new Settore();
		this.utente = utente != null ? utente : new Utente();
		this.checkDataValiditaFineRUteSett = checkDataValiditaFineRUteSett != null ? checkDataValiditaFineRUteSett : Boolean.FALSE;
	}

	/**
	 * @return the dirigente
	 */
	public Boolean getDirigente() {
		return dirigente;
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
	 * @return the utente
	 */
	public Utente getUtente() {
		return utente;
	}

	/**
	 * @return the settore
	 */
	public Settore getSettore() {
		return settore;
	}

	/**
	 * @return the checkDataValiditaFineRUteSett
	 */
	public Boolean getCheckDataValiditaFineRUteSett() {
		return checkDataValiditaFineRUteSett;
	}



}
