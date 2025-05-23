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
import it.csi.cpass.cpassbe.ejb.business.be.service.request.elaborazione.GetElaborazioneByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.elaborazione.GetElaborazioneByIdResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;

/**
 * Retrieves an elaborazione by its id
 */
public class GetElaborazioneByIdService extends BaseElaborazioneService<GetElaborazioneByIdRequest, GetElaborazioneByIdResponse> {

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param elaborazioneDad the elaborazione DAD
	 */
	public GetElaborazioneByIdService(ConfigurationHelper configurationHelper, ElaborazioneDad elaborazioneDad) {
		super(configurationHelper, elaborazioneDad);
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getId(), "id");
	}

	@Override
	protected void execute() {
		final Elaborazione elaborazione = new Elaborazione();//elaborazioneDad.getElaborazione(request.getId()).orElseThrow(() -> new NotFoundException("elaborazione"));
		response.setElaborazione(elaborazione);
	}

}
