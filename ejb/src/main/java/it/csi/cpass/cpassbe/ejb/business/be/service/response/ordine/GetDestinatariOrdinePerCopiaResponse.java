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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;

public class GetDestinatariOrdinePerCopiaResponse extends BaseGetResponse<List<Destinatario>> {

	private List<Destinatario> destinatari;

	/**
	 * @return the destinatari
	 */
	public List<Destinatario> getDestinatari() {
		return destinatari;
	}

	/**
	 * @param destinatari the destinatari to set
	 */
	public void setDestinatari(List<Destinatario> destinatari) {
		this.destinatari = destinatari;
	}

	@Override
	protected List<Destinatario> getEntity() {
		return destinatari;
	}

}
