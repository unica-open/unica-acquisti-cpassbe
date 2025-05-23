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
import it.csi.cpass.cpassbe.lib.dto.pba.SettoreInterventi;

/**
 * The Class GetSettoreInterventiResponse.
 */
public class GetSettoreInterventiResponse extends BaseGetResponse<List<SettoreInterventi>> {

	/** The lingue. */
	private List<SettoreInterventi> settoreInterventis = new ArrayList<>();

	/**
	 * @return the settoreInterventis
	 */
	public List<SettoreInterventi> getSettoreInterventis() {
		return settoreInterventis;
	}

	/**
	 * @param settoreInterventis the settoreInterventis to set
	 */
	public void setSettoreInterventis(List<SettoreInterventi> settoreInterventis) {
		this.settoreInterventis = settoreInterventis;
	}

	@Override
	protected List<SettoreInterventi> getEntity() {
		return settoreInterventis;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("GetSettoreInterventiResponse [settoreInterventis=").append(settoreInterventis).append(", apiErrors=").append(getApiErrors()).append("]");
		return builder.toString();
	}

}
