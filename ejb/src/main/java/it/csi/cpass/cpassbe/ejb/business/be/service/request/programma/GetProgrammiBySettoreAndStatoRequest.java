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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.programma;

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

/**
 * Request for reading the Programmi
 */
public class GetProgrammiBySettoreAndStatoRequest implements BaseRequest {
	private final UUID settoreId;
	private final String statoCode;

	/**
	 * Constructor
	 * @param settoreId the id settore
	 * @param statoCode
	 */
	public GetProgrammiBySettoreAndStatoRequest(UUID settoreId , String statoCode) {
		this.settoreId = settoreId;
		this.statoCode = statoCode;
	}

	/**
	 * @return the settoreId
	 */
	public UUID getSettoreId() {
		return settoreId;
	}

	/**
	 * @return the soloValidi
	 */
	public String getStatoCode() {
		return statoCode;
	}




}
