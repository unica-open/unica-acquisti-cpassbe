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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetCpvRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetCpvResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;

/**
 * Gets the Cpvs
 */
public class GetCpvService extends BaseDecodificaService<GetCpvRequest, GetCpvResponse> {

	/**
	 * Constructor
	 * @param configurationHelper the helper for the configuration
	 * @param decodificaDad the DAD for the decodifica
	 */
	public GetCpvService(ConfigurationHelper configurationHelper, DecodificaDad decodificaDad) {
		super(configurationHelper, decodificaDad);
	}

	@Override
	protected void execute() {
		response.setCpvs(decodificaDad.getCpvs());
	}

}
