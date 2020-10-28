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

public class DeleteRigaEvasioneRequest implements BaseRequest {

	private final UUID idRigaEvasione;

	/**
	 * Constructor
	 * 
	 * @param idRiga the id of the riga evasione to delete
	 */
	public DeleteRigaEvasioneRequest(UUID idRigaEvasione) {
		this.idRigaEvasione = idRigaEvasione;
	}

	/**
	 * @return the idRigaEvasione
	 */
	public UUID getIdRigaEvasione() {
		return idRigaEvasione;
	}

}
