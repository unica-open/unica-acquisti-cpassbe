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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.elaborazione;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;

/**
 * Request for putting the Elaborazione
 */
public class PutElaborazioneRequest implements BaseRequest {

	private final Elaborazione elaborazione;

	/**
	 * Constructor
	 * @param elaborazione the elaborazione
	 */
	public PutElaborazioneRequest(Elaborazione elaborazione) {
		this.elaborazione = elaborazione;
	}

	/**
	 * @return the elaborazione
	 */
	public Elaborazione getElaborazione() {
		return elaborazione;
	}
}
