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
import it.csi.cpass.cpassbe.lib.dto.ListinoFornitore;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedListImpl;

public class PostRicercaListinoFornitoreResponse extends BasePagedResponse<ListinoFornitore> {

	private PagedList<ListinoFornitore> list = new PagedListImpl<>();

	/**
	 * @return the listinoFornitore
	 */
	public PagedList<ListinoFornitore> getListinoFornitore() {
		return list;
	}

	/**
	 * @param fornitori the fornitori to set
	 */
	public void setListinoFornitore(PagedList<ListinoFornitore> listinoFornitore) {
		this.list = listinoFornitore != null ? listinoFornitore : new PagedListImpl<>();
	}

	@Override
	protected PagedList<ListinoFornitore> getEntity() {
		return list;
	}
}
