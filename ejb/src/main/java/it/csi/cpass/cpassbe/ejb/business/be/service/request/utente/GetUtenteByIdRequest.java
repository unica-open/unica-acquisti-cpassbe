/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.service.request.utente;

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

/**
 * Request for reading the Intervento by its id
 */
public class GetUtenteByIdRequest implements BaseRequest {

	private final UUID id;

	/**
	 * Constructor
	 * @param id the id
	 */
	public GetUtenteByIdRequest(UUID id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}
}
