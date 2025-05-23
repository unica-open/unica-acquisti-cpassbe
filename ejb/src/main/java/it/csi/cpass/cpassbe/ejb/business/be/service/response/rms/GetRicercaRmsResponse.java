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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.rms;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BasePagedResponse;
import it.csi.cpass.cpassbe.lib.dto.rms.TestataRms;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedListImpl;

/**
 * Response for reading Rmss.
 */
public class GetRicercaRmsResponse extends BasePagedResponse<TestataRms> {

	private PagedList<TestataRms> testataRmss = new PagedListImpl<>();

	/**
	 * @return the testataRms
	 */
	public PagedList<TestataRms> getTestataRmss() {
		return testataRmss;
	}

	/**
	 * @param testataRmss the testataRms to set
	 */
	public void setTestataRmss(PagedList<TestataRms> testataRmss) {
		this.testataRmss = testataRmss != null ? testataRmss : new PagedListImpl<>();
	}

	@Override
	protected PagedList<TestataRms> getEntity() {
		return testataRmss;
	}

}
