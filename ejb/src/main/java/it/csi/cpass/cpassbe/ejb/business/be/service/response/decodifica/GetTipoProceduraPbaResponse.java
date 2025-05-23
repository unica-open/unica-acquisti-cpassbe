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
import it.csi.cpass.cpassbe.lib.dto.pba.TipoProceduraPba;

/**
 * The Class GetTipoProceduraResponse.
 */
public class GetTipoProceduraPbaResponse extends BaseGetResponse<List<TipoProceduraPba>> {

	/** The lingue. */
	private List<TipoProceduraPba> tipoProceduraPbas = new ArrayList<>();

	/**
	 * @return the tipoProceduras
	 */
	public List<TipoProceduraPba> getTipoProceduras() {
		return tipoProceduraPbas;
	}

	/**
	 * @param tipoProceduraOrds the tipoProceduras to set
	 */
	public void setTipoProceduras(List<TipoProceduraPba> tipoProceduraPbas) {
		this.tipoProceduraPbas = tipoProceduraPbas;
	}

	@Override
	protected List<TipoProceduraPba> getEntity() {
		return tipoProceduraPbas;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("GetTipoProceduraResponse [tipoProceduras=").append(tipoProceduraPbas).append(", apiErrors=").append(getApiErrors()).append("]");
		return builder.toString();
	}

}
