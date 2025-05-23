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

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BasePostResponse;
import it.csi.cpass.cpassbe.lib.dto.Settore;

/**
 * Response for reading settore by its id.
 */
//public class PutSettoreResponse extends BasePutResponse {
public class PutSettoreResponse extends BasePostResponse<UUID, Settore> {

	private Settore settore;


	/**
	 * @return the settore
	 */
	public Settore getSettore() {
		return settore;
	}

	/**
	 * @param settore the settore to set
	 */
	public void setSettore(Settore settore) {
		this.settore = settore;
	}

	@Override
	protected String getBaseUri() {
		// TODO Auto-generated method stub
		return "settore";
	}

	@Override
	protected Settore getEntity() {
		// TODO Auto-generated method stub
		return settore;
	}

}
