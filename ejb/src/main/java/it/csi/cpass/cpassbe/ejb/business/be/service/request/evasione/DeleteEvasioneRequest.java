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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione;

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

public class DeleteEvasioneRequest implements BaseRequest {

	private final UUID idTestataEvasione;

	/**
	 * Constructor
	 *
	 * @param idTestataEvasione the id of the TestataEvasione to delete
	 */
	public DeleteEvasioneRequest(UUID idTestataEvasione) {
		this.idTestataEvasione = idTestataEvasione;
	}

	/**
	 * @return the idTestataEvasione
	 */
	public UUID getIdTestataEvasione() {
		return idTestataEvasione;
	}
}
