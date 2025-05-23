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
import it.csi.cpass.cpassbe.lib.dto.MotiviEsclusioneCig;

/**
 * The Class GetMotiviEsclusioneCigResponse.
 */
public class GetMotiviEsclusioneCigResponse extends BaseGetResponse<List<MotiviEsclusioneCig>> {

	/** The lingue. */
	private List<MotiviEsclusioneCig> motiviEsclusioneCigs = new ArrayList<>();


	/**
	 * @return the motiviEsclusioneCigs
	 */
	public List<MotiviEsclusioneCig> getMotiviEsclusioneCigs() {
		return motiviEsclusioneCigs;
	}


	/**
	 * @param motiviEsclusioneCigs the motiviEsclusioneCigs to set
	 */
	public void setMotiviEsclusioneCigs(List<MotiviEsclusioneCig> motiviEsclusioneCigs) {
		this.motiviEsclusioneCigs = motiviEsclusioneCigs;
	}


	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("GetMotiviEsclusioneCigResponse [MotiviEsclusioneCig=").append(motiviEsclusioneCigs).append(", apiErrors=").append(getApiErrors()).append("]");
		return builder.toString();
	}


	@Override
	protected List<MotiviEsclusioneCig> getEntity() {
		return motiviEsclusioneCigs;
	}

}
