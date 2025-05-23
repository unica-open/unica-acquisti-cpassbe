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

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BasePagedRequest;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.custom.RicercaDdt;

/**
 * Request for reading the Ordines paginated
 */
public class PostRicercaDdtEvasioneRequest extends BasePagedRequest {
	private final RicercaDdt ricercaDdt;

	/**
	 * Constructor
	 *
	 * @param ricercaDdt the ricercaDdt filter
	 */
	public PostRicercaDdtEvasioneRequest(Integer page, Integer size, String sort, String direction, RicercaDdt ricercaDdt) {
		super(size, page, sort, direction);
		this.ricercaDdt = ricercaDdt;
	}

	/**
	 * @return the ricercaDdt object
	 */
	public RicercaDdt getRicercaDdt() {
		return ricercaDdt;
	}

}
