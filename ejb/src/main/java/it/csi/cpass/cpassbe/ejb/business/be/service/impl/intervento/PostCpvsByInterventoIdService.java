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

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PostCpvsByInterventoIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PostCpvsByInterventoIdResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;

/**
 * Saves an Intervento
 */
public class PostCpvsByInterventoIdService extends BaseInterventoService<PostCpvsByInterventoIdRequest, PostCpvsByInterventoIdResponse> {


	/**
	 *
	 * @param configurationHelper
	 * @param interventoDad
	 */
	public PostCpvsByInterventoIdService(ConfigurationHelper configurationHelper, InterventoDad interventoDad) {
		super(configurationHelper, interventoDad);
	}

	@Override
	protected void checkServiceParams() {
		final UUID interventoId = request.getIdIntervento();
		checkNotNull( interventoId,"interventoId");
	}

	@Override
	protected void execute() {
		//interventoDad.getIntervento(request.getIdIntervento()).orElseThrow(() -> new NotFoundException("intervento"));
		//request.getCpvs();
		// Servizio non usato
	}
}
