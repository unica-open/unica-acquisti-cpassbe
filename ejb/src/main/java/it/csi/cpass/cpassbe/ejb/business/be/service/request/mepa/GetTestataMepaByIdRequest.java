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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.mepa;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

public class GetTestataMepaByIdRequest implements BaseRequest {

	private final Integer id;

	public GetTestataMepaByIdRequest(Integer idTestata) {
		this.id = idTestata;
	}

	public Integer getId() {
		return id;
	}
}
