/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.service.response.rms;

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BasePostResponse;
import it.csi.cpass.cpassbe.lib.dto.rms.RigaRms;

public class PostRigaRmsResponse  extends BasePostResponse<UUID,RigaRms> {

	private RigaRms rigaRms;

	public void setRigaRms(RigaRms rigaRms) {
		this.rigaRms = rigaRms;
	}

	@Override
	protected RigaRms getEntity() {
		return rigaRms;
	}

	@Override
	protected String getBaseUri() {
		return "rigaRms";
	}

}
