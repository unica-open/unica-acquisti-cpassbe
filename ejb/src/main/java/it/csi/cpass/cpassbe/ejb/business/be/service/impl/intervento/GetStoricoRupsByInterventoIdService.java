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

import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.GetStoricoRupsByInterventoIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.GetStoricoRupsByInterventoIdResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.pba.StoricoInterventoRup;

/**
 * Retrieves an GetStoricoRupsByInterventoId by its id
 */
public class GetStoricoRupsByInterventoIdService extends BaseService<GetStoricoRupsByInterventoIdRequest, GetStoricoRupsByInterventoIdResponse> {

    private InterventoDad interventoDad;
	public GetStoricoRupsByInterventoIdService(ConfigurationHelper configurationHelper, InterventoDad interventoDad) {
		super(configurationHelper);
		this.interventoDad = interventoDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getIdIntervento(), "id");		
	}

	@Override
	protected void execute() {
		List<StoricoInterventoRup> lista = interventoDad.getStoricoRupsByInterventoIdService(request.getIdIntervento());
		response.setInterventoStoricoRup(lista);
	}

}
