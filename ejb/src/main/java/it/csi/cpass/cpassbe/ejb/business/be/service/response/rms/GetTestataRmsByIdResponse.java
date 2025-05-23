/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.service.response.rms;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.rms.TestataRms;

public class GetTestataRmsByIdResponse extends BaseGetResponse<TestataRms> {

	private TestataRms testataRms;

	public TestataRms getTestataRms() {
		return testataRms;
	}

	public void setTestataRms(TestataRms testataRms) {
		this.testataRms = testataRms;
	}

	/**
	 * @return the entity
	 */
	@Override
	protected TestataRms getEntity() {
		return testataRms;
	}
}
