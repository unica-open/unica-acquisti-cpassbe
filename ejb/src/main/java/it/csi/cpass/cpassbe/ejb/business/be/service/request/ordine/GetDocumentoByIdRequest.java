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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

public class GetDocumentoByIdRequest implements BaseRequest {

	private final Integer id;

	/**
	 * Constructor
	 *
	 * @param id the id
	 */
	public GetDocumentoByIdRequest(Integer id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
}
