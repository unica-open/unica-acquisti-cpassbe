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

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.ord.rda.TestataRda;

/**
 * Response for reading TestataOrdine by its id.
 */
public class GetTestataRdaByIdResponse extends BaseGetResponse<TestataRda> {

	private TestataRda testatarda;

	/**
	 * @return the testataRda
	 */
	public TestataRda getTestataRda() {
		return testatarda;
	}

	/**
	 * @param testataOrdine the testataRda to set
	 */
	public void setTestataRda(TestataRda testatarda) {
		this.testatarda = testatarda;
	}

	@Override
	protected TestataRda getEntity() {
		return testatarda;
	}

}
