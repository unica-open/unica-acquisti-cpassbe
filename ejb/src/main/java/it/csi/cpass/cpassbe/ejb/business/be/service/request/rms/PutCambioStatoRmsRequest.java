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

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.rms.TestataRms;

/**
 * Request for putting the TestataRms
 */
public class PutCambioStatoRmsRequest implements BaseRequest {

	private final UUID id;
	private final String statoCode;
	private final TestataRms testataRms;

	/**
	 * Constructor
	 * @param testataRms the testataRms
	 */
	public PutCambioStatoRmsRequest(UUID id,String statoCode, TestataRms testataRms ) {
		this.id = id;
		this.statoCode = statoCode;
		this.testataRms = testataRms;
	}

	/**
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * @return the statoCode
	 */
	public String getStatoCode() {
		return statoCode;
	}

	public TestataRms getTestataRms() {
		return testataRms;
	}




}
