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
 * Response for get utente by settore ruolo
 *
 */
public class GetUtenteBySettoreRuoloResponse extends BaseGetResponse<List<Utente>> {
	private List<Utente> utenti = new ArrayList<>();

	/**
	 * @return the utente
	 */
	public List<Utente> getUtente() {
		return utenti;
	}

	/**
	 * @param utenti the utenti to set
	 */
	public void setUtente(List<Utente> utenti) {
		this.utenti = utenti != null ? utenti : new ArrayList<>();
	}

	@Override
	protected List<Utente> getEntity() {
		return utenti;
	}
}
