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
import it.csi.cpass.cpassbe.lib.dto.ord.TipoProcedura;

/**
 * The Class GetTipoProceduraResponse.
 */
public class GetTipoProceduraResponse extends BaseGetResponse<List<TipoProcedura>> {

	/** The lingue. */
	private List<TipoProcedura> tipoProceduras = new ArrayList<>();

	/**
	 * @return the tipoProceduras
	 */
	public List<TipoProcedura> getTipoProceduras() {
		return tipoProceduras;
	}

	/**
	 * @param tipoProceduras the tipoProceduras to set
	 */
	public void setTipoProceduras(List<TipoProcedura> tipoProceduras) {
		this.tipoProceduras = tipoProceduras;
	}

	@Override
	protected List<TipoProcedura> getEntity() {
		return tipoProceduras;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GetTipoProceduraResponse [tipoProceduras=").append(tipoProceduras).append(", apiErrors=").append(getApiErrors()).append("]");
		return builder.toString();
	}

}
