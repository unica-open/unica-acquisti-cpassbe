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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;

public class PostControlliInvioInFirmaResponse extends BaseGetResponse<Boolean> {

	private Boolean controllo;


	/**
	 * @return the controllo
	 */
	public Boolean getControllo() {
		return controllo;
	}


	/**
	 * @param controllo the controllo to set
	 */
	public void setControllo(Boolean controllo) {
		this.controllo = controllo;
	}


	@Override
	protected Boolean getEntity() {
		return controllo;
	}
}
