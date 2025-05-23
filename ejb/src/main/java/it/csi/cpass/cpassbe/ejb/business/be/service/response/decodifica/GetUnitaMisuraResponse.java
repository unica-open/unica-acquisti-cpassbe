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
import it.csi.cpass.cpassbe.lib.dto.UnitaMisura;

/**
 * The Class GetUnitaMisuraResponse.
 */
public class GetUnitaMisuraResponse extends BaseGetResponse<List<UnitaMisura>> {

	/** The lingue. */
	private List<UnitaMisura> um = new ArrayList<>();

	/**
	 * @return the UnitaMisura
	 */
	public List<UnitaMisura> getUnitaMisura() {
		return um;
	}


	public void setUnitaMisura(List<UnitaMisura> um) {
		this.um = um;
	}

	@Override
	protected List<UnitaMisura> getEntity() {
		return um;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("GetAliquoteIvaResponse [UnitaMisura=").append(um).append(", apiErrors=").append(getApiErrors()).append("]");
		return builder.toString();
	}

}
