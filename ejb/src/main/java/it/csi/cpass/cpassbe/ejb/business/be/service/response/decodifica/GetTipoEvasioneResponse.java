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
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.TipoEvasione;

/**
 * The Class GetTipoOrdineResponse.
 */
public class GetTipoEvasioneResponse extends BaseGetResponse<List<TipoEvasione>> {

	/** The lingue. */
	private List<TipoEvasione> tipoEvasiones = new ArrayList<>();

	/**
	 * @return the tipoOrdines
	 */
	public List<TipoEvasione> getTipoEvasiones() {
		return tipoEvasiones;
	}

	/**
	 * @param tipoOrdines the tipoOrdines to set
	 */
	public void setTipoEvasiones(List<TipoEvasione> tipoEvasiones) {
		this.tipoEvasiones = tipoEvasiones;
	}

	@Override
	protected List<TipoEvasione> getEntity() {
		return tipoEvasiones;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GetTipoEvasioneResponse [tipoOrdines=").append(tipoEvasiones).append(", apiErrors=").append(getApiErrors()).append("]");
		return builder.toString();
	}

}
