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

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.GetRiepilogoFatturaByIdEvasioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.GetRiepilogoFatturaByIdEvasioneResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.RiepilogoFatturaEvasione;

public class GetRiepilogoFatturaByIdEvasioneService extends BaseService<GetRiepilogoFatturaByIdEvasioneRequest, GetRiepilogoFatturaByIdEvasioneResponse> {

	private final TestataEvasioneDad testataEvasioneDad;

	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad    the testataOrdine DAD
	 */
	public GetRiepilogoFatturaByIdEvasioneService(ConfigurationHelper configurationHelper, TestataEvasioneDad testataEvasioneDad) {
		super(configurationHelper);
		this.testataEvasioneDad = testataEvasioneDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getId(), "id testata evasione");
	}

	@Override
	protected void execute() {
		final List<RiepilogoFatturaEvasione> listaRiepilogoFatturaEvasione = testataEvasioneDad.getRiepilogoFatturaByIdEvasione(request.getId());
		response.setListaRiepilogoFatturaEvasione(listaRiepilogoFatturaEvasione);
	}

}
