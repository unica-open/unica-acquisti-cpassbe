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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.elaborazione;

import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.elaborazione.PutElaborazioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.elaborazione.PutElaborazioneResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Saves an Elaborazione
 */
public class PutElaborazioneService extends BaseElaborazioneService<PutElaborazioneRequest, PutElaborazioneResponse> {

	private Elaborazione elaborazione;

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param elaborazioneDad the elaborazione DAD
	 */
	public PutElaborazioneService(ConfigurationHelper configurationHelper, ElaborazioneDad elaborazioneDad) {
		super(configurationHelper, elaborazioneDad);
	}

	@Override
	protected void checkServiceParams() {
		elaborazione = request.getElaborazione();
		checkNotNull(elaborazione, "elaborazione", Boolean.TRUE);
	}

	@Override
	protected void execute() {
		elaborazione = request.getElaborazione();
		final Ente ente = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte();
		elaborazione.setEnte(ente);
		elaborazioneDad.saveElaborazione(elaborazione);
	}
}
