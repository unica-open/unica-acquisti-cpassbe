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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.pba.InterventoCig;

/**
 * Response for reading Intervento by its id.
 */
public class GetCigByInterventoIdResponse extends BaseGetResponse<List<InterventoCig>> {

	private List<InterventoCig> interventiCig;

	/**
	 * @return the interventiCig
	 */
	public List<InterventoCig> getInterventiCig() {
		return interventiCig;
	}

	/**
	 * @param interventiCig the interventiCig to set
	 */
	public void setInterventiCig(List<InterventoCig> interventiCig) {
		this.interventiCig = interventiCig;
	}

	@Override
	protected List<InterventoCig> getEntity() {
		return interventiCig;
	}



}
