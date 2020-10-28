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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.utente;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BasePagedResponse;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedListImpl;

/**
 * Response for reading Utente by its id.
 */
public class GetUtentiResponse extends BasePagedResponse<Utente> {

	private PagedList<Utente> utenti = new PagedListImpl<>();

	/**
	 * @return the interventi
	 */
	public PagedList<Utente> getUtenti() {
		return utenti;
	}

	/**
	 * @param utenti the utenti to set
	 */
	public void setUtenti(PagedList<Utente> utenti) {
		this.utenti = utenti != null ? utenti : new PagedListImpl<>();
	}

	@Override
	protected PagedList<Utente> getEntity() {
		return utenti;
	}

}
