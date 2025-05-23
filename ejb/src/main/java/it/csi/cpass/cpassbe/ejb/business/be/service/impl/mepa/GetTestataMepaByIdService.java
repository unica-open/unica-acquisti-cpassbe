/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - WAR submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.mepa;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ScaricoMepaTestataDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.mepa.GetTestataMepaByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.mepa.GetTestataMepaByIdResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ord.mepa.ScaricoMepaTestata;

public class GetTestataMepaByIdService
extends BaseScaricoMepaService<GetTestataMepaByIdRequest, GetTestataMepaByIdResponse> {

	/**
	 * Constructor
	 *
	 * @param configurationHelper   the configuration helper
	 * @param scaricoMepaTestataDad the scarico MEPA dad
	 */
	public GetTestataMepaByIdService(ConfigurationHelper configurationHelper, ScaricoMepaTestataDad scaricoMepaTestataDad, DecodificaDad decodificaDad) {
		super(configurationHelper, scaricoMepaTestataDad, decodificaDad);
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getId(), "id");
	}

	@Override
	protected void execute() {

		final ScaricoMepaTestata scaricoMepaTestata = scaricoMepaTestataDad.getTestataOrdineMepaById(request.getId());
		response.setScaricoMepaTestata(scaricoMepaTestata);
	}
}
