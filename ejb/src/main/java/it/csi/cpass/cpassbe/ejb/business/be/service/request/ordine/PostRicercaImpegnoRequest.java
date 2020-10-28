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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BasePagedRequest;
import it.csi.cpass.cpassbe.lib.dto.ord.FiltroImpegni;

public class PostRicercaImpegnoRequest extends BasePagedRequest {

	private final FiltroImpegni filtroImpegni;

	/**
	 * Constructor
	 * 
	 * @param page
	 * @param size
	 * @param sort
	 * @param direction
	 * @param subimpegno
	 * @param testataOrdineId
	 * @param dataEmissione
	 */
	public PostRicercaImpegnoRequest(Integer page, Integer size, String sort, String direction, FiltroImpegni filtroImpegni) {
		super(size, page, sort, direction);
		this.filtroImpegni = filtroImpegni;
	}

	/**
	 * @return the filtroImpegni
	 */
	public FiltroImpegni getFiltroImpegni() {
		return filtroImpegni;
	}

}
