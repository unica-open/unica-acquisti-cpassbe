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
import it.csi.cpass.cpassbe.lib.dto.Ods;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedListImpl;

/**
 * Response for reading Intervento by its id.
 */
public class GetRicercaOggettiSpesaResponse extends BasePagedResponse<Ods> {

	private PagedList<Ods> oggettiSpesa = new PagedListImpl<>();

	/**
	 * @return the interventi
	 */
	public PagedList<Ods> getOggettiSpesa() {
		return oggettiSpesa;
	}

	/**
	 * @param interventi the interventi to set
	 */
	public void setOggettiSpesa(PagedList<Ods> oggettiSpesa) {
		this.oggettiSpesa = oggettiSpesa != null ? oggettiSpesa : new PagedListImpl<>();
	}

	@Override
	protected PagedList<Ods> getEntity() {
		return oggettiSpesa;
	}

}
