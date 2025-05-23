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
import it.csi.cpass.cpassbe.lib.dto.ProvvedimentoTipo;

/**
 * The Class ProvvedimentoTipo.
 */
public class GetProvvedimentoTipoResponse extends BaseGetResponse<List<ProvvedimentoTipo>> {

	/** The lingue. */
	private List<ProvvedimentoTipo> um = new ArrayList<>();

	/**
	 * @return the ProvvedimentoTipo
	 */
	public List<ProvvedimentoTipo> getUnitaMisura() {
		return um;
	}


	public void setUnitaMisura(List<ProvvedimentoTipo> um) {
		this.um = um;
	}

	@Override
	protected List<ProvvedimentoTipo> getEntity() {
		return um;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("ProvvedimentoTipo [UnitaMisura=").append(um).append(", apiErrors=").append(getApiErrors()).append("]");
		return builder.toString();
	}

}
