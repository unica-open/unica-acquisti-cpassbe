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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.documentale;

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

public class GetVerificaArchiviazioneOrdineRequest implements BaseRequest {

	private final UUID testataOrdineId;

	/**
	 * @param testataOrdineId
	 */
	public GetVerificaArchiviazioneOrdineRequest(UUID testataOrdineId) {
		super();
		this.testataOrdineId = testataOrdineId;
	}

	/**
	 * @return the testataOrdineId
	 */
	public UUID getTestataOrdineId() {
		return testataOrdineId;
	}


}
