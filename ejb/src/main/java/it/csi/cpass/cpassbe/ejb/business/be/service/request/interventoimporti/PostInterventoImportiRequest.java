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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.interventoimporti;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.pba.InterventoImporti;

/**
 * Request for posting the InterventoImporti
 */
public class PostInterventoImportiRequest implements BaseRequest {

	private final InterventoImporti interventoImporti;

	/**
	 * Constructor
	 * @param interventoImporti the interventoImporti
	 */
	public PostInterventoImportiRequest(InterventoImporti interventoImporti) {
		this.interventoImporti = interventoImporti;
	}

	/**
	 * @return the interventoImporti
	 */
	public InterventoImporti getInterventoImporti() {
		return interventoImporti;
	}
}
