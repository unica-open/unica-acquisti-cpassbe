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

import java.math.BigDecimal;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.GetRicercaRigheByDestinatarioEvasioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.GetRicercaRigheByDestinatarioEvasioneResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.RigaEvasione;

public class GetRicercaRigheByDestinatarioEvasioneService
extends BaseService<GetRicercaRigheByDestinatarioEvasioneRequest, GetRicercaRigheByDestinatarioEvasioneResponse> {

	RigaEvasioneDad rigaEvasioneDad;

	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 * @param testataEvasioneDad  the testataEvasione DAD
	 */
	public GetRicercaRigheByDestinatarioEvasioneService(ConfigurationHelper configurationHelper, RigaEvasioneDad rigaEvasioneDad) {
		super(configurationHelper);
		this.rigaEvasioneDad = rigaEvasioneDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getIdDestinatarioEvasione(), "destinatarioId");
	}

	@Override
	protected void execute() {
		final List<RigaEvasione> righe = rigaEvasioneDad.getRigheByDestinatarioEvasione(request.getIdDestinatarioEvasione());

		for(final RigaEvasione riga : righe) {
			final BigDecimal totaleEvaso = rigaEvasioneDad.calcolaTotale(riga.getRigaOrdine().getId());
			riga.setTotaleEvaso(totaleEvaso);
		}


		response.setRigheEvasione(righe);
	}

}
