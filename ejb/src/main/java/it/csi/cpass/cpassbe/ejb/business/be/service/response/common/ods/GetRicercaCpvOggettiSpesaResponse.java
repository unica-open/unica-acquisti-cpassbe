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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.common.ods;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BasePagedResponse;
import it.csi.cpass.cpassbe.lib.dto.Cpv;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedListImpl;

/**
 * Response for reading Intervento by its id.
 */
public class GetRicercaCpvOggettiSpesaResponse extends BasePagedResponse<Cpv> {

	private PagedList<Cpv> listaCpvOds = new PagedListImpl<>();

	/**
	 * @return the list cpv
	 */
	public PagedList<Cpv> getListaCpvOds() {
		return listaCpvOds;
	}
	/**
	 *
	 * @param value
	 */
	public void setListaCpvOds(PagedList<Cpv> listaCpvOds) {
		this.listaCpvOds = listaCpvOds != null ? listaCpvOds : new PagedListImpl<>();
	}

	@Override
	protected PagedList<Cpv> getEntity() {
		return listaCpvOds;
	}

}
