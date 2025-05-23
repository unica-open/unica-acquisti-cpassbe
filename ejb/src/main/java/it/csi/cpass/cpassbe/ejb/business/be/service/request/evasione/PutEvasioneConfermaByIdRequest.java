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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione;

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.TestataEvasione;

public class PutEvasioneConfermaByIdRequest implements BaseRequest {

	private final UUID id;
	private final TestataEvasione testataEvasione;

	/**
	 * Constructor
	 *
	 * @param testataEvasione the testataEvasione
	 */
	public PutEvasioneConfermaByIdRequest(UUID id, TestataEvasione testataEvasione) {
		this.id = id;
		this.testataEvasione = testataEvasione;
	}

	/**
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * @return the testataEvasione
	 */
	public TestataEvasione getTestataEvasione() {
		return testataEvasione;
	}

}
