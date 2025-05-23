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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.evasione;

import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.PutTestataEvasioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.PutTestataEvasioneResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.TestataEvasione;

/**
 * Saves an TestataEvasione
 */
public class PutTestataEvasioneService extends BaseService<PutTestataEvasioneRequest, PutTestataEvasioneResponse> {

	private final TestataEvasioneDad testataEvasioneDad;

	/**
	 * Constructor
	 *
	 * @param configurationHelper
	 * @param testataEvasioneDad
	 */
	public PutTestataEvasioneService(ConfigurationHelper configurationHelper, TestataEvasioneDad testataEvasioneDad) {
		super(configurationHelper);
		this.testataEvasioneDad = testataEvasioneDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getTestataEvasione(), "testataEvasione", Boolean.TRUE);
	}

	@Override
	protected void execute() {
		TestataEvasione testataEvasione = request.getTestataEvasione();
		testataEvasione = testataEvasioneDad.updateTestataEvasione(testataEvasione);
		response.setTestataEvasione(testataEvasione);
	}

}
