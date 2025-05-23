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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.mepa;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ScaricoMepaTestataDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.mepa.DeleteOrdineMepaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.mepa.DeleteOrdineMepaResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;

public class DeleteOrdineMepaService extends BaseScaricoMepaService<DeleteOrdineMepaRequest, DeleteOrdineMepaResponse> {

	/**
	 * Constructor
	 *
	 * @param configurationHelper   the configuration helper
	 * @param scaricoMepaTestataDad the scarico MEPA dad
	 * @param decodificaDad
	 */
	public DeleteOrdineMepaService(ConfigurationHelper configurationHelper, ScaricoMepaTestataDad scaricoMepaTestataDad, DecodificaDad decodificaDad) {
		super(configurationHelper, scaricoMepaTestataDad, decodificaDad);
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getIdOrdineDaCancellare(), "idOrdineDaCancellare");
	}

	/**
	 * Execution
	 */
	@Override
	protected void execute() {
		final Integer idTestata = request.getIdOrdineDaCancellare();

		scaricoMepaTestataDad.rimuoviOrdineMepa(idTestata);
		response.setIdOrdineCancellato(idTestata);
	}
}
