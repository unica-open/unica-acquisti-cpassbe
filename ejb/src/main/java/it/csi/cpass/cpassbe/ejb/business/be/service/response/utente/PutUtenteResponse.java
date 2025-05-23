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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.utente;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BasePutResponse;
import it.csi.cpass.cpassbe.lib.dto.Utente;

/**
 * Response for reading Intervento by its id.
 */
public class PutUtenteResponse extends BasePutResponse {
	private Utente utente;

	/**
	 * @return the utente
	 */
	public Utente getUtente() {
		return utente;
	}

	/**
	 * @param utente the utente to set
	 */
	public void setUtente(Utente utente) {
		this.utente = utente;
	}

}
