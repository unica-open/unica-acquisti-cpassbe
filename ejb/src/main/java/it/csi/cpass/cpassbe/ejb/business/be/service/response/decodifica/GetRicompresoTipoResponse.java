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
import it.csi.cpass.cpassbe.lib.dto.pba.RicompresoTipo;

/**
 * The Class GetRicompresoTipoResponse.
 */
public class GetRicompresoTipoResponse extends BaseGetResponse<List<RicompresoTipo>> {

	/** The lingue. */
	private List<RicompresoTipo> ricompresoTipi = new ArrayList<>();

	/**
	 * @return the prioritas
	 */
	public List<RicompresoTipo> getRicompresoTipos() {
		return ricompresoTipi;
	}

	/**
	 * @param ricompresoTipi the prioritas to set
	 */
	public void setRicompresoTipos(List<RicompresoTipo> ricompresoTipi) {
		this.ricompresoTipi = ricompresoTipi;
	}

	@Override
	protected List<RicompresoTipo> getEntity() {
		return ricompresoTipi;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("GetRicompresoTipoResponse [ricompresoTipi=").append(ricompresoTipi).append(", apiErrors=").append(getApiErrors()).append("]");
		return builder.toString();
	}

}
