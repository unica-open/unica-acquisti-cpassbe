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
import it.csi.cpass.cpassbe.lib.dto.Ruolo;

/**
 * Response for get utente by settore ruolo
 *
 */
public class GetRuoliBySettoreResponse extends BaseGetResponse<List<Ruolo>> {
	private List<Ruolo> ruoli = new ArrayList<>();

	/**
	 * Return ruoli
	 * @return
	 */
	public List<Ruolo> getRuoli() {
		return ruoli;
	}

	/**
	 * @param utenti the utenti to set
	 */
	public void setRuoli(List<Ruolo> ruoli) {
		this.ruoli = ruoli != null ? ruoli : new ArrayList<>();
	}

	@Override
	protected List<Ruolo> getEntity() {
		return ruoli;
	}
}
