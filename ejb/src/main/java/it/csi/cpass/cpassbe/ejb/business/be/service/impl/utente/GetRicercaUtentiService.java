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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente;

import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetRicercaUtentiRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetRicercaUtentiResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Retrieves an utentes
 */
public class GetRicercaUtentiService extends BaseUtenteService<GetRicercaUtentiRequest, GetRicercaUtentiResponse> {

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param utenteDad the utente DAD
	 */
	public GetRicercaUtentiService(ConfigurationHelper configurationHelper, UtenteDad utenteDad) {
		super(configurationHelper, utenteDad);
	}

	@Override
	protected void execute() {
		final Ente ente = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte();
		final PagedList<Utente> utenti = utenteDad.getRicercaUtenti(
				request.getPage()
				,request.getSize()
				,request.getSort()
				,request.getDirigente()
				,request.getUtente()
				,request.getRuolo()
				,request.getSettore()
				,ente.getId()
				,Boolean.TRUE
				//,request.getCheckDataValiditaFineRUteSett()
				);
		response.setutenti(utenti);
	}

}
