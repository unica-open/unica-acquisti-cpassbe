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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.programma;


import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BasePagedRequest;
import it.csi.cpass.cpassbe.lib.dto.pba.Programma;

/**
 * Request for reading the Programmas paginated
 */
public class GetRicercaProgrammiRequest extends BasePagedRequest {

	private final Programma programma;
	/**
	 * Constructor
	 * @param size the size
	 * @param page the page
	 * @param sort the sort
	 * @param direction the direction
	 * @param programma the programma
	 */
	public GetRicercaProgrammiRequest(Integer page, Integer size, String sort, String direction, Programma programma) {
		super(size, page, sort, direction);
		this.programma = programma != null ? programma : new Programma();
	}
	/**
	 * @return the programma
	 */
	public Programma getProgramma() {
		return programma;
	}

}
