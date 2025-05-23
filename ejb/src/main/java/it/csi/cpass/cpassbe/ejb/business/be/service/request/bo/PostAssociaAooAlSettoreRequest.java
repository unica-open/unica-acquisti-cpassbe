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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.bo;

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

/**
 * Request for posting the Notifica
 */
public class PostAssociaAooAlSettoreRequest implements BaseRequest {

	private Integer aooActaId;
	private UUID settoreId;

	/**
	 * Constructor
	 * @param elaborazioneMessaggio the notifica
	 */
	public PostAssociaAooAlSettoreRequest(Integer aooActaId,UUID settoreId) {
		this.aooActaId = aooActaId;
		this.settoreId = settoreId;

	}

	/**
	 * @return the aooActaId
	 */
	public Integer getAooActaId() {
		return aooActaId;
	}

	/**
	 * @param aooActaId the aooActaId to set
	 */
	public void setAooActaId(Integer aooActaId) {
		this.aooActaId = aooActaId;
	}

	/**
	 * @return the settoreId
	 */
	public UUID getSettoreId() {
		return settoreId;
	}

	/**
	 * @param settoreId the settoreId to set
	 */
	public void setSettoreId(UUID settoreId) {
		this.settoreId = settoreId;
	}


}
