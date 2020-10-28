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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BasePagedResponse;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.TestataEvasione;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedListImpl;

/**
 * Response for reading Evasione.
 */
public class GetRicercaEvasioniResponse extends BasePagedResponse<TestataEvasione> {

	private PagedList<TestataEvasione> testataEvasiones = new PagedListImpl<>();

	/**
	 * @return the testataOrdines
	 */
	public PagedList<TestataEvasione> getTestataEvasiones() {
		return testataEvasiones;
	}

	/**
	 * @param interventi the interventi to set
	 */
	public void setTestataEvasiones(PagedList<TestataEvasione> testataEvasiones) {
		this.testataEvasiones = testataEvasiones != null ? testataEvasiones : new PagedListImpl<>();
	}

	@Override
	protected PagedList<TestataEvasione> getEntity() {
		return testataEvasiones;
	}

}
