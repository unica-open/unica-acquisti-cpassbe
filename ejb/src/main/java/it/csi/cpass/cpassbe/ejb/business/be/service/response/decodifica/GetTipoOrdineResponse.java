/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica;

import java.util.ArrayList;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.ord.TipoOrdine;

/**
 * The Class GetTipoOrdineResponse.
 */
public class GetTipoOrdineResponse extends BaseGetResponse<List<TipoOrdine>> {

	/** The lingue. */
	private List<TipoOrdine> tipoOrdines = new ArrayList<>();

	/**
	 * @return the tipoOrdines
	 */
	public List<TipoOrdine> getTipoOrdines() {
		return tipoOrdines;
	}

	/**
	 * @param tipoOrdines the tipoOrdines to set
	 */
	public void setTipoOrdines(List<TipoOrdine> tipoOrdines) {
		this.tipoOrdines = tipoOrdines;
	}

	@Override
	protected List<TipoOrdine> getEntity() {
		return tipoOrdines;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GetTipoOrdineResponse [tipoOrdines=").append(tipoOrdines).append(", apiErrors=").append(getApiErrors()).append("]");
		return builder.toString();
	}

}
