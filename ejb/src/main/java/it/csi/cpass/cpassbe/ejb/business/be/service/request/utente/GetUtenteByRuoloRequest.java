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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.utente;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

/**
 * Request for get utente by settore
 */
public class GetUtenteByRuoloRequest implements BaseRequest {

	private final String ruoloCodice;

	/**
	 * Constructor
	 * @param ruoloCodice the ruolo codice
	 */
	public GetUtenteByRuoloRequest(String ruoloCodice) {
		this.ruoloCodice = ruoloCodice;
	}

	/**
	 * @return the ruoloCode
	 */
	public String getRuoloCodice() {
		return ruoloCodice;
	}
}
