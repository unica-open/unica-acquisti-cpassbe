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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.rda;

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

public class DeleteTestataRdaByIdRequest implements BaseRequest {

	private final UUID idTestataRda;

	/**
	 * Constructor
	 *
	 * @param idTestataRda the id of the idTestataRda to delete
	 */
	public DeleteTestataRdaByIdRequest(UUID idTestataRda) {
		this.idTestataRda = idTestataRda;
	}

	/**
	 * @return the idDestinatario
	 */
	public UUID getIdTestataRda() {
		return idTestataRda;
	}

}
