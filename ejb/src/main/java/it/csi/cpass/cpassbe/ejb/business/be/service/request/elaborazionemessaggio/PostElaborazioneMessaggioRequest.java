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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.elaborazionemessaggio;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.ElaborazioneMessaggio;

/**
 * Request for posting the Elaborazione
 */
public class PostElaborazioneMessaggioRequest implements BaseRequest {

	private final ElaborazioneMessaggio elaborazioneMessaggio;

	/**
	 * Constructor
	 * @param elaborazioneMessaggio the elaborazione
	 */
	public PostElaborazioneMessaggioRequest(ElaborazioneMessaggio elaborazioneMessaggio) {
		this.elaborazioneMessaggio = elaborazioneMessaggio;
	}

	/**
	 * @return the elaborazione
	 */
	public ElaborazioneMessaggio getElaborazioneMessaggio() {
		return elaborazioneMessaggio;
	}
}
