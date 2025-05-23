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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.GetInterventoInProgrammaFuturoByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.GetInterventoInProgrammaFuturoByIdResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Retrieves an intervento by its id
 */
public class GetInterventoInProgrammaFuturoByIdService extends BaseInterventoService<GetInterventoInProgrammaFuturoByIdRequest, GetInterventoInProgrammaFuturoByIdResponse> {

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param interventoDad the intervento DAD
	 */
	public GetInterventoInProgrammaFuturoByIdService(ConfigurationHelper configurationHelper, InterventoDad interventoDad) {
		super(configurationHelper, interventoDad);
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getId(), "id");
	}

	@Override
	protected void execute() {

		final Optional<Intervento> intervento = interventoDad.getInterventoOpt(request.getId());

		List<Intervento> interventi = new ArrayList<>();
		if(intervento.isPresent()) {
			final UUID enteId = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte().getId();
			interventi = interventoDad.getInterventiSuprogrammiFuturi(intervento.get().getCui(),intervento.get().getProgramma().getAnno(),enteId);
		}
		//setRisorsaCapitalePrivato(intervento);
		response.setInterventi(interventi);
	}

}
