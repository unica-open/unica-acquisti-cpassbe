/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.magazzino;

import it.csi.cpass.cpassbe.ejb.business.be.dad.MagazzinoDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.magazzino.GetMagazzinoByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.magazzino.GetMagazzinoByIdResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;

public class GetMagazzinoByIdService extends BaseMagazzinoService<GetMagazzinoByIdRequest, GetMagazzinoByIdResponse> {

	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 * @param magazzinoDad              the magazzino dad
	 */
	public GetMagazzinoByIdService(ConfigurationHelper configurationHelper, MagazzinoDad magazzinoDad) {
		super(configurationHelper, magazzinoDad);
	}

	/**
	 * Checks own service parameters
	 *
	 * @in case of errors in param check
	 */
	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getId(), "id", Boolean.TRUE);
	}

	@Override
	protected void execute() {
		response.setMagazzino(magazzinoDad.getMagazzinoById(request.getId()).orElse(null));
	}
}
