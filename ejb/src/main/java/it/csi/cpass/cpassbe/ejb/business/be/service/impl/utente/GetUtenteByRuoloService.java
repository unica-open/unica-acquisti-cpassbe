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

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetUtenteByRuoloRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetUtenteByRuoloResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Utente;

/**
 * Retrieves an utente by its id
 */
public class GetUtenteByRuoloService extends BaseUtenteService<GetUtenteByRuoloRequest, GetUtenteByRuoloResponse> {

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param utenteDad the utente DAD
	 */
	public GetUtenteByRuoloService(ConfigurationHelper configurationHelper, UtenteDad utenteDad) {
		super(configurationHelper, utenteDad);
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getRuoloCodice(), "ruolo codice");
	}

	@Override
	protected void execute() {
		final List<Utente> utente = utenteDad.getUtenteByRuolo(request.getRuoloCodice());
		response.setUtente(utente);
	}

}
