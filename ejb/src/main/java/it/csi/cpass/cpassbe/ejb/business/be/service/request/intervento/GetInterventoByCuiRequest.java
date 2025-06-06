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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento;

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

/**
 * Request for reading the Intervento by its id
 */
public class GetInterventoByCuiRequest implements BaseRequest {

	private final String cui;
	private final UUID idProgramma;


	/**
	 * Constructor
	 * @param cui the cui
	 * @param idProgramma
	 */
	public GetInterventoByCuiRequest(String cui, UUID idProgramma) {
		this.cui = cui;
		this.idProgramma = idProgramma;
	}

	/**
	 * @return the id
	 */
	public String getCui() {
		return cui;
	}

	/**
	 * @return the idProgramma
	 */
	public UUID getIdProgramma() {
		return idProgramma;
	}

}
