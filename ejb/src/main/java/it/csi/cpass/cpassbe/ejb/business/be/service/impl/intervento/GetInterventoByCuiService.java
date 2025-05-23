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

import java.util.Optional;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.GetInterventoByCuiRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.GetInterventoByCuiResponse;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.ejb.validatori.Validazioni;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassPba;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;

/**
 * Retrieves an intervento by its id
 */
public class GetInterventoByCuiService extends BaseInterventoService<GetInterventoByCuiRequest, GetInterventoByCuiResponse> {

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param interventoDad the intervento DAD
	 */
	public GetInterventoByCuiService(ConfigurationHelper configurationHelper, InterventoDad interventoDad) {
		super(configurationHelper, interventoDad);
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getCui(), "cui");
		checkNotNull(request.getIdProgramma(), "Id Programma");
	}

	@Override
	protected void execute() {
		final String cui = request.getCui();
		final UUID idProgramma = request.getIdProgramma();
		checkBusinessCondition(new Validazioni().controlloCui(cui), MsgCpassPba.PBAACQE0018.getError());
		final Optional<Intervento> interventoOpt = interventoDad.findInterventoByCUI(cui, idProgramma);
		checkBusinessCondition(interventoOpt.isPresent(), MsgCpassPba.PBAACQE0019.getError());
		final Intervento intervento = interventoOpt.orElseThrow(() -> new NotFoundException("intervento"));
		setRisorsaCapitalePrivato(intervento);
		response.setIntervento(intervento);
	}

}
