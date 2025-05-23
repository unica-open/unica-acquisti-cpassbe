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

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

/**
 * The Class GetSettoreTreeByEnteRequest.
 */
public class GetTokenRequest implements BaseRequest {
	private final Integer numToken;
	private final Integer numSecondi;

	public GetTokenRequest(Integer numToken,Integer numSecondi) {
		this.numToken = numToken;
		this.numSecondi = numSecondi;
	}

	public Integer getNumToken() {
		return numToken;
	}

	public Integer getNumSecondi() {
		return numSecondi;
	}

	
}
