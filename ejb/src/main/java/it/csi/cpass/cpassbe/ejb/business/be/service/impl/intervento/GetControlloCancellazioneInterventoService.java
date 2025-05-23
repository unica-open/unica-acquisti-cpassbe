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

import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.GetControlloCancellazioneInterventoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.GetControlloCancellazioneInterventoResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassPba;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Saves an stato Intervento
 */
public class GetControlloCancellazioneInterventoService extends BaseInterventoService<GetControlloCancellazioneInterventoRequest, GetControlloCancellazioneInterventoResponse> {

	public GetControlloCancellazioneInterventoService(ConfigurationHelper configurationHelper, InterventoDad interventoDad) {
		super(configurationHelper, interventoDad);
	}

	@Override
	protected void checkServiceParams() {
	}

	@Override
	protected void execute() {
		final Intervento intervento = request.getInterventi().get(0);
		final Intervento interventoAttuale = isEntityPresent(() -> interventoDad.getInterventoOpt(intervento.getId()), "intervento");
		final List<Intervento> list = interventoDad.findInterventiByCUI(interventoAttuale.getCui(), CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte().getId());
		response.setControllo(Boolean.FALSE);
		if(list.size()!=1) {
			final List<ApiError> apiErrors = new ArrayList<>();
			final ApiError err = new ApiError();
			err.setCode(MsgCpassPba.PBAACQE0078.getCode());
			err.setErrorMessage(MsgCpassPba.PBAACQE0078.getMessage());
			apiErrors.add(err);
			response.setApiErrors(apiErrors );
		}
		response.setControllo(Boolean.TRUE);
	}

}
