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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

/**
 * Request for putting the stato PutAvviaInterventoById
 */
public class PutAvviaInterventoByIdRequest implements BaseRequest {

	private final UUID interventoId;
	private final Integer motivoEsclusioneCigId;
	private List<String> cigs = new ArrayList<>();


	public PutAvviaInterventoByIdRequest(UUID interventoId, Integer motivoEsclusioneCigId, List<String> cigs) {
		this.interventoId = interventoId;
		this.motivoEsclusioneCigId = motivoEsclusioneCigId;
		this.cigs = cigs;
	}

	/**
	 * @return the cigs
	 */
	public List<String> getCigs() {
		return cigs;
	}

	/**
	 * @return the interventoId
	 */
	public UUID getInterventoId() {
		return interventoId;
	}

	/**
	 * @return the motivoEsclusioneCigId
	 */

	public Integer getMotivoEsclusioneCigId() {
		return motivoEsclusioneCigId;
	}

}
