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
import it.csi.cpass.cpassbe.lib.dto.Ufficio;

/**
 * The Class GetUfficioResponse.
 */
public class GetUfficiBySettoreResponse extends BaseGetResponse<List<Ufficio>> {

	/** The lingue. */
	private List<Ufficio> ufficios = new ArrayList<>();

	/**
	 * @return the ufficios
	 */
	public List<Ufficio> getUfficios() {
		return ufficios;
	}

	/**
	 * @param ufficios the ufficios to set
	 */
	public void setUfficios(List<Ufficio> ufficios) {
		this.ufficios = ufficios;
	}

	@Override
	protected List<Ufficio> getEntity() {
		return ufficios;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GetUfficioResponse [ufficios=").append(ufficios).append(", apiErrors=").append(getApiErrors()).append("]");
		return builder.toString();
	}

}
