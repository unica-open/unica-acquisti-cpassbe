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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.common;

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

/**
 * The Class GetUfficiBySettoreRequest.
 */
public class IsMySectorParentRequest implements BaseRequest {
	private final UUID settoreId;
	private UUID settorePadreId;

	/**
	 * Constructor
	 * 
	 * @param settoreId the settoreId
	 */
	public IsMySectorParentRequest(UUID settoreId,UUID settorePadreId) {
		this.settoreId = settoreId;
		this.settorePadreId = settorePadreId;
	}

	/**
	 * @return the settoreId
	 */
	public UUID getSettoreId() {
		return settoreId;
	}

	/**
	 * @return the settorePadreId
	 */
	public UUID getSettorePadreId() {
		return settorePadreId;
	}



}
