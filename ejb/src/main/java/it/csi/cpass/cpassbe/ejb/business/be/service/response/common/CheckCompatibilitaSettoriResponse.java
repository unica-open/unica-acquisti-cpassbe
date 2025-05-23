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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.common;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;

public class  CheckCompatibilitaSettoriResponse extends BaseGetResponse<Boolean>{

	private Boolean compatibili;


	public Boolean getCompatibili() {
		return compatibili;
	}


	public void setCompatibili(Boolean compatibili) {
		this.compatibili = compatibili;
	}



	@Override
	protected Boolean getEntity() {
		return compatibili;
	}

}
