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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.elaborazione;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;

public class GetElaborazioniByEntityResponse extends BaseGetResponse<List<Elaborazione>> {

	private List<Elaborazione> elaborazioni;

	/**
	 * @return the elaborazioni
	 */
	public List<Elaborazione> getElaborazioni() {
		return elaborazioni;
	}

	/**
	 * @param elaborazioni the elaborazioni to set
	 */
	public void setElaborazioni(List<Elaborazione> elaborazioni) {
		this.elaborazioni = elaborazioni;
	}

	@Override
	protected List<Elaborazione> getEntity() {
		return elaborazioni;
	}

}
