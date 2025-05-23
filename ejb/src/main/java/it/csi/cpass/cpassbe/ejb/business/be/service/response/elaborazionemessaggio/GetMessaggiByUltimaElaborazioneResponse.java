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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.elaborazionemessaggio;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.ElaborazioneMessaggio;

/**
 * Response for reading Elaborazione by its id.
 */
public class GetMessaggiByUltimaElaborazioneResponse extends BaseGetResponse<List<ElaborazioneMessaggio>> {

	private List<ElaborazioneMessaggio>listaMessaggi;

	/**
	 * @return the listaMessaggi
	 */
	public List<ElaborazioneMessaggio> getListaMessaggi() {
		return listaMessaggi;
	}

	/**
	 * @param listaMessaggi the listaMessaggi to set
	 */
	public void setListaMessaggi(List<ElaborazioneMessaggio> listaMessaggi) {
		this.listaMessaggi = listaMessaggi;
	}

	@Override
	protected List<ElaborazioneMessaggio> getEntity() {
		// TODO Auto-generated method stub
		return listaMessaggi;
	}




}
