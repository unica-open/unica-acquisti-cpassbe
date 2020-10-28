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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BasePagedResponse;
import it.csi.cpass.cpassbe.lib.dto.ord.Provvedimento;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedListImpl;

/**
 * Response for reading list of Provvedimento.
 */
public class PostRicercaProvvedimentoResponse extends BasePagedResponse<Provvedimento> {

	private PagedList<Provvedimento> provvedimenti = new PagedListImpl<>();

	/**
	 * @return the provvedimenti
	 */
	public PagedList<Provvedimento> getProvvedimenti() {
		return provvedimenti;
	}

	/**
	 * @param Provvedimenti the provvedimenti to set
	 */
	public void setProvvedimenti(PagedList<Provvedimento> provvedimenti) {
		this.provvedimenti = provvedimenti != null ? provvedimenti : new PagedListImpl<>();
	}

	@Override
	protected PagedList<Provvedimento> getEntity() {
		return provvedimenti;
	}

}
