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

public class DeleteImpegniByRigaRequest implements BaseRequest {

	private final UUID idRiga;

	/**
	 * Constructor
	 * 
	 * @param idRiga the id of the riga to delete
	 */
	public DeleteImpegniByRigaRequest(UUID idRiga) {
		this.idRiga = idRiga;
	}

	/**
	 * @return the idRiga
	 */
	public UUID getIdRiga() {
		return idRiga;
	}
	
}
