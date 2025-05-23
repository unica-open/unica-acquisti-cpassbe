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


public class GetTestataRdaByAnnoENumResponse extends BaseGetResponse<TestataRda> {

	private TestataRda testataRda;

	/**
	 * @return the testataOrdine
	 */
	public TestataRda getTestataRda() {
		return testataRda;
	}

	/**
	 * @param testataOrdine the testataOrdine to set
	 */
	public void setTestataRda(TestataRda testataRda) {
		this.testataRda = testataRda;
	}

	@Override
	protected TestataRda getEntity() {
		return testataRda;
	}

}
