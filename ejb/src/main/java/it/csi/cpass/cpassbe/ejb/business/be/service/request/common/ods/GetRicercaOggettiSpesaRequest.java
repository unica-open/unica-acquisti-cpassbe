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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.common.ods;


import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BasePagedRequest;
import it.csi.cpass.cpassbe.lib.dto.Ods;

/**
 * Request for reading the Interventos paginated
 */
public class GetRicercaOggettiSpesaRequest extends BasePagedRequest {

	private final Ods oggettiSpesa;

	/**
	 * Constructor
	 * @param size the size
	 * @param page the page
	 * @param sort the sort
	 * @param direction the direction
	 * @param intervento the oggettiSpesa
	 */
	public GetRicercaOggettiSpesaRequest(Integer page, Integer size, String sort, String direction, Ods oggettiSpesa) {
		super(size, page, sort, direction);
		this.oggettiSpesa = oggettiSpesa != null ? oggettiSpesa : new Ods();
	}

	/**
	 * @return the oggettiSpesa
	 */
	public Ods getOggettiSpesa() {
		return oggettiSpesa;
	}

}
