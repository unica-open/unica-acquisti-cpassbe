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

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BasePutResponse;
import it.csi.cpass.cpassbe.lib.dto.rms.RigaRms;

public class PutCambioStatoRigaRmsResponse extends BasePutResponse{

	private List<RigaRms> rigaRmsList;

	public List<RigaRms> getRigaRmsList() {
		return rigaRmsList;
	}

	public void setRigaRmsList(List<RigaRms> rigaRmsList) {
		this.rigaRmsList = rigaRmsList;
	}



}
