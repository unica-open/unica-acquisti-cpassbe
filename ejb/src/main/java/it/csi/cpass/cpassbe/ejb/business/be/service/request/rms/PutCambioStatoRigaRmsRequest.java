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

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.rms.RigaRms;

public class PutCambioStatoRigaRmsRequest implements BaseRequest {

	private final String statoCode;
	private final List<RigaRms> rigaRmsList;

	public PutCambioStatoRigaRmsRequest(String statoCode, List<RigaRms> rigaRmsList) {
		this.statoCode = statoCode;
		this.rigaRmsList = rigaRmsList;
	}

	public String getStatoCode() {
		return statoCode;
	}

	public List<RigaRms> getRigaRmsList() {
		return rigaRmsList;
	}

}
