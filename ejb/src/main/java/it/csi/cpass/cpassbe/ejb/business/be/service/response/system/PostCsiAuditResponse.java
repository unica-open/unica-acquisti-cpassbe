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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.system;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;

/**
 * The Class GetComunicazioneResponse.
 */
public class PostCsiAuditResponse extends BaseGetResponse<Boolean> {

	/** The comunicaziones. */
	private Boolean logout;

	@Override
	protected Boolean getEntity() {
		return logout;
	}

	public Boolean getLogout() {
		return logout;
	}

	public void setLogout(Boolean logout) {
		this.logout = logout;
	}



}
