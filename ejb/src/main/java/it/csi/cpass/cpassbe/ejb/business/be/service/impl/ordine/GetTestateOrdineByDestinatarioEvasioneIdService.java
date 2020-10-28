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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.GetTestateOrdineByDestinatarioEvasioneIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.GetTestateOrdineByDestinatarioEvasioneIdResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;

/**
 * Retrieves an testataEvasione by its id
 */
public class GetTestateOrdineByDestinatarioEvasioneIdService extends BaseTestataOrdineService<GetTestateOrdineByDestinatarioEvasioneIdRequest, GetTestateOrdineByDestinatarioEvasioneIdResponse> {


	/**
	 * Constructor
	 * 
	 * @param configurationHelper the configuration helper
	 * @param testataEvasioneDad  the testataEvasione DAD
	 */
	public GetTestateOrdineByDestinatarioEvasioneIdService(ConfigurationHelper configurationHelper, TestataOrdineDad testataOrdineDad) {
		super(configurationHelper, testataOrdineDad);
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getId(), "id");
	}

	@Override
	protected void execute() {
		// TestataEvasione testataEvasione = testataEvasioneDad.getTestataEvasione(request.getId()).orElseThrow(() -> new
		// NotFoundException("testataEvasione"));
		List<TestataOrdine> listaTestataOrdine = testataOrdineDad.findTestateOrdineByDestinatarioEvasioneId(request.getId());
		response.setListaTestataOrdine(listaTestataOrdine);
	}

}
