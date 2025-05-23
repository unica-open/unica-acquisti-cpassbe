/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2025 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento;

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

public class PutInterventoNonAvviatoByIdRequest implements BaseRequest {

	private final UUID interventoId;

	public PutInterventoNonAvviatoByIdRequest(UUID interventoId) {
		this.interventoId = interventoId;
	}

	/**
	 * @return the interventoId
	 */
	public UUID getInterventoId() {
		return interventoId;
	}

}
