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

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.Settore;

/**
 * Request for putting the InterventoImporti
 */
public class PutSettoreRequest implements BaseRequest {

	private final UUID settoreId;
	private final Settore settore;

	/**
	 * Constructor
	 * @param settore the settore
	 */
	public PutSettoreRequest(UUID settoreId,Settore settore) {
		this.settore = settore;
		this.settoreId = settoreId;
	}

	/**
	 * @return the interventoImporti
	 */
	public Settore getSettore() {
		return settore;
	}

	/**
	 * @return the settoreId
	 */
	public UUID getSettoreId() {
		return settoreId;
	}

}
