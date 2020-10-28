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
import it.csi.cpass.cpassbe.ejb.business.be.service.request.elaborazionemessaggio.DeleteElaborazioneMessaggioByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.elaborazionemessaggio.DeleteElaborazioneMessaggioByIdResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;

/**
 * Deletes an elaborazione by its id
 */
public class DeleteElaborazioneMessaggioByIdService extends BaseElaborazioneMessaggioService<DeleteElaborazioneMessaggioByIdRequest, DeleteElaborazioneMessaggioByIdResponse> {

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param elaborazioneMessaggioDad the elaborazione DAD
	 */
	public DeleteElaborazioneMessaggioByIdService(ConfigurationHelper configurationHelper, ElaborazioneMessaggioDad elaborazioneMessaggioDad) {
		super(configurationHelper, elaborazioneMessaggioDad);
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getId(), "id");
	}

	@Override
	protected void execute() {
		// TODO da fare
		//elaborazioneDad.deleteElaborazione(request.getId());
	}

}
