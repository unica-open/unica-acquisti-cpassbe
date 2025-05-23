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

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.MagazzinoDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.magazzino.GetMagazziniRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.magazzino.GetMagazziniResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.mag.Magazzino;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

public class GetMagazziniService extends BaseMagazzinoService<GetMagazziniRequest, GetMagazziniResponse> {

	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 * @param magazzinoDad              the magazzino dad
	 */
	public GetMagazziniService(ConfigurationHelper configurationHelper, MagazzinoDad magazzinoDad) {
		super(configurationHelper, magazzinoDad);
	}

	/**
	 * Checks own service parameters
	 *
	 * @in case of errors in param check
	 */
	@Override
	protected void checkServiceParams() {

	}

	@Override
	protected void execute() {
		final Settore settoreUtente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		final Ente ente = settoreUtente.getEnte();
		final List<Magazzino> lista = magazzinoDad.getMagazziniByEnteId(ente.getId());
		response.setMagazzini(lista);
	}
}
