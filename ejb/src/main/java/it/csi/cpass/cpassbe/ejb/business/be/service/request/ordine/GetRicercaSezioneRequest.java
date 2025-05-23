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
import it.csi.cpass.cpassbe.lib.dto.ord.Sezione;

/**
 * Request for reading the Rms paginated
 */
public class GetRicercaSezioneRequest extends BasePagedRequest {


	private final Sezione sezione;

	public GetRicercaSezioneRequest(Integer page, Integer size, String sort, String direction, Sezione sezione) {
		super(size, page, sort, direction);
		this.sezione = sezione;
	}

	/**
	 * @return the magazzino
	 */
	public Sezione getSezione() {
		return sezione;
	}

}
