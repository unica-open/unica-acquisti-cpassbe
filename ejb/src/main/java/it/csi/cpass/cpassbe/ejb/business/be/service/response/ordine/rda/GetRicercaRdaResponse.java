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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.rda;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BasePagedResponse;
import it.csi.cpass.cpassbe.lib.dto.ord.rda.TestataRda;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedListImpl;

/**
 * Response for reading Rmss.
 */
public class GetRicercaRdaResponse extends BasePagedResponse<TestataRda> {

	private PagedList<TestataRda> testataRdas = new PagedListImpl<>();

	/**
	 * @return the testataRda
	 */
	public PagedList<TestataRda> getTestataRdas() {
		return testataRdas;
	}

	/**
	 * @param testataRdas the testataRdas to set
	 */
	public void setTestataRdas(PagedList<TestataRda> testataRdas) {
		this.testataRdas = testataRdas != null ? testataRdas : new PagedListImpl<>();
	}

	@Override
	protected PagedList<TestataRda> getEntity() {
		return testataRdas;
	}

}
