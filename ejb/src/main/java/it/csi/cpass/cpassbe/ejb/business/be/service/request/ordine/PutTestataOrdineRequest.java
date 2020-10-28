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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;

/**
 * Request for putting the TestataOrdine
 */
public class PutTestataOrdineRequest implements BaseRequest {

	private final TestataOrdine testataOrdine;

	/**
	 * Constructor
	 * 
	 * @param testataOrdine the testataOrdine
	 */
	public PutTestataOrdineRequest(TestataOrdine testataOrdine) {
		this.testataOrdine = testataOrdine;
	}

	/**
	 * @return the testataOrdine
	 */
	public TestataOrdine getTestataOrdine() {
		return testataOrdine;
	}
}
