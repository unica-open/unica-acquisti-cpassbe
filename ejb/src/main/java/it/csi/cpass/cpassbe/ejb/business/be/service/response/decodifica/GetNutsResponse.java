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
import it.csi.cpass.cpassbe.lib.dto.pba.Nuts;

/**
 * The Class GetNutsResponse.
 */
public class GetNutsResponse extends BaseGetResponse<List<Nuts>> {

	/** The lingue. */
	private List<Nuts> nutss = new ArrayList<>();

	/**
	 * @return the nutss
	 */
	public List<Nuts> getNutss() {
		return nutss;
	}

	/**
	 * @param nutss the nutss to set
	 */
	public void setNutss(List<Nuts> nutss) {
		this.nutss = nutss;
	}

	@Override
	protected List<Nuts> getEntity() {
		return nutss;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GetNutsResponse [nutss=").append(nutss).append(", apiErrors=").append(getApiErrors()).append("]");
		return builder.toString();
	}

}
