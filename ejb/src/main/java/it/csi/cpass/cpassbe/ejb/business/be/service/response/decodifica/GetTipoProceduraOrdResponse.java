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
import it.csi.cpass.cpassbe.lib.dto.ord.TipoProceduraOrd;

/**
 * The Class GetTipoProceduraResponse.
 */
public class GetTipoProceduraOrdResponse extends BaseGetResponse<List<TipoProceduraOrd>> {

	/** The lingue. */
	private List<TipoProceduraOrd> tipoProceduraOrds = new ArrayList<>();

	/**
	 * @return the tipoProceduras
	 */
	public List<TipoProceduraOrd> getTipoProceduras() {
		return tipoProceduraOrds;
	}

	/**
	 * @param tipoProceduraOrds the tipoProceduras to set
	 */
	public void setTipoProceduras(List<TipoProceduraOrd> tipoProceduraOrds) {
		this.tipoProceduraOrds = tipoProceduraOrds;
	}

	@Override
	protected List<TipoProceduraOrd> getEntity() {
		return tipoProceduraOrds;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("GetTipoProceduraResponse [tipoProceduras=").append(tipoProceduraOrds).append(", apiErrors=").append(getApiErrors()).append("]");
		return builder.toString();
	}

}
