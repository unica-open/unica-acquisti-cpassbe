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

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BasePostResponse;
import it.csi.cpass.cpassbe.lib.dto.ord.rda.TestataRda;

/**
 * Response for reading TestataRda by its id.
 */
public class PutTestataRdaResponse extends BasePostResponse<UUID, TestataRda> {

	private TestataRda testataRda;

	/**
	 * @return the TestataRda
	 */
	public TestataRda getTestataRda() {
		return testataRda;
	}

	/**
	 * @param testataRda the testataRda to set
	 */
	public void setTestataRda(TestataRda testataRda) {
		this.testataRda = testataRda;
	}

	@Override
	protected TestataRda getEntity() {
		return testataRda;
	}

	@Override
	protected String getBaseUri() {
		return "testataRda";
	}

}
