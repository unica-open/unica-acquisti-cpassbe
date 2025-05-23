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

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BasePostResponse;
import it.csi.cpass.cpassbe.lib.dto.rms.TestataRms;

/**
 * Response for posting testataRms.
 */
public class PostTestataRmsResponse extends BasePostResponse<UUID, TestataRms> {

	private TestataRms testataRms;

	/**
	 * @return the testataRms
	 */
	public TestataRms getTestataRms() {
		return testataRms;
	}

	/**
	 * @param testataRms the testataRms to set
	 */
	public void setTestataRms(TestataRms testataRms) {
		this.testataRms = testataRms;
	}

	@Override
	protected TestataRms getEntity() {
		return testataRms;
	}

	@Override
	protected String getBaseUri() {
		return "rms";
	}

}
