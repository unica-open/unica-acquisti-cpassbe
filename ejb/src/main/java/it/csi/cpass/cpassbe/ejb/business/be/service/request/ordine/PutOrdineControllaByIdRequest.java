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

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;

public class PutOrdineControllaByIdRequest implements BaseRequest {

	private final UUID id;
	private final TestataOrdine testataOrdine;

	/**
	 * Constructor
	 * 
	 * @param testataOrdine the testataOrdine
	 */
	public PutOrdineControllaByIdRequest(UUID id, TestataOrdine testataOrdine) {
		this.id = id;
		this.testataOrdine = testataOrdine;
	}

	/**
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * @return the testataOrdine
	 */
	public TestataOrdine getTestataOrdine() {
		return testataOrdine;
	}

}
