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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;

public class PostAnnullaTestataOrdineRequest implements BaseRequest {

	private final TestataOrdine testataOrdine;
	private final boolean bypassControlli;

	/**
	 * Constructor
	 *
	 * @param testataOrdine the testataOrdine
	 */
	public PostAnnullaTestataOrdineRequest(TestataOrdine testataOrdine, boolean bypassControlli) {
		this.testataOrdine = testataOrdine;
		this.bypassControlli = bypassControlli;
	}

	/**
	 * @return the testataOrdine
	 */
	public TestataOrdine getTestataOrdine() {
		return testataOrdine;
	}

	public boolean isBypassControlli() {
		return bypassControlli;
	}

}
