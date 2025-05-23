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

import java.util.ArrayList;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.GetEvasioniCollegatePerFatturaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.GetEvasioniCollegatePerFatturaResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.TestataEvasione;
public class GetEvasioniCollegatePerFatturaService extends BaseTestataEvasioneService<GetEvasioniCollegatePerFatturaRequest, GetEvasioniCollegatePerFatturaResponse> {

	public GetEvasioniCollegatePerFatturaService(ConfigurationHelper configurationHelper,
			TestataEvasioneDad testataEvasioneDad) {
		super(configurationHelper, testataEvasioneDad);
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getIdTestataEvasione(), "idTestataEvasione");
	}

	@Override
	protected void execute() {

		final List<TestataEvasione> responseList = new ArrayList<>();
		final TestataEvasione tempEvasione = testataEvasioneDad.getTestataEvasioneModel(request.getIdTestataEvasione());

		final List<TestataEvasione> listaEvasioni = testataEvasioneDad.findEvasioniByFattura(tempEvasione.getFatturaAnno(), tempEvasione.getFatturaNumero(),
				tempEvasione.getFatturaTipo(), tempEvasione.getFatturaCodiceFornitore());

		//creo lista priva dell'evasione usata come parametro
		for(final TestataEvasione evasione : listaEvasioni) {
			if(!evasione.getId().equals(request.getIdTestataEvasione())) {
				responseList.add(evasione);
			}
		}

		response.setListaEvasioni(responseList);
	}

}
