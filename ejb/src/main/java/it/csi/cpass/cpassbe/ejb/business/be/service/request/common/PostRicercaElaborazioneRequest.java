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

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;

/**
 * Request for posting the Elaborazione
 */
public class PostRicercaElaborazioneRequest implements BaseRequest {

	private final Elaborazione elaborazione;
	private final String elaborazioneTipoCodice;

	/**
	 * Constructor
	 * 
	 * @param elaborazione the elaborazione
	 */
	public PostRicercaElaborazioneRequest(Elaborazione elaborazione, String elaborazioneTipoCodice) {
		this.elaborazione = elaborazione;
		this.elaborazioneTipoCodice = elaborazioneTipoCodice;
	}

	/**
	 * @return the elaborazione
	 */
	public Elaborazione getElaborazione() {
		return elaborazione;
	}

	public String getElaborazioneTipoCodice() {
		return elaborazioneTipoCodice;
	}

}
