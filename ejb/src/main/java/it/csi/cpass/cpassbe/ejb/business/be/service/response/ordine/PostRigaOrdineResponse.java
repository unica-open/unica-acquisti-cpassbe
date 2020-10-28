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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine;

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BasePostResponse;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;

public class PostRigaOrdineResponse extends BasePostResponse<UUID, RigaOrdine> {
	
	private RigaOrdine rigaOrdine;

	/**
	 * @return the testataOrdine
	 */
	public RigaOrdine getRigaOrdine() {
		return rigaOrdine;
	}

	/**
	 * @param testataOrdine the testataOrdine to set
	 */
	public void setRigaOrdine(RigaOrdine rigaOrdine) {
		this.rigaOrdine = rigaOrdine;
	}

	@Override
	protected RigaOrdine getEntity() {
		return rigaOrdine;
	}

	@Override
	protected String getBaseUri() {
		return "rigaOrdine";
	}

}
