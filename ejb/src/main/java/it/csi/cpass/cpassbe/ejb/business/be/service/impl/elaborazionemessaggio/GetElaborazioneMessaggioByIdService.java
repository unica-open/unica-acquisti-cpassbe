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
import it.csi.cpass.cpassbe.ejb.business.be.service.request.elaborazionemessaggio.GetElaborazioneMessaggioByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.elaborazionemessaggio.GetElaborazioneMessaggioByIdResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ElaborazioneMessaggio;

/**
 * Retrieves an elaborazioneMessaggio by its id
 */
public class GetElaborazioneMessaggioByIdService extends BaseElaborazioneMessaggioService<GetElaborazioneMessaggioByIdRequest, GetElaborazioneMessaggioByIdResponse> {

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param elaborazioneMessaggioDad the elaborazioneMessaggio DAD
	 */
	public GetElaborazioneMessaggioByIdService(ConfigurationHelper configurationHelper, ElaborazioneMessaggioDad elaborazioneMessaggioDad) {
		super(configurationHelper, elaborazioneMessaggioDad);
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getId(), "id");
	}

	@Override
	protected void execute() {
		ElaborazioneMessaggio elaborazioneMessaggio = new ElaborazioneMessaggio();//elaborazioneDad.getElaborazione(request.getId()).orElseThrow(() -> new NotFoundException("elaborazione"));
		response.setElaborazioneMessaggio(elaborazioneMessaggio);
	}

}
