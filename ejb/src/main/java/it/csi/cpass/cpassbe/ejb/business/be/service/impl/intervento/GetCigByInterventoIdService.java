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
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.GetCigByInterventoIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.GetCigByInterventoIdResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.pba.InterventoCig;

/**
 * Retrieves an intervento by its id
 */
public class GetCigByInterventoIdService extends BaseInterventoService<GetCigByInterventoIdRequest, GetCigByInterventoIdResponse> {

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param interventoDad the intervento DAD
	 */
	public GetCigByInterventoIdService(ConfigurationHelper configurationHelper, InterventoDad interventoDad) {
		super(configurationHelper, interventoDad);
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getId(), "id intervento");
	}

	@Override
	protected void execute() {
		final UUID idIntervento = request.getId();
		final List<InterventoCig> lista = interventoDad.getCigByInterventoId(idIntervento);
		response.setInterventiCig(lista);
	}

}
