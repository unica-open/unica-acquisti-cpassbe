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

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseDeleteResponse;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.TestataEvasione;

public class DeleteEvasioneResponse extends BaseDeleteResponse {

	private TestataEvasione testataEvasione;

	/**
	 * @return the testataEvasione
	 */
	public TestataEvasione getTestataEvasione() {
		return testataEvasione;
	}

	/**
	 * @param testataEvasione the testataEvasione to set
	 */
	public void setTestataEvasione(TestataEvasione testataEvasione) {
		this.testataEvasione = testataEvasione;
	}

}
