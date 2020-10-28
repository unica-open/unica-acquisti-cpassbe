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
import it.csi.cpass.cpassbe.lib.dto.ord.StatoElOrdine;

/**
 * The Class GetStatoElOrdineByTipoResponse.
 */
public class GetStatoElOrdineByTipoResponse extends BaseGetResponse<List<StatoElOrdine>> {

	/** The lingue. */
	private List<StatoElOrdine> statoElOrdines = new ArrayList<>();

	/**
	 * @return the statoElOrdines
	 */
	public List<StatoElOrdine> getStatoElOrdines() {
		return statoElOrdines;
	}

	/**
	 * @param tipoProceduras the tipoProceduras to set
	 */
	public void setStatoElOrdines(List<StatoElOrdine> statoElOrdines) {
		this.statoElOrdines = statoElOrdines;
	}

	@Override
	protected List<StatoElOrdine> getEntity() {
		return statoElOrdines;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GetStatoElOrdineResponse [statoElOrdines=").append(statoElOrdines).append(", apiErrors=").append(getApiErrors()).append("]");
		return builder.toString();
	}

}
