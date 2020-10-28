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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.elaborazione;

import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.elaborazione.PostElaborazioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.elaborazione.PostElaborazioneResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;

/**
 * Saves an Elaborazione
 */
public class PostElaborazioneService extends BaseElaborazioneService<PostElaborazioneRequest, PostElaborazioneResponse> {

	private Elaborazione elaborazione;
	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param elaborazioneDad the elaborazione DAD
	 */
	public PostElaborazioneService(ConfigurationHelper configurationHelper, ElaborazioneDad elaborazioneDad) {
		super(configurationHelper, elaborazioneDad);
	}

	@Override
	protected void checkServiceParams() {
		elaborazione = request.getElaborazione();
		checkNotNull(elaborazione, "elaborazione", true);
	}

	@Override
	protected void execute() {
		elaborazione = request.getElaborazione();		
		elaborazione = elaborazioneDad.saveElaborazione(elaborazione);		
		response.setElaborazione(elaborazione);
	}
}
