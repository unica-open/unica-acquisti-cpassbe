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
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetMotiviEsclusioneCigRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetMotiviEsclusioneCigResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.MotiviEsclusioneCig;

public class GetMotiviEsclusioneCigService extends BaseDecodificaService<GetMotiviEsclusioneCigRequest, GetMotiviEsclusioneCigResponse> {
	/**
	 * Constructor
	 * @param configurationHelper the helper for the configuration
	 * @param decodificaDad the DAD for the decodifica
	 */
	public GetMotiviEsclusioneCigService(ConfigurationHelper configurationHelper, DecodificaDad decodificaDad) {
		super(configurationHelper, decodificaDad);
	}

	@Override
	protected void execute() {
		final List<MotiviEsclusioneCig> lista = decodificaDad.getMotiviEsclusioneCigs();
		response.setMotiviEsclusioneCigs(lista);
	}

}
