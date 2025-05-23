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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.elaborazionemessaggio;


import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneMessaggioDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.elaborazionemessaggio.PutElaborazioneMessaggioRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.elaborazionemessaggio.PutElaborazioneMessaggioResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ElaborazioneMessaggio;

/**
 * Saves an ElaborazioneMessaggio
 */
public class PutElaborazioneMessaggioService extends BaseElaborazioneMessaggioService<PutElaborazioneMessaggioRequest, PutElaborazioneMessaggioResponse> {

	private ElaborazioneMessaggio elaborazioneMessaggio;

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param elaborazioneMessaggioDad the elaborazione DAD
	 */
	public PutElaborazioneMessaggioService(ConfigurationHelper configurationHelper, ElaborazioneMessaggioDad elaborazioneMessaggioDad) {
		super(configurationHelper, elaborazioneMessaggioDad);
	}

	@Override
	protected void checkServiceParams() {
		elaborazioneMessaggio = request.getElaborazioneMessaggio();
		checkNotNull(elaborazioneMessaggio, "elaborazione", Boolean.TRUE);
	}

	@Override
	protected void execute() {
		elaborazioneMessaggio = request.getElaborazioneMessaggio();
		elaborazioneMessaggioDad.updateElaborazioneMessaggio(elaborazioneMessaggio);
	}
}
