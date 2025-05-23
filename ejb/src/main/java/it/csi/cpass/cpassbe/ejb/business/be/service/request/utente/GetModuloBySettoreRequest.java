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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.utente;

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

/**
 * Request for reading the Settori
 */
public class GetModuloBySettoreRequest implements BaseRequest {
	private final UUID settoreId;

	/**
	 * Constructor
	 * @param settoreId the id settore
	 */
	public GetModuloBySettoreRequest(UUID settoreId) {
		this.settoreId = settoreId;
	}

	/**
	 * @return the settoreId
	 */
	public UUID getSettoreId() {
		return settoreId;
	}
}
