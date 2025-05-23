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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.magazzino;


import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BasePagedRequest;
import it.csi.cpass.cpassbe.lib.dto.mag.Magazzino;

/**
 * Request for reading the Rms paginated
 */
public class GetRicercaMagazzinoRequest extends BasePagedRequest {


	private final Magazzino magazzino;

	public GetRicercaMagazzinoRequest(Integer page, Integer size, String sort, String direction, Magazzino magazzino) {
		super(size, page, sort, direction);
		this.magazzino = magazzino;
	}

	/**
	 * @return the magazzino
	 */
	public Magazzino getMagazzino() {
		return magazzino;
	}

}
