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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica;

import java.util.ArrayList;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.ord.StatoNso;

/**
 * The Class GetStatoNsoByTipoResponse.
 */
public class GetStatoNsoByTipoResponse extends BaseGetResponse<List<StatoNso>> {

	/** The Stato. */
	private List<StatoNso> statoNsos = new ArrayList<>();

	/**
	 * @return the statos
	 */
	public List<StatoNso> getStatoNsos() {
		return statoNsos;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setStatoNsos(List<StatoNso> statoNsos) {
		this.statoNsos = statoNsos;
	}

	@Override
	protected List<StatoNso> getEntity() {
		return statoNsos;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("GetStatoNsoByTipoResponse [statoNsos=").append(statoNsos).append(", apiErrors=").append(getApiErrors()).append("]");
		return builder.toString();
	}

}
