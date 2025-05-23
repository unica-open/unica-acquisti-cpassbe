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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.utente;

import java.util.ArrayList;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.Utente;

/**
 * Response for reading Utente by its id.
 */
public class GetRicercaUtentiNoPageResponse extends BaseGetResponse<List<Utente>> {

	private List<Utente> utenti = new ArrayList<>();

	/**
	 * @return the utenti
	 */
	public List<Utente> getUtenti() {
		return utenti;
	}

	/**
	 * @param utenti the utenti to set
	 */
	public void setUtenti(List<Utente> utenti) {
		this.utenti = utenti;
	}

	@Override
	protected List<Utente> getEntity() {
		// TODO Auto-generated method stub
		return utenti;
	}



}
