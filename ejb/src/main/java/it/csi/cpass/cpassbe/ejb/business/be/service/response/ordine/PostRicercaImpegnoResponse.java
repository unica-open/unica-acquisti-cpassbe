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
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedListImpl;

/**
 * Response for reading Impegno paged.
 */
public class PostRicercaImpegnoResponse extends BasePagedResponse<Impegno> {

	private PagedList<Impegno> impegni = new PagedListImpl<>();

	/**
	 * @return the impegni
	 */
	public PagedList<Impegno> getImpegni() {
		return impegni;
	}

	/**
	 * @param impegni the impegni to set
	 */
	public void setImpegni(PagedList<Impegno> impegni) {
		this.impegni = impegni != null ? impegni : new PagedListImpl<>();
	}

	@Override
	protected PagedList<Impegno> getEntity() {
		return impegni;
	}

}
