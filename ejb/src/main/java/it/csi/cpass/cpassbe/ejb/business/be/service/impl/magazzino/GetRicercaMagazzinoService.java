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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.magazzino;

import it.csi.cpass.cpassbe.ejb.business.be.dad.MagazzinoDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.magazzino.GetRicercaMagazzinoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.magazzino.GetRicercaMagazzinoResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.mag.Magazzino;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Retrieves an Rmss
 */
public class GetRicercaMagazzinoService extends BaseMagazzinoService<GetRicercaMagazzinoRequest, GetRicercaMagazzinoResponse> {

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad the testataOrdine DAD
	 */
	public GetRicercaMagazzinoService(ConfigurationHelper configurationHelper, MagazzinoDad magazzinoDad) {
		super(configurationHelper, magazzinoDad);
	}

	@Override
	protected void execute() {
		final Settore settoreUtente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		final Ente ente = settoreUtente.getEnte();
		final Magazzino mag = request.getMagazzino();
		mag.setEnte(ente);
		final PagedList<Magazzino> listaPag = magazzinoDad.getMagazzini(request.getPage(), request.getSize(), request.getSort(), mag);
		response.setMagazzinos(listaPag);
	}
}
