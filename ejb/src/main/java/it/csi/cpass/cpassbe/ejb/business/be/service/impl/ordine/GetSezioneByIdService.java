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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine;

import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.GetSezioneByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.GetSezioneByIdResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;

public class GetSezioneByIdService extends BaseOrdineService<GetSezioneByIdRequest, GetSezioneByIdResponse> {

	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 * @param magazzinoDad              the magazzino dad
	 */
	public GetSezioneByIdService(ConfigurationHelper configurationHelper, TestataOrdineDad testataOrdineDad,SettoreDad settoreDad) {
		super(configurationHelper, testataOrdineDad, settoreDad);
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
		response.setSezione(testataOrdineDad.getSezioneById(request.getId()).orElse(null));
	}
}
