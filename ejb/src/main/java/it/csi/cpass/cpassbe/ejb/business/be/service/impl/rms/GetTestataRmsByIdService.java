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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.rms;

import it.csi.cpass.cpassbe.ejb.business.be.dad.RmsDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.rms.GetTestataRmsByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.rms.GetTestataRmsByIdResponse;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.rms.TestataRms;

public class GetTestataRmsByIdService extends BaseRmsService<GetTestataRmsByIdRequest, GetTestataRmsByIdResponse> {

	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 * @param rmsDad              the rms dad
	 */
	public GetTestataRmsByIdService(ConfigurationHelper configurationHelper, RmsDad rmsDad) {
		super(configurationHelper, rmsDad);
	}

	/**
	 * Checks own service parameters
	 *
	 * @in case of errors in param check
	 */
	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getId(), "id", true);
	}

	@Override
	protected void execute() {
		final TestataRms testataRms = rmsDad.getTestataRmsById(request.getId()).orElseThrow(() -> new NotFoundException("Testata Rms"));
		if(testataRms.getSettoreIndirizzo().getPrincipale()) {
			testataRms.setSede(CpassEnum.PRINCIPALE.getCostante());
		} else {
			testataRms.setSede(testataRms.getSettoreIndirizzo().getDescrizione());
		}
		response.setTestataRms(testataRms);
	}
}
