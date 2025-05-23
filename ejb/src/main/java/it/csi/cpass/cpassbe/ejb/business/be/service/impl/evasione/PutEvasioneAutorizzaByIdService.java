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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.evasione;

import java.util.Date;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.PutEvasioneAutorizzaByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.PutEvasioneAutorizzaByIdResponse;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoEvasioneEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.TestataEvasione;

public class PutEvasioneAutorizzaByIdService extends BaseTestataEvasioneService<PutEvasioneAutorizzaByIdRequest, PutEvasioneAutorizzaByIdResponse> {

	private final DecodificaDad decodificaDad;

	/**
	 * Constructor
	 *
	 * @param configurationHelper
	 * @param testataEvasioneDad
	 * @param rigaEvasioneDad
	 */
	public PutEvasioneAutorizzaByIdService(ConfigurationHelper configurationHelper, TestataEvasioneDad testataEvasioneDad, DecodificaDad decodificaDad) {
		super(configurationHelper, testataEvasioneDad);
		this.decodificaDad = decodificaDad;
	}

	@Override
	protected void checkServiceParams() {
	}

	@Override
	protected void execute() {
		final TestataEvasione testataEvasione = testataEvasioneDad.getTestataEvasioneModel(request.getId());
		// TestataEvasione testataEvasione = request.getTestataEvasione(); // l'oggetto non è completo
		testataEvasione.setDataAutorizzazione(new Date(System.currentTimeMillis()));
		testataEvasione.setStato(isEntityPresent(() -> decodificaDad.getStatoOpt(StatoEvasioneEnum.AUTORIZZATA.getCostante(), ConstantsCPassStato.TipoStatoEnum.EVASIONE.getCostante()),"stato"));
		testataEvasioneDad.updateTestataEvasione(testataEvasione);
		response.setTestataEvasione(testataEvasione);
	}

}
