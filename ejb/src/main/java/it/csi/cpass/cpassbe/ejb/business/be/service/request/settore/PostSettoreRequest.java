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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.settore;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.Settore;

/**
 * Request for posting the InterventoImporti
 */
public class PostSettoreRequest implements BaseRequest {

	private final Settore settore;

	/**
	 * Constructor
	 * @param settore the settore
	 */
	public PostSettoreRequest(Settore settore) {
		this.settore = settore;
	}

	/**
	 * @return the interventoImporti
	 */
	public Settore getSettore() {
		return settore;
	}
}
