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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.magazzino;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BasePagedResponse;
import it.csi.cpass.cpassbe.lib.dto.mag.Magazzino;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedListImpl;

/**
 * Response for reading Rmss.
 */
public class GetRicercaMagazzinoResponse extends BasePagedResponse<Magazzino> {

	private PagedList<Magazzino> magazzinos = new PagedListImpl<>();

	/**
	 * @return the magazzino
	 */
	public PagedList<Magazzino> getMagazzinos() {
		return magazzinos;
	}

	/**
	 * @param magazzinos the magazzino to set
	 */
	public void setMagazzinos(PagedList<Magazzino> magazzinos) {
		this.magazzinos = magazzinos != null ? magazzinos : new PagedListImpl<>();
	}

	@Override
	protected PagedList<Magazzino> getEntity() {
		return magazzinos;
	}

}
