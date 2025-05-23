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

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetAcquistiVariatiRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetAcquistiVariatiResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.pba.AcquistoVariato;

/**
 * Lettura AcquistoVariato
 */
public class GetAcquistiVariatiService extends BaseDecodificaService<GetAcquistiVariatiRequest, GetAcquistiVariatiResponse> {
	/**
	 * Constructor
	 * @param configurationHelper the helper for the configuration
	 * @param decodificaDad the DAD for the decodifica
	 */
	public GetAcquistiVariatiService(ConfigurationHelper configurationHelper, DecodificaDad decodificaDad) {
		super(configurationHelper, decodificaDad);
	}

	@Override
	protected void execute() {
		final List<AcquistoVariato> av = decodificaDad.getAcquistiVariati();
		response.setAcquistiVariati(av);
	}

}
