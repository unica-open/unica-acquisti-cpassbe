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
import it.csi.cpass.cpassbe.lib.dto.pba.InterventoImporti;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedListImpl;

/**
 * Response for reading InterventoImporti by the id of the parent Intervento.
 */
public class GetInterventoImportiByInterventoResponse extends BasePagedResponse<InterventoImporti> {

	private PagedList<InterventoImporti> interventiImporti = new PagedListImpl<>();

	/**
	 * @return the interventiImporti
	 */
	public PagedList<InterventoImporti> getInterventiImporti() {
		return interventiImporti;
	}

	/**
	 * @param interventiImporti the interventiImporti to set
	 */
	public void setInterventiImporti(PagedList<InterventoImporti> interventiImporti) {
		this.interventiImporti = interventiImporti != null ? interventiImporti : new PagedListImpl<>();
	}

	@Override
	protected PagedList<InterventoImporti> getEntity() {
		return interventiImporti;
	}

}
