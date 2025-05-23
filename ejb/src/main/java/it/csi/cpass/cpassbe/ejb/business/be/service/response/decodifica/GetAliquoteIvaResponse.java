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
import it.csi.cpass.cpassbe.lib.dto.AliquoteIva;

/**
 * The Class GetAliquoteIvaResponse.
 */
public class GetAliquoteIvaResponse extends BaseGetResponse<List<AliquoteIva>> {

	/** The lingue. */
	private List<AliquoteIva> aliquoteIva = new ArrayList<>();

	/**
	 * @return the aliquoteIva
	 */
	public List<AliquoteIva> getAliquoteIva() {
		return aliquoteIva;
	}


	public void setAliquoteIva(List<AliquoteIva> aliquoteIva) {
		this.aliquoteIva = aliquoteIva;
	}

	@Override
	protected List<AliquoteIva> getEntity() {
		return aliquoteIva;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("GetAliquoteIvaResponse [AliquoteIva=").append(aliquoteIva).append(", apiErrors=").append(getApiErrors()).append("]");
		return builder.toString();
	}

}
