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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;

public class PutRigaOrdineRequest implements BaseRequest {

	private final RigaOrdine rigaOrdine;
	private final Boolean flagBypassControlloIva;

	/**
	 * Constructor
	 *
	 * @param rigaOrdine the rigaOrdine
	 */
	public PutRigaOrdineRequest(RigaOrdine rigaOrdine, Boolean flagBypassControlloIva) {
		this.rigaOrdine = rigaOrdine;
		this.flagBypassControlloIva = flagBypassControlloIva;
	}

	/**
	 * @return the rigaOrdine
	 */
	public RigaOrdine getRigaOrdine() {
		return rigaOrdine;
	}

	public Boolean getFlagBypassControlloIva() {
		return flagBypassControlloIva;
	}

}
