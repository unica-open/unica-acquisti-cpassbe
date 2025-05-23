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

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BasePostResponse;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdineWrapper;

/**
 * Response for saving TestataOrdine from rda
 */
public class PostTestataOrdineDerivatoResponse extends BasePostResponse<UUID, TestataOrdineWrapper> {

	private TestataOrdine testataOrdine;

	/**
	 * @return the testataOrdine
	 */
	public TestataOrdine getTestataOrdine() {
		return testataOrdine;
	}

	/**
	 * @param testataOrdine the testataOrdine to set
	 */
	public void setTestataOrdine(TestataOrdine testataOrdine) {
		this.testataOrdine = testataOrdine;
	}

	@Override
	protected TestataOrdineWrapper getEntity() {
		return new TestataOrdineWrapper(testataOrdine,apiWarnings);
	}

	@Override
	protected String getBaseUri() {
		return "testataOrdine";
	}

}
