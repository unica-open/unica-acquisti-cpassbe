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

public class DeleteImpegniEvasioneByRigaRequest implements BaseRequest {

	private final UUID rigaEvasioneId;

	/**
	 * Constructor
	 *
	 * @param rigaEvasioneId the id of the riga evasione to delete
	 */
	public DeleteImpegniEvasioneByRigaRequest(UUID rigaEvasioneId) {
		this.rigaEvasioneId = rigaEvasioneId;
	}

	/**
	 * @return the rigaEvasioneId
	 */
	public UUID getRigaEvasioneId() {
		return rigaEvasioneId;
	}

}
