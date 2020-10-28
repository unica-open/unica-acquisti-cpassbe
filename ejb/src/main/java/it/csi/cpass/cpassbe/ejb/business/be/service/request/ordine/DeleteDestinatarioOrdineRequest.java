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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine;

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

public class DeleteDestinatarioOrdineRequest implements BaseRequest {

	private final UUID idDestinatario;

	/**
	 * Constructor
	 * 
	 * @param idDestinatario the id of the Destinatario to delete
	 */
	public DeleteDestinatarioOrdineRequest(UUID idDestinatario) {
		this.idDestinatario = idDestinatario;
	}

	/**
	 * @return the idDestinatario
	 */
	public UUID getIdDestinatario() {
		return idDestinatario;
	}
}
