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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.common;

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.GetSettoreTreeByEnteRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.GetSettoreTreeByEnteResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;

/**
 * Gets the Settores by ente
 */
public class GetSettoreTreeByEnteService extends BaseCommonService<GetSettoreTreeByEnteRequest, GetSettoreTreeByEnteResponse> {
	
	/**
	 * Constructor
	 * @param configurationHelper the helper for the configuration
	 * @param commonDad the DAD for the common
	 */
	public GetSettoreTreeByEnteService(ConfigurationHelper configurationHelper, CommonDad commonDad) {
		super(configurationHelper, commonDad);
	}

	@Override
	protected void execute() {
		response.setSettores(commonDad.getSettoreTreeByEnte(request.getEnteId()));
	}

}
