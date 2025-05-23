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

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BasePostResponse;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;

public class PostDestinatarioOrdineResponse extends BasePostResponse<UUID, Destinatario> {

	private Destinatario destinatario;

	/**
	 * @return the testataOrdine
	 */
	public Destinatario getDestinatario() {
		return destinatario;
	}

	/**
	 * @param testataOrdine the testataOrdine to set
	 */
	public void setDestinatario(Destinatario destinatario) {
		this.destinatario = destinatario;
	}

	@Override
	protected Destinatario getEntity() {
		return destinatario;
	}

	@Override
	protected String getBaseUri() {
		return "destinatario";
	}

}
