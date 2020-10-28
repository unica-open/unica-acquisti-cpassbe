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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.common;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BasePagedResponse;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedListImpl;

/**
 * Response for reading list of Fornitore.
 */
public class PostRicercaFornitoreResponse extends BasePagedResponse<Fornitore> {

	private PagedList<Fornitore> list = new PagedListImpl<>();

	/**
	 * @return the fornitori
	 */
	public PagedList<Fornitore> getFornitori() {
		return list;
	}

	/**
	 * @param fornitori the fornitori to set
	 */
	public void setFornitori(PagedList<Fornitore> fornitori) {
		this.list = fornitori != null ? fornitori : new PagedListImpl<>();
	}

	@Override
	protected PagedList<Fornitore> getEntity() {
		return list;
	}

}
