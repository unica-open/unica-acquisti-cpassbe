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
 * The Class GetControlloBatchImpegniRequest.
 */
public class GetControlloBatchImpegniRequest implements BaseRequest {
	String codEnte;
	Integer numelab;
	String dataElab;

	/**
	 * @param codEnte
	 */
	public GetControlloBatchImpegniRequest(String codEnte,Integer numelab,String dataElab) {
		super();
		this.codEnte = codEnte;
		this.numelab = numelab;
		this.dataElab = dataElab;
	}

	/**
	 * @return the codEnte
	 */
	public String getCodEnte() {
		return codEnte;
	}

	public Integer getNumelab() {
		return numelab;
	}


	public String getDataElab() {
		return dataElab;
	}


}