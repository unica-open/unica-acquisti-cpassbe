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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetTipoEvasioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetTipoEvasioneResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;

/**
 * Gets the TipoOrdines
 */
public class GetTipoEvasioneService extends BaseDecodificaService<GetTipoEvasioneRequest, GetTipoEvasioneResponse> {
	
	/**
	 * Constructor
	 * @param configurationHelper the helper for the configuration
	 * @param decodificaDad the DAD for the decodifica
	 */
	public GetTipoEvasioneService(ConfigurationHelper configurationHelper, DecodificaDad decodificaDad) {
		super(configurationHelper, decodificaDad);
	}

	@Override
	protected void execute() {
		response.setTipoEvasiones(decodificaDad.getTipoEvasiones());
	}

}
