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

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BasePagedRequest;
import it.csi.cpass.cpassbe.lib.dto.Settore;


public class PostRicercaSettoreRequest extends BasePagedRequest {

	private final Settore settore;
	private final UUID idSettoreRadice;

	/**
	 * Constructor
	 *
	 * @param settore the fornitore
	 */
	public PostRicercaSettoreRequest(Integer page, Integer size, String sort, String direction, Settore settore, UUID idSettoreRadice) {
		super(size, page, sort, direction);
		this.settore = settore != null ? settore : new Settore();
		this.idSettoreRadice = idSettoreRadice;
	}

	/**
	 * @return the fornitore
	 */
	public Settore getSettore() {
		return settore;
	}

	public UUID getIdSettoreRadice() {
		return idSettoreRadice;
	}

}
