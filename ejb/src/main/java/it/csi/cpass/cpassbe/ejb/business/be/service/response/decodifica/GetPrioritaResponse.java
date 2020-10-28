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
import it.csi.cpass.cpassbe.lib.dto.pba.Priorita;

/**
 * The Class GetPrioritaResponse.
 */
public class GetPrioritaResponse extends BaseGetResponse<List<Priorita>> {

	/** The lingue. */
	private List<Priorita> prioritas = new ArrayList<>();

	/**
	 * @return the prioritas
	 */
	public List<Priorita> getPrioritas() {
		return prioritas;
	}

	/**
	 * @param prioritas the prioritas to set
	 */
	public void setPrioritas(List<Priorita> prioritas) {
		this.prioritas = prioritas;
	}

	@Override
	protected List<Priorita> getEntity() {
		return prioritas;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GetPrioritaResponse [prioritas=").append(prioritas).append(", apiErrors=").append(getApiErrors()).append("]");
		return builder.toString();
	}

}
