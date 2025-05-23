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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.rda;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.ord.rda.RigaRda;


public class PutRigaRdaRequest implements BaseRequest{

	private final RigaRda rigaRda;
	private final Boolean checkOdsConfermato;

	public PutRigaRdaRequest(RigaRda rigaRda, Boolean checkOdsConfermato) {
		this.rigaRda = rigaRda;
		this.checkOdsConfermato = checkOdsConfermato;
	}

	public RigaRda getRigaRda() {
		return rigaRda;
	}

	public Boolean getCheckOdsConfermato() {
		return checkOdsConfermato;
	}



}
