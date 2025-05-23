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
 * The Class GetVerificaQuoteDocumentiRequest.
 */
public class GetAggiornamentiSubImpegniRequest implements BaseRequest {
	String ente;
	Integer numelab;
	String dataElab;
	public GetAggiornamentiSubImpegniRequest(String ente,Integer numelab, String dataElab) {
		super();
		this.ente = ente;
		this.numelab = numelab;
		this.dataElab = dataElab;
	}
	/**
	 * @return the ente
	 */
	public String getEnte() {
		return ente;
	}
	/**
	 * @return the numelab
	 */
	public Integer getNumelab() {
		return numelab;
	}
	/**
	 * @return the dataElab
	 */
	public String getDataElab() {
		return dataElab;
	}
	/**
	 * @param dataElab the dataElab to set
	 */
	public void setDataElab(String dataElab) {
		this.dataElab = dataElab;
	}

}