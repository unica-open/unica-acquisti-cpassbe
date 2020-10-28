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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.elaborazionemessaggio;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.ElaborazioneMessaggio;

/**
 * Response for reading Elaborazione by its id.
 */
public class GetElaborazioneMessaggioByIdResponse extends BaseGetResponse<ElaborazioneMessaggio> {

	private ElaborazioneMessaggio elaborazioneMessaggio;

	/**
	 * @return the elaborazioneMessaggio
	 */
	public ElaborazioneMessaggio getElaborazioneMessaggio() {
		return elaborazioneMessaggio;
	}

	/**
	 * @param elaborazioneMessaggio the elaborazione messaggio to set
	 */
	public void setElaborazioneMessaggio(ElaborazioneMessaggio elaborazioneMessaggio) {
		this.elaborazioneMessaggio = elaborazioneMessaggio;
	}

	@Override
	protected ElaborazioneMessaggio getEntity() {
		return elaborazioneMessaggio;
	}

}
