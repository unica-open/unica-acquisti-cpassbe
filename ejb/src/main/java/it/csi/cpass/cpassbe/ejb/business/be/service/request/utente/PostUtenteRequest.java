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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.utente;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.Utente;

/**
 * Request for posting the utente
 */
public class PostUtenteRequest implements BaseRequest {

	private final Utente utente;

	/**
	 * Constructor
	 * @param utente the utente
	 */
	public PostUtenteRequest(Utente utente) {
		this.utente = utente;
	}

	/**
	 * @return the Utente
	 */
	public Utente getUtente() {
		return utente;
	}
}
