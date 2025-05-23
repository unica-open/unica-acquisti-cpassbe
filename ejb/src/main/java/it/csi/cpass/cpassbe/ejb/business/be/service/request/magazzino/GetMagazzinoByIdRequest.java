/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.service.request.magazzino;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

public class GetMagazzinoByIdRequest implements BaseRequest {
	private final Integer id;

	public GetMagazzinoByIdRequest(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
}
