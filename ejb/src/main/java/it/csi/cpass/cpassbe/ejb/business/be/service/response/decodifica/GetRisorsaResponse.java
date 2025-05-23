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
import it.csi.cpass.cpassbe.lib.dto.pba.Risorsa;

/**
 * The Class GetRisorsaResponse.
 */
public class GetRisorsaResponse extends BaseGetResponse<List<Risorsa>> {

	/** The lingue. */
	private List<Risorsa> risorsas = new ArrayList<>();

	/**
	 * @return the risorsas
	 */
	public List<Risorsa> getRisorsas() {
		return risorsas;
	}

	/**
	 * @param risorsas the risorsas to set
	 */
	public void setRisorsas(List<Risorsa> risorsas) {
		this.risorsas = risorsas;
	}

	@Override
	protected List<Risorsa> getEntity() {
		return risorsas;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("GetRisorsaResponse [risorsas=").append(risorsas).append(", apiErrors=").append(getApiErrors()).append("]");
		return builder.toString();
	}

}
