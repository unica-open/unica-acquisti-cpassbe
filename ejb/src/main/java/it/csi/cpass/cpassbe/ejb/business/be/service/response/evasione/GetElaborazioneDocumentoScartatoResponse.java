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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;

public class GetElaborazioneDocumentoScartatoResponse extends BaseGetResponse<List<Elaborazione>> {
	private List<Elaborazione> elaborazioneList;

	public List<Elaborazione> getElaborazioneList() {
		return elaborazioneList;
	}

	public void setElaborazioneList(List<Elaborazione> elaborazioneList) {
		this.elaborazioneList = elaborazioneList;
	}


	@Override
	protected List<Elaborazione> getEntity() {
		return elaborazioneList;
	}
}
