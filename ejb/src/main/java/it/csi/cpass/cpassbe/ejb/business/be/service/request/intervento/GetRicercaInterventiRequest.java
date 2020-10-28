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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento;


import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BasePagedRequest;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;

/**
 * Request for reading the Interventos paginated
 */
public class GetRicercaInterventiRequest extends BasePagedRequest {

	private final Intervento intervento;
	private final UUID settoreId;
	
	/**
	 * Constructor
	 * @param size the size
	 * @param page the page
	 * @param sort the sort
	 * @param direction the direction
	 * @param intervento the intervento
	 */
	public GetRicercaInterventiRequest(Integer page, Integer size, String sort, String direction, Intervento intervento, UUID settoreId) {
		super(size, page, sort, direction);
		this.intervento = intervento != null ? intervento : new Intervento();
		this.settoreId = settoreId;
	}
	
	/**
	 * @return the intervento
	 */
	public Intervento getIntervento() {
		return intervento;
	}
	
	/**
	 * @return
	 */
	public UUID getSettoreId() {
		return settoreId;
	}

}
