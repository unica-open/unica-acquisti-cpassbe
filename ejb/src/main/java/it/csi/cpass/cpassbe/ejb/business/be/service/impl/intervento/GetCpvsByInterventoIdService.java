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

import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.GetCpvsByInterventoIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.GetCpvsByInterventoIdResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Cpv;

/**
 * Retrieves an GetRupsByInterventoId by its id
 */
public class GetCpvsByInterventoIdService extends BaseService<GetCpvsByInterventoIdRequest, GetCpvsByInterventoIdResponse> {

	private final InterventoDad interventoDad;
	public GetCpvsByInterventoIdService(ConfigurationHelper configurationHelper, InterventoDad interventoDad) {
		super(configurationHelper);
		this.interventoDad = interventoDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getId(), "id");
	}

	@Override
	protected void execute() {
		final List<Cpv> cpvs = interventoDad.getCpvsByInterventoId(request.getId());
		response.setCpvs(cpvs);
	}

}
