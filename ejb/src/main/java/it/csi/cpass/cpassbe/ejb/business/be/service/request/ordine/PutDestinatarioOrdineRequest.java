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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;

public class PutDestinatarioOrdineRequest implements BaseRequest {

	private final Destinatario destinatario;

	/**
	 * Constructor
	 *
	 * @param testataOrdine the testataOrdine
	 */
	public PutDestinatarioOrdineRequest(Destinatario destinatario) {
		this.destinatario = destinatario;
	}

	/**
	 * @return the testataOrdine
	 */
	public Destinatario getOrdineDestinatario() {
		return destinatario;
	}

}
