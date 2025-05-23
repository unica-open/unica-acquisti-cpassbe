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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.mepa;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

public class DeleteOrdineMepaRequest implements BaseRequest {

	Integer idOrdineDaCancellare;

	public DeleteOrdineMepaRequest(Integer idOrdineDaCancellare) {
		this.idOrdineDaCancellare = idOrdineDaCancellare;
	}

	public Integer getIdOrdineDaCancellare() {
		return idOrdineDaCancellare;
	}

	public void setIdOrdineDaCancellare(Integer idOrdineDaCancellare) {
		this.idOrdineDaCancellare = idOrdineDaCancellare;
	}
}
