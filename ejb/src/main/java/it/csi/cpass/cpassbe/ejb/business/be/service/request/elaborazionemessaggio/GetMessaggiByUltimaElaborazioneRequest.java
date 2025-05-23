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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.elaborazionemessaggio;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

/**
 * Request for reading the Programma by its id
 */
public class GetMessaggiByUltimaElaborazioneRequest implements BaseRequest {

	private final String elaborazioneTipoCodice;
	private String entitaId;

	/**
	 * Constructor
	 * @param id the id
	 */
	public GetMessaggiByUltimaElaborazioneRequest(String entitaId,String elaborazioneTipoCodice) {
		this.elaborazioneTipoCodice = elaborazioneTipoCodice;
		this.entitaId = entitaId;
	}

	/**
	 * @return the id
	 */
	public String getElaborazioneTipoCodice() {
		return elaborazioneTipoCodice;
	}

	/**
	 * @return the enteId
	 */
	public String getEntitaId() {
		return entitaId;
	}

	/**
	 * @param enteId the enteId to set
	 */
	public void setEntitaId(String entitaId) {
		this.entitaId = entitaId;
	}

}
