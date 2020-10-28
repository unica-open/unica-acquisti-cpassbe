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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.elaborazionemessaggio;

import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneMessaggioDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.elaborazionemessaggio.PostElaborazioneMessaggioRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.elaborazionemessaggio.PostElaborazioneMessaggioResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ElaborazioneMessaggio;

/**
 * Saves an Elaborazione
 */
public class PostElaborazioneMessaggioService extends BaseElaborazioneMessaggioService<PostElaborazioneMessaggioRequest, PostElaborazioneMessaggioResponse> {

	private ElaborazioneMessaggio elaborazioneMessaggio;
	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param elaborazioneMessaggioDad the elaborazione DAD
	 */
	public PostElaborazioneMessaggioService(ConfigurationHelper configurationHelper, ElaborazioneMessaggioDad elaborazioneMessaggioDad) {
		super(configurationHelper, elaborazioneMessaggioDad);
	}

	@Override
	protected void checkServiceParams() {
		elaborazioneMessaggio = request.getElaborazioneMessaggio();
		checkNotNull(elaborazioneMessaggio, "elaborazioneMessaggio", true);
	}

	@Override
	protected void execute() {
		elaborazioneMessaggio = request.getElaborazioneMessaggio();		
		elaborazioneMessaggio = elaborazioneMessaggioDad.saveElaborazioneMessaggio(elaborazioneMessaggio);		
		response.setElaborazioneMessaggio(elaborazioneMessaggio);
	}
}
