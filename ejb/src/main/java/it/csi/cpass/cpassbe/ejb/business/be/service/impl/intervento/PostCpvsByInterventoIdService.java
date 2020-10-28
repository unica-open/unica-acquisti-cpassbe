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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento;

import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PostCpvsByInterventoIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PostCpvsByInterventoIdResponse;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Cpv;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;

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
		UUID interventoId = request.getIdIntervento();
		checkNotNull( interventoId,"interventoId");
	}

	@Override
	protected void execute() {
		Intervento intervento = interventoDad.getIntervento(request.getIdIntervento()).orElseThrow(() -> new NotFoundException("intervento"));		
		List<Cpv> listaCpv = request.getCpvs();
		//TODO da capire se serve ed eventualmente completare
	}

	
}
