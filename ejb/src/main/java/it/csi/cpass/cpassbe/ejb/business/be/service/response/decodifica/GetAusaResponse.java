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
import it.csi.cpass.cpassbe.lib.dto.pba.Ausa;

/**
 * The Class GetAusaResponse.
 */
public class GetAusaResponse extends BaseGetResponse<List<Ausa>> {

	/** The lingue. */
	private List<Ausa> ausas = new ArrayList<>();

	/**
	 * @return the ausas
	 */
	public List<Ausa> getAusas() {
		return ausas;
	}

	/**
	 * @param ausas the ausas to set
	 */
	public void setAusas(List<Ausa> ausas) {
		this.ausas = ausas;
	}

	@Override
	protected List<Ausa> getEntity() {
		return ausas;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GetAusaResponse [ausas=").append(ausas).append(", apiErrors=").append(getApiErrors()).append("]");
		return builder.toString();
	}

}
