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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.rms;

import it.csi.cpass.cpassbe.ejb.business.be.dad.RmsDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.rms.PutTestataRmsRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.rms.PutTestataRmsResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.rms.TestataRms;

/**
 * Saves an TestataRms
 */
public class PutTestataRmsService extends BaseRmsService<PutTestataRmsRequest, PutTestataRmsResponse> {

	private TestataRms testataRms;

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param rmsDad the rms DAD
	 */
	public PutTestataRmsService(ConfigurationHelper configurationHelper, RmsDad rmsDad) {
		super(configurationHelper, rmsDad);
	}

	@Override
	protected void checkServiceParams() {
		testataRms = request.getTestataRms();
		checkModel(request.getTestataRms(), "testataRms");
	}

	@Override
	protected void execute() {
		rmsDad.updateTestataRms(testataRms);
	}

}
