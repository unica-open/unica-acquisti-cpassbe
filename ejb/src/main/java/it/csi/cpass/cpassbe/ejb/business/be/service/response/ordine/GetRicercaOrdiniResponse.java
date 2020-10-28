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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BasePagedResponse;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedListImpl;

/**
 * Response for reading Intervento by its id.
 */
public class GetRicercaOrdiniResponse extends BasePagedResponse<TestataOrdine> {

	private PagedList<TestataOrdine> testataOrdines = new PagedListImpl<>();

	/**
	 * @return the testataOrdines
	 */
	public PagedList<TestataOrdine> getTestataOrdines() {
		return testataOrdines;
	}

	/**
	 * @param interventi the interventi to set
	 */
	public void setTestataOrdines(PagedList<TestataOrdine> testataOrdines) {
		this.testataOrdines = testataOrdines != null ? testataOrdines : new PagedListImpl<>();
	}

	@Override
	protected PagedList<TestataOrdine> getEntity() {
		return testataOrdines;
	}

}
