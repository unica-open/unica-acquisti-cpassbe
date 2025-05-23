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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.rms;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseDeleteResponse;
import it.csi.cpass.cpassbe.lib.dto.rms.RigaRms;

public class DeleteRigaRmsResponse extends BaseDeleteResponse {

	private RigaRms rigaRms;

	/**
	 * @return the rigaRms
	 */
	public RigaRms getRigaRms() {
		return rigaRms;
	}

	/**
	 * @param rigaOrdine the rigaRms to set
	 */
	public void setRigaRms(RigaRms rigaRms) {
		this.rigaRms = rigaRms;
	}

}
