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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.settore;

import java.util.ArrayList;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.Cdc;

/**
 * Response for reading Intervento by its id.
 */
public class GetCdcValidiResponse extends BaseGetResponse<List<Cdc>> {

	/** The lingue. */
	private List<Cdc> listaCdc = new ArrayList<>();

	/**
	 * @return the listaCdc
	 */
	public List<Cdc> getListaCdc() {
		return listaCdc;
	}

	/**
	 * @param listaCdc the listaCdc to set
	 */
	public void setListaCdc(List<Cdc> listaCdc) {
		this.listaCdc = listaCdc;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("GetUfficioResponse [ufficios=").append(listaCdc).append(", apiErrors=").append(getApiErrors()).append("]");
		return builder.toString();
	}

	@Override
	protected List<Cdc> getEntity() {
		return listaCdc;
	}

}
