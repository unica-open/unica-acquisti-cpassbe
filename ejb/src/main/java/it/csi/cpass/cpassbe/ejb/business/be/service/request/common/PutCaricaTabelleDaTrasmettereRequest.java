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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.common;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;


public class PutCaricaTabelleDaTrasmettereRequest implements BaseRequest {

	private String idProgramma;


	/**
	 * @param idProgramma
	 */
	public PutCaricaTabelleDaTrasmettereRequest(String idProgramma) {
		this.idProgramma = idProgramma;
	}

	/**
	 * @return the idProgramma
	 */
	public String getIdProgramma() {
		return idProgramma;
	}

	/**
	 * @param idProgramma the idProgramma to set
	 */
	public void setIdProgramma(String idProgramma) {
		this.idProgramma = idProgramma;
	}

}
