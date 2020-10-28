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

/**
 * Request for reading the Interventos paginated
 */
public class GetRicercaInterventiXCopiaRequest extends BasePagedRequest {

	private UUID programmaIdOld;
	private UUID programmaIdNew;
	/**
	 * Constructor
	 * @param size the size
	 * @param page the page
	 * @param sort the sort
	 * @param direction the direction
	 * @param programmaIdOld 
	 * @param programmaIdNew 
	 */
	public GetRicercaInterventiXCopiaRequest(Integer page, Integer size, String sort, String direction, UUID programmaIdOld,UUID programmaIdNew) {
		super(size, page, sort, direction);
		this.programmaIdOld = programmaIdOld;
		this.programmaIdNew = programmaIdNew;
	}
	/**
	 * @return the programmaIdOld
	 */
	public UUID getProgrammaIdOld() {
		return programmaIdOld;
	}
	/**
	 * @return the programmaIdNew
	 */
	public UUID getProgrammaIdNew() {
		return programmaIdNew;
	}
	
}
