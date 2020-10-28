/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica;


import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BasePagedRequest;
import it.csi.cpass.cpassbe.lib.dto.ord.OggettiSpesa;

/**
 * Request for reading the Interventos paginated
 */
public class GetRicercaOggettiSpesaRequest extends BasePagedRequest {

	private final OggettiSpesa oggettiSpesa;
	
	/**
	 * Constructor
	 * @param size the size
	 * @param page the page
	 * @param sort the sort
	 * @param direction the direction
	 * @param intervento the oggettiSpesa
	 */
	public GetRicercaOggettiSpesaRequest(Integer page, Integer size, String sort, String direction, OggettiSpesa oggettiSpesa) {
		super(size, page, sort, direction);
		this.oggettiSpesa = oggettiSpesa != null ? oggettiSpesa : new OggettiSpesa();
	}
	
	/**
	 * @return the oggettiSpesa
	 */
	public OggettiSpesa getOggettiSpesa() {
		return oggettiSpesa;
	}
	
}
