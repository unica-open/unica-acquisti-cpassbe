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
import it.csi.cpass.cpassbe.lib.dto.ord.Sezione;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedListImpl;

public class GetRicercaSezioneResponse extends BasePagedResponse<Sezione> {

	private PagedList<Sezione> seziones = new PagedListImpl<>();

	/**
	 * @return the magazzino
	 */
	public PagedList<Sezione> getSezioneos() {
		return seziones;
	}

	/**
	 * @param magazzinos the magazzino to set
	 */
	public void setSeziones(PagedList<Sezione> seziones) {
		this.seziones = seziones != null ? seziones : new PagedListImpl<>();
	}

	@Override
	protected PagedList<Sezione> getEntity() {
		return seziones;
	}

}
