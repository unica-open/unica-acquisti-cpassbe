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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.mepa;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseDeleteResponse;

public class DeleteOrdineMepaResponse extends BaseDeleteResponse {
	Integer idOrdineCancellato;

	public Integer getIdOrdineCancellato() {
		return idOrdineCancellato;
	}

	public void setIdOrdineCancellato(Integer idOrdineCancellato) {
		this.idOrdineCancellato = idOrdineCancellato;
	}

}
