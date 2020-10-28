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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione;

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

public class DeleteDestinatarioEvasioneRequest implements BaseRequest {

	private final UUID idDestinatarioEvasione;

	/**
	 * Constructor
	 * 
	 * @param idDestinatarioEvasione the id of the DestinatarioEvasione to delete
	 */
	public DeleteDestinatarioEvasioneRequest(UUID idDestinatarioEvasione) {
		this.idDestinatarioEvasione = idDestinatarioEvasione;
	}

	/**
	 * @return the idDestinatarioEvasione
	 */
	public UUID getIdDestinatarioEvasione() {
		return idDestinatarioEvasione;
	}
}
