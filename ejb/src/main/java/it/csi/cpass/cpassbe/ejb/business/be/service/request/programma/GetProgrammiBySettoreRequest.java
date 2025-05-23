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
public class GetProgrammiBySettoreRequest implements BaseRequest {
	private final UUID settoreId;
	private final Boolean soloValidi;

	/**
	 * Constructor
	 * @param settoreId the id settore
	 * @param soloValidi
	 */
	public GetProgrammiBySettoreRequest(UUID settoreId , Boolean soloValidi) {
		this.settoreId = settoreId;
		this.soloValidi = soloValidi;
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
	public Boolean getSoloValidi() {
		return soloValidi;
	}




}
