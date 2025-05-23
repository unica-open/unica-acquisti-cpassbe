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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.rms;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.rms.RigaRms;

public class PutRigaRmsRequest implements BaseRequest{

	private final RigaRms rigaRms;

	public PutRigaRmsRequest(RigaRms rigaRms) {
		this.rigaRms = rigaRms;
	}

	public RigaRms getRigaRms() {
		return rigaRms;
	}

}
