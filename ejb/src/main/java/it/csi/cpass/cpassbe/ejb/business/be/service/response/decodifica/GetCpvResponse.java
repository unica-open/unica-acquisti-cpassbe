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
import it.csi.cpass.cpassbe.lib.dto.Cpv;

/**
 * The Class GetCpvResponse.
 */
public class GetCpvResponse extends BaseGetResponse<List<Cpv>> {

	/** The lingue. */
	private List<Cpv> cpvs = new ArrayList<>();

	/**
	 * @return the cpvs
	 */
	public List<Cpv> getCpvs() {
		return cpvs;
	}

	/**
	 * @param cpvs the cpvs to set
	 */
	public void setCpvs(List<Cpv> cpvs) {
		this.cpvs = cpvs;
	}

	@Override
	protected List<Cpv> getEntity() {
		return cpvs;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GetCpvResponse [cpvs=").append(cpvs).append(", apiErrors=").append(getApiErrors()).append("]");
		return builder.toString();
	}

}
