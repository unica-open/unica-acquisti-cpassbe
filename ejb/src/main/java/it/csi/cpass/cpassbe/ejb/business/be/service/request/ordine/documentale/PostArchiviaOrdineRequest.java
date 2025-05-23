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

public class PostArchiviaOrdineRequest implements BaseRequest {

	private final UUID testataOrdineId;
	private final Boolean aggiornaOrdine;

	/**
	 * @param testataOrdineId
	 */
	public PostArchiviaOrdineRequest(UUID testataOrdineId, Boolean aggiornaOrdine) {
		super();
		this.testataOrdineId = testataOrdineId;
		this.aggiornaOrdine = aggiornaOrdine;
	}

	/**
	 * @return the testataOrdineId
	 */
	public UUID getTestataOrdineId() {
		return testataOrdineId;
	}

	/**
	 * @return the aggiornaOrdine
	 */
	public Boolean isAggiornaOrdine() {
		return aggiornaOrdine;
	}
}
