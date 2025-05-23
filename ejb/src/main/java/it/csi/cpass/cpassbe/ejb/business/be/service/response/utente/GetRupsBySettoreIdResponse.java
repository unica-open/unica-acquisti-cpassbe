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

import java.util.ArrayList;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.Utente;

/**
 * Response for reading Intervento by its id.
 */
public class GetRupsBySettoreIdResponse extends BaseGetResponse<List<Utente>> {

	private List<Utente> utenteRup;

	/**
	 * @return the utenteRup
	 */
	public List<Utente> getUtenteRup() {
		return utenteRup;
	}

	/**
	 * @param utenteRup the utenteRup to set
	 */
	public void setUtenteRup(List<Utente> utenteRup) {
		this.utenteRup = utenteRup != null ? utenteRup : new ArrayList<>();

	}

	@Override
	protected List<Utente> getEntity() {
		return utenteRup;
	}

}
