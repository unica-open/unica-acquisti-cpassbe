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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.TestataEvasione;

/**
 * Request for posting the TestataEvasione
 */
public class PutTestataEvasioneRequest implements BaseRequest {

	private final TestataEvasione testataEvasione;

	/**
	 * Constructor
	 * 
	 * @param testataEvasione the testataEvasione
	 */
	public PutTestataEvasioneRequest(TestataEvasione testataEvasione) {
		this.testataEvasione = testataEvasione;
	}

	/**
	 * @return the testataEvasione
	 */
	public TestataEvasione getTestataEvasione() {
		return testataEvasione;
	}

}
