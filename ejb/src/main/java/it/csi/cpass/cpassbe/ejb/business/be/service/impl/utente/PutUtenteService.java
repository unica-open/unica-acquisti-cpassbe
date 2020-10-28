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

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.PutUtenteRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.PutUtenteResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Utente;

/**
 * Saves an Utente
 */
public class PutUtenteService extends BaseUtenteService<PutUtenteRequest, PutUtenteResponse> {

	private Utente utente;

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param utenteDad the utente DAD
	 */
	public PutUtenteService(ConfigurationHelper configurationHelper, UtenteDad utenteDad) {
		super(configurationHelper, utenteDad);
	}

	@Override
	protected void checkServiceParams() {
		utente = request.getUtente();
		checkModel(request.getUtente(), "utente");
		//checkNotNull( utente.getAnnoAvvio(),"anno avvio");
		//checkModel(utente.getUtenteRup(), "utente Rup");
	}

	@Override
	protected void execute() {
		Utente utenteAttuale = isEntityPresent(() -> utenteDad.getUtente(utente.getId()), "utente");
		UUID optlookAttuale = utenteAttuale.getOptlock();
		// controllo per la concorrenza
		checkOptlock(utente.getOptlock(), optlookAttuale);
		//throw new BusinessException("Exception in inner method invocation", e);
		utenteDad.updateUtente(request.getUtente());
	}
}
