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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.mepa;

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BasePostResponse;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;

public class PostOrdineMepaResponse extends BasePostResponse<UUID, TestataOrdine> {
	private TestataOrdine testataOrdine;

	public TestataOrdine getTestataOrdine() {
		return testataOrdine;
	}

	public void setTestataOrdine(TestataOrdine testataOrdine) {
		this.testataOrdine = testataOrdine;
	}

	@Override
	protected String getBaseUri() {
		return "postOrdineMepa";
	}

	@Override
	protected TestataOrdine getEntity() {
		return testataOrdine;
	}
}
