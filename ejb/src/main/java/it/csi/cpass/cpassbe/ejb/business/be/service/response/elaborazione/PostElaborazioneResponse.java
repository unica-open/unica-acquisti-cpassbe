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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.elaborazione;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BasePostResponse;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;

/**
 * Response for reading Elaborazione by its id.
 */
public class PostElaborazioneResponse extends BasePostResponse<Integer, Elaborazione> {

	private Elaborazione elaborazione;

	/**
	 * @return the elaborazione
	 */
	public Elaborazione getElaborazione() {
		return elaborazione;
	}

	/**
	 * @param elaborazione the elaborazione to set
	 */
	public void setElaborazione(Elaborazione elaborazione) {
		this.elaborazione = elaborazione;
	}

	@Override
	protected Elaborazione getEntity() {
		return elaborazione;
	}

	@Override
	protected String getBaseUri() {
		return "elaborazione";
	}

}
