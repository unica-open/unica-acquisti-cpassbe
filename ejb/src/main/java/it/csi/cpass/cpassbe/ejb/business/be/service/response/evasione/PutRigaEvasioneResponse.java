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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione;

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BasePostResponse;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.RigaEvasione;

/**
 * Response for reading RigaEvasione by its id.
 */
public class PutRigaEvasioneResponse extends BasePostResponse<UUID, RigaEvasione> {

	private RigaEvasione rigaEvasione;

	/**
	 * @return the rigaEvasione
	 */
	public RigaEvasione getRigaEvasione() {
		return rigaEvasione;
	}

	/**
	 * @param rigaEvasione the rigaEvasione to set
	 */
	public void setRigaEvasione(RigaEvasione rigaEvasione) {
		this.rigaEvasione = rigaEvasione;
	}

	@Override
	protected RigaEvasione getEntity() {
		return rigaEvasione;
	}

	@Override
	protected String getBaseUri() {
		return "rigaEvasione";
	}

}
