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
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.ControllaEvasione;

public class PutEvasioneControllaByIdRequest implements BaseRequest {

	private final UUID id;
	private final ControllaEvasione controllaEvasione;
	private final Boolean perAutorizzazione;

	/**
	 * Constructor
	 *
	 * @param testataEvasione the testataEvasione
	 */
	public PutEvasioneControllaByIdRequest(UUID id, ControllaEvasione controllaEvasione, Boolean perAutorizzazione) {
		this.id = id;
		this.controllaEvasione = controllaEvasione;
		this.perAutorizzazione = perAutorizzazione;
	}

	/**
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * @return the controllaEvasione
	 */
	public ControllaEvasione getControllaEvasione() {
		return controllaEvasione;
	}


	/**
	 * @return the perAutorizzazione flag
	 */
	public Boolean isPerAutorizzazione() {
		return perAutorizzazione;
	}

}
