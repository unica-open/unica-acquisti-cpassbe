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

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.rms.TestataRms;

public class GetTestataRmsByAnnoENumResponse extends BaseGetResponse<TestataRms> {

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

}
