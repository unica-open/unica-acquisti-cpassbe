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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DestinatarioEvasione;

public class PutDestinatarioEvasioneRequest implements BaseRequest {

	private DestinatarioEvasione destinatarioEvasione;

	/**
	 * Constructor
	 *
	 * @param testataOrdine the testataOrdine
	 */
	public PutDestinatarioEvasioneRequest(DestinatarioEvasione destinatarioEvasione) {
		this.destinatarioEvasione = destinatarioEvasione;
	}

	/**
	 * @return the destinatarioEvasione
	 */
	public DestinatarioEvasione getDestinatarioEvasione() {
		return destinatarioEvasione;
	}

	/**
	 * @param destinatarioEvasione the destinatarioEvasione to set
	 */
	public void setDestinatarioEvasione(DestinatarioEvasione destinatarioEvasione) {
		this.destinatarioEvasione = destinatarioEvasione;
	}



}
