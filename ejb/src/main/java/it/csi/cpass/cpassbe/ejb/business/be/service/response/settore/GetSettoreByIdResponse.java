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

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.Settore;

/**
 * Response for reading Intervento by its id.
 */
public class GetSettoreByIdResponse extends BaseGetResponse<Settore> {

	private Settore settore;

	/**
	 * @return the Settore
	 */
	public Settore getSettore() {
		return settore;
	}

	/**
	 * @param intervento the Settore to set
	 */
	public void setSettore(Settore settore) {
		this.settore = settore;
	}

	@Override
	protected Settore getEntity() {
		return settore;
	}

}
