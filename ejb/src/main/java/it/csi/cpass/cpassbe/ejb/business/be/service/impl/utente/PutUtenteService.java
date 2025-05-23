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

import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.PutUtenteRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.PutUtenteResponse;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Saves an Utente
 */
public class PutUtenteService extends BaseUtenteService<PutUtenteRequest, PutUtenteResponse> {
	private Utente utente;
	private SystemDad systemDad;
	private ExternalHelperLookup externalHelperLookup;

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param utenteDad the utente DAD
	 */
	public PutUtenteService(ConfigurationHelper configurationHelper,ExternalHelperLookup externalHelperLookup, UtenteDad utenteDad, SystemDad systemDad) {
		super(configurationHelper, utenteDad);
		this.externalHelperLookup 	= externalHelperLookup;
		this.systemDad = systemDad;
	}

	@Override
	protected void checkServiceParams() {
		utente = request.getUtente();
		checkModel(utente, "utente");
		checkNotNull(utente.getCognome(), "cognome", true);
		checkNotNull(utente.getNome(), "nome", true);
		checkNotNull(utente.getCodiceFiscale(), "c.f.", true);
	}

	@Override
	protected void execute() {
		final Ente ente = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte();
		utente.setNome(utente.getNome().toUpperCase());
		utente.setCognome(utente.getCognome().toUpperCase());
		validaUtente(externalHelperLookup,systemDad,utente,ente,"put");
		utenteDad.updateUtente(utente);
		response.setUtente(utente);
	}
}
