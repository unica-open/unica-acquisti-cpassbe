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

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;


public class PutCambioStatoRdaRequest implements BaseRequest{

	private final UUID rdaId;
	private String statoCode;

	public PutCambioStatoRdaRequest(UUID rdaId,String statoCode) {
		this.rdaId   = rdaId;
		this.statoCode = statoCode;
	}

	public UUID getRdaId() {
		return rdaId;
	}

	/**
	 * @return the statoCode
	 */
	public String getStatoCode() {
		return statoCode;
	}

	/**
	 * @param statoCode the statoCode to set
	 */
	public void setStatoCode(String statoCode) {
		this.statoCode = statoCode;
	}


}
