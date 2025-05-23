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
import it.csi.cpass.cpassbe.lib.dto.Stato;

/**
 * The Class GetStatoByTipoResponse.
 */
public class GetStatoByTipoResponse extends BaseGetResponse<List<Stato>> {

	/** The Stato. */
	private List<Stato> statos = new ArrayList<>();

	/**
	 * @return the statos
	 */
	public List<Stato> getStatos() {
		return statos;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setStatos(List<Stato> statos) {
		this.statos = statos;
	}

	@Override
	protected List<Stato> getEntity() {
		return statos;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("GetStatoByTipoResponse [statos=").append(statos).append(", apiErrors=").append(getApiErrors()).append("]");
		return builder.toString();
	}

}
