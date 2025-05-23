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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.rda;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.ord.rda.TestataRda;

/**
 * Request for posting the testataRda
 */
public class PutTestataRdaRequest implements BaseRequest {

	private final TestataRda testataRda;

	/**
	 * Constructor
	 *
	 * @param testataRda the testataRda
	 */
	public PutTestataRdaRequest(TestataRda testataRda) {
		this.testataRda = testataRda;
	}

	/**
	 * @return the testataRda
	 */
	public TestataRda getTestataRda() {
		return testataRda;
	}

}
