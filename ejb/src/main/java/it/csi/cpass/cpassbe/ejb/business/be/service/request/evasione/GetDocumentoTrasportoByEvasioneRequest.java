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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione;

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

public class GetDocumentoTrasportoByEvasioneRequest implements BaseRequest{

	private final UUID idEvasione;

	public GetDocumentoTrasportoByEvasioneRequest(UUID idEvasione) {
		super();
		this.idEvasione = idEvasione;
	}

	public UUID getIdEvasione() {
		return idEvasione;
	}

}
