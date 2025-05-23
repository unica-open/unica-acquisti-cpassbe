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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.common;

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

/**
 * The Class IsSettoreRiorganizzatoRequest.
 */
public class IsSettoreRiorganizzatoRequest implements BaseRequest {
	private UUID idSettorePrecedente;
	private UUID idSettoreAttuale;

	/**
	 * Constructor
	 *
	 * @param settoreId the settoreId
	 */
	public IsSettoreRiorganizzatoRequest(UUID idSettorePrecedente, UUID idSettoreAttuale) {
		this.idSettorePrecedente = idSettorePrecedente;
		this.idSettoreAttuale = idSettoreAttuale;
	}

	/**
	 * @return the idSettorePrecedente
	 */
	public UUID getIdSettorePrecedente() {
		return idSettorePrecedente;
	}

	/**
	 * @param idSettorePrecedente the idSettorePrecedente to set
	 */
	public void setIdSettorePrecedente(UUID idSettorePrecedente) {
		this.idSettorePrecedente = idSettorePrecedente;
	}

	/**
	 * @return the idSettoreAttuale
	 */
	public UUID getIdSettoreAttuale() {
		return idSettoreAttuale;
	}

	/**
	 * @param idSettoreAttuale the idSettoreAttuale to set
	 */
	public void setIdSettoreAttuale(UUID idSettoreAttuale) {
		this.idSettoreAttuale = idSettoreAttuale;
	}




}
