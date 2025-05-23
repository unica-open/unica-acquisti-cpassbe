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

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetTipoSettoreRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetTipoSettoreResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Gets the TipoOrdines
 */
public class GetTipoSettoreService extends BaseDecodificaService<GetTipoSettoreRequest, GetTipoSettoreResponse> {

	/**
	 * Constructor
	 * @param configurationHelper the helper for the configuration
	 * @param decodificaDad the DAD for the decodifica
	 */
	public GetTipoSettoreService(ConfigurationHelper configurationHelper, DecodificaDad decodificaDad) {
		super(configurationHelper, decodificaDad);
	}

	@Override
	protected void execute() {
		final UUID enteId = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte().getId();
		response.setTipoSettores(decodificaDad.getTipoSettores(enteId));
	}

}
