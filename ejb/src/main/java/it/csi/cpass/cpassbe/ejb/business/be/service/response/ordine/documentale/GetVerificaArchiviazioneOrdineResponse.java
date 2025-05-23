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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.documentale;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;

/**
 * Response for reading ProtocolloOrigine by its id.
 */
public class GetVerificaArchiviazioneOrdineResponse extends BaseGetResponse<Boolean> {

	private Boolean archiviato;

	/**
	 * @return the archiviato
	 */
	public Boolean getArchiviato() {
		return archiviato;
	}

	/**
	 * @param archiviato the archiviato to set
	 */
	public void setArchiviato(Boolean archiviato) {
		this.archiviato = archiviato;
	}

	@Override
	protected Boolean getEntity() {
		// TODO Auto-generated method stub
		return archiviato;
	}




}
