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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.rms;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.rms.TestataRms;

/**
 * Request for posting the testataRms
 */
public class PostTestataRmsRequest implements BaseRequest {

	private final TestataRms testataRms;

	/**
	 * Constructor
	 * @param intervento the testataRms
	 */
	public PostTestataRmsRequest(TestataRms testataRms) {
		this.testataRms = testataRms;
	}

	/**
	 * @return the testataRms
	 */
	public TestataRms getTestataRms() {
		return testataRms;
	}
}
