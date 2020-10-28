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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente;

import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.PostUtenteRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.PostUtenteResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Utente;

/**
 * Saves an Utente
 */
public class PostUtenteService extends BaseUtenteService<PostUtenteRequest, PostUtenteResponse> {

	private Utente utente;

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param utenteDad the utente DAD
	 */
	public PostUtenteService(ConfigurationHelper configurationHelper, UtenteDad utenteDad) {
		super(configurationHelper, utenteDad);
	}

	@Override
	protected void checkServiceParams() {
		utente = request.getUtente();
		checkNotNull(utente, "utente", true);
	}

	@Override
	protected void execute() {
		// TODO: gestire errore
		utente = utenteDad.saveUtente(utente);
		response.setUtente(utente);
	}
}
