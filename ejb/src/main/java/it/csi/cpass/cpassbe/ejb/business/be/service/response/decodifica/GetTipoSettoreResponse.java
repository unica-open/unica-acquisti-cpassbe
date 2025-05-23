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
import it.csi.cpass.cpassbe.lib.dto.TipoSettore;

/**
 * The Class GetTipoOrdineResponse.
 */
public class GetTipoSettoreResponse extends BaseGetResponse<List<TipoSettore>> {

	/** The lingue. */
	private List<TipoSettore> tipoSettores = new ArrayList<>();

	/**
	 * @return the tipoOrdines
	 */
	public List<TipoSettore> getTipoSettores() {
		return tipoSettores;
	}

	/**
	 * @param tipoOrdines the tipoOrdines to set
	 */
	public void setTipoSettores(List<TipoSettore> tipoSettores) {
		this.tipoSettores = tipoSettores;
	}

	@Override
	protected List<TipoSettore> getEntity() {
		return tipoSettores;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("GetTipoOrdineResponse [tipoOrdines=").append(tipoSettores).append(", apiErrors=").append(getApiErrors()).append("]");
		return builder.toString();
	}

}
