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
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.CausaleSospensioneEvasione;

public class GetAllCausaleSospensioneEvasioneValideResponse extends BaseGetResponse<List<CausaleSospensioneEvasione>> {

	/** The causaleSospensioneEvasiones. */
	private List<CausaleSospensioneEvasione> causaleSospensioneEvasione = new ArrayList<>();

	/**
	 * @return the causaleSospensioneEvasione
	 */
	public List<CausaleSospensioneEvasione> getCausaleSospensioneEvasione() {
		return causaleSospensioneEvasione;
	}

	/**
	 * @param causaleSospensiones the causaleSospensiones to set
	 */
	public void setCausaleSospensioneEvasione(List<CausaleSospensioneEvasione> causaleSospensioneEvasione) {
		this.causaleSospensioneEvasione = causaleSospensioneEvasione;
	}

	@Override
	protected List<CausaleSospensioneEvasione> getEntity() {
		return causaleSospensioneEvasione;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GetAllCausaleSospensioneEvasioneValideRequestResponse [CausaleSospensioneEvasione=").append(causaleSospensioneEvasione).append(", apiErrors=").append(getApiErrors()).append("]");
		return builder.toString();
	}

}
