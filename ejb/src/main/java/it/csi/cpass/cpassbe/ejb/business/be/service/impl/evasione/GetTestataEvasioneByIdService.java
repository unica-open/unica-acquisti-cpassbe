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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.evasione;

import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.GetTestataEvasioneByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.GetTestataEvasioneByIdResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.TestataEvasione;

/**
 * Retrieves an testataEvasione by its id
 */
public class GetTestataEvasioneByIdService extends BaseTestataEvasioneService<GetTestataEvasioneByIdRequest, GetTestataEvasioneByIdResponse> {

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the configuration helper
	 * @param testataEvasioneDad  the testataEvasione DAD
	 */
	public GetTestataEvasioneByIdService(ConfigurationHelper configurationHelper, TestataEvasioneDad testataEvasioneDad) {
		super(configurationHelper, testataEvasioneDad);
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getId(), "id");
	}

	@Override
	protected void execute() {
		// TestataEvasione testataEvasione = testataEvasioneDad.getTestataEvasione(request.getId()).orElseThrow(() -> new
		// NotFoundException("testataEvasione"));
		TestataEvasione testataEvasione = testataEvasioneDad.getTestataEvasioneModel(request.getId());
		response.setTestataEvasione(testataEvasione);
	}

}
