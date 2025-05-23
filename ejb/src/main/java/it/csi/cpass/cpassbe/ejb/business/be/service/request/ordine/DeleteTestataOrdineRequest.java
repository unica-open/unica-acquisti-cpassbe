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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine;

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

public class DeleteTestataOrdineRequest implements BaseRequest {

	private final UUID idTestataOrdine;

	/**
	 * Constructor
	 *
	 * @param idTestataOrdine the id of the idTestataOrdine to delete
	 */
	public DeleteTestataOrdineRequest(UUID idTestataOrdine) {
		this.idTestataOrdine = idTestataOrdine;
	}

	/**
	 * @return the idDestinatario
	 */
	public UUID getIdTestataOrdine() {
		return idTestataOrdine;
	}

}
