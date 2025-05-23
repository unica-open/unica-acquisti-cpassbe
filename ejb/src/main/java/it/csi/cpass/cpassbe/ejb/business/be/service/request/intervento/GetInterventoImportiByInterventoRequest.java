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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento;

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BasePagedRequest;

/**
 * Request for reading the Intervento Importis paginated
 */
public class GetInterventoImportiByInterventoRequest extends BasePagedRequest {

	private final UUID id;
	/**
	 * Constructor
	 * @param id the id
	 * @param size the size
	 * @param page the page
	 */
	public GetInterventoImportiByInterventoRequest(UUID id, Integer page, Integer size) {
		super(size, page);
		this.id = id;
	}
	/**
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}

}
