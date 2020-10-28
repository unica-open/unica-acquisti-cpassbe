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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BasePagedResponse;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedListImpl;

/**
 * Response for reading Intervento by its id.
 */
public class GetRicercaInterventiResponse extends BasePagedResponse<Intervento> {

	private PagedList<Intervento> interventi = new PagedListImpl<>();

	/**
	 * @return the interventi
	 */
	public PagedList<Intervento> getInterventi() {
		return interventi;
	}

	/**
	 * @param interventi the interventi to set
	 */
	public void setInterventi(PagedList<Intervento> interventi) {
		this.interventi = interventi != null ? interventi : new PagedListImpl<>();
	}

	@Override
	protected PagedList<Intervento> getEntity() {
		return interventi;
	}

}
