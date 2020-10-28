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
import it.csi.cpass.cpassbe.lib.dto.pba.AcquistoVariato;

/**
 * The Class GetAcquistiVariatiResponse.
 */
public class GetAcquistiVariatiResponse extends BaseGetResponse<List<AcquistoVariato>> {

	/** The lingue. */
	private List<AcquistoVariato> acquistiVariati = new ArrayList<>();

	/**
	 * @return the acquistiVariati
	 */
	public List<AcquistoVariato> getAcquistiVariati() {
		return acquistiVariati;
	}

	/**
	 * @param acquistiVariati the acquistiVariati to set
	 */
	public void setAcquistiVariati(List<AcquistoVariato> acquistiVariati) {
		this.acquistiVariati = acquistiVariati;
	}

	@Override
	protected List<AcquistoVariato> getEntity() {
		return acquistiVariati;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GetAcquistoVariatoResponse [acquistiVariati=").append(acquistiVariati).append(", apiErrors=").append(getApiErrors()).append("]");
		return builder.toString();
	}

}
