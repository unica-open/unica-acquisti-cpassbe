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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine;

import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.GetRicercaSezioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.GetRicercaSezioneResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.ord.Sezione;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Retrieves an Rmss
 */
public class GetRicercaSezioneService extends BaseOrdineService<GetRicercaSezioneRequest, GetRicercaSezioneResponse> {

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad the testataOrdine DAD
	 */
	public GetRicercaSezioneService(ConfigurationHelper configurationHelper, TestataOrdineDad testataOrdineDad,SettoreDad settoreDad) {
		super(configurationHelper,testataOrdineDad, settoreDad);
	}

	@Override
	protected void execute() {
		final Settore settoreUtente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		final Ente ente = settoreUtente.getEnte();
		final Sezione sez = request.getSezione();
		sez.setEnte(ente);
		final PagedList<Sezione> listaPag = testataOrdineDad.getSezionii(request.getPage(), request.getSize(), request.getSort(), sez);
		response.setSeziones(listaPag);
	}
}
