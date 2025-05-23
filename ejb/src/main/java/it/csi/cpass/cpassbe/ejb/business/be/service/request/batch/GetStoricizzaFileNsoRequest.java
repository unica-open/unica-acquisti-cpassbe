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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.batch;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

/**
 * The Class GetRecuperoDdtRequest.
 */
public class GetStoricizzaFileNsoRequest implements BaseRequest {
	String codEnte;

	/**
	 * @param codEnte
	 */
	public GetStoricizzaFileNsoRequest(String codEnte) {
		super();
		this.codEnte = codEnte;
	}

	/**
	 * @return the codEnte
	 */
	public String getCodEnte() {
		return codEnte;
	}

}