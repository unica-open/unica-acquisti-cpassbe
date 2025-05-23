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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.common;

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

/**
 * The Class GetSettoreTreeByEnteRequest.
 */
public class GetSettoreTreeByEnteRequest implements BaseRequest {
	private final UUID enteId;
	private final String codSettoreRadice;
	private final String validita;

	public GetSettoreTreeByEnteRequest(UUID enteId, String codSettoreRadice, String validita) {
		this.enteId = enteId;
		this.codSettoreRadice = codSettoreRadice;
		this.validita = validita;
	}

	public UUID getEnteId() {
		return enteId;
	}

	public String getCodSettoreRadice() {
		return codSettoreRadice;
	}

	public String getValidita() {
		return validita;
	}
}
