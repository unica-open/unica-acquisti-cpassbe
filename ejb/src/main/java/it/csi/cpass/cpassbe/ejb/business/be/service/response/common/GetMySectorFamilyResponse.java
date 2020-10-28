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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.common;

import java.util.ArrayList;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.Settore;

/**
 * The Class GetUfficioResponse.
 */
public class GetMySectorFamilyResponse extends BaseGetResponse<List<Settore>> {

	/** The Settore. */
	private List<Settore> settores = new ArrayList<>();

	/**
	 * @return the settores
	 */
	public List<Settore> getSettores() {
		return settores;
	}

	/**
	 * @param ufficios the ufficios to set
	 */
	public void setSettores(List<Settore> settores) {
		this.settores = settores;
	}

	@Override
	protected List<Settore> getEntity() {
		return settores;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GetSettoreTreeByEnteResponse [settores=").append(settores).append(", apiErrors=").append(getApiErrors()).append("]");
		return builder.toString();
	}



}
