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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.settore;

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.settore.GetCdcValidiRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.settore.GetCdcValidiResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Gets the Ufficios by settore
 */
public class GetCdcValidiService extends BaseSettoreService<GetCdcValidiRequest, GetCdcValidiResponse> {

	public GetCdcValidiService(ConfigurationHelper configurationHelper, SettoreDad settoreDad) {
		super(configurationHelper, settoreDad);
	}

	@Override
	protected void execute() {
		final UUID enteId = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte().getId();
		response.setListaCdc(settoreDad.getCdcValidiByEnte(enteId));
	}

}
