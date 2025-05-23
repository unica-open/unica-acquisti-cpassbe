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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.elaborazione;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

public class GetElaborazioniByEntityRequest implements BaseRequest {

	private final String id;

	/**
	 * Constructor
	 * @param id the id
	 */
	public GetElaborazioniByEntityRequest(String entityId) {
		this.id = entityId;
	}

	/**
	 * @return the id
	 */
	public String getEntityId() {
		return id;
	}
}
