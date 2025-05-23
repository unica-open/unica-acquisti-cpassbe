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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento;

import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.GetInterventiByCuiRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.GetInterventiByCuiResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Retrieves an intervento by its id
 */
public class GetInterventiByCuiService extends BaseInterventoService<GetInterventiByCuiRequest, GetInterventiByCuiResponse> {

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param interventoDad the intervento DAD
	 */
	public GetInterventiByCuiService(ConfigurationHelper configurationHelper, InterventoDad interventoDad) {
		super(configurationHelper, interventoDad);
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getCui(), "cui");
	}

	@Override
	protected void execute() {
		final String cui = request.getCui();
		final Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		final UUID enteId = settoreCorrente.getEnte().getId();
		final List<Intervento> lista = interventoDad.findInterventiByCUI(cui, enteId);
		response.setInterventi(lista);
	}

}
