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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione;

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

public class PutEvasioneInviaContabilitaByIdRequest implements BaseRequest {

	private final UUID id;
	private final Boolean bypassControls;
	private final Boolean saltaVerificaCongruenzaTotali;

	/**
	 * Constructor
	 *
	 * @param testataEvasione the testataEvasione
	 */
	public PutEvasioneInviaContabilitaByIdRequest(UUID id, Boolean bypassControls, Boolean saltaVerificaCongruenzaTotali) {
		this.id = id;
		this.bypassControls = bypassControls;
		this.saltaVerificaCongruenzaTotali = saltaVerificaCongruenzaTotali;
	}

	/**
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}

	public Boolean getBypassControls() {
		return bypassControls;
	}

	/**
	 * @return the saltaVerificaCongruenzaTotali
	 */
	public Boolean getSaltaVerificaCongruenzaTotali() {
		return saltaVerificaCongruenzaTotali;
	}

}
