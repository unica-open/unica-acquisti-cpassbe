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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BasePagedResponse;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedListImpl;

public class PostRicercaSettoreResponse extends BasePagedResponse<Settore> {

	private PagedList<Settore> list = new PagedListImpl<>();

	/**
	 * @return the settori
	 */
	public PagedList<Settore> getSettori() {
		return list;
	}

	/**
	 * @param settori the settori to set
	 */
	public void setSettori(PagedList<Settore> settori) {
		this.list = settori != null ? settori : new PagedListImpl<>();
	}

	@Override
	protected PagedList<Settore> getEntity() {
		return list;
	}

}
