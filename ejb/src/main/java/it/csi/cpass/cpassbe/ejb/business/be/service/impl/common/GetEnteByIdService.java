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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.common;

import it.csi.cpass.cpassbe.ejb.business.be.dad.EnteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ente.GetEnteByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ente.GetEnteByIdResponse;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Ente;

/**
 * Retrieves an ente by its id
 */
public class GetEnteByIdService extends BaseEnteService<GetEnteByIdRequest, GetEnteByIdResponse> {

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param enteDad the ente DAD
	 */
	public GetEnteByIdService(ConfigurationHelper configurationHelper, EnteDad enteDad) {
		super(configurationHelper, enteDad);
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getId(), "id");
	}

	@Override
	protected void execute() {
		final Ente ente = enteDad.getEnteById(request.getId()).orElseThrow(() -> new NotFoundException("ente"));
		response.setEnte(ente);
	}

}
