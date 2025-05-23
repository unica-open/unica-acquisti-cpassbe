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
import it.csi.cpass.cpassbe.ejb.business.be.service.request.elaborazione.DeleteElaborazioneByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.elaborazione.DeleteElaborazioneByIdResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;

/**
 * Deletes an elaborazione by its id
 */
public class DeleteElaborazioneByIdService extends BaseElaborazioneService<DeleteElaborazioneByIdRequest, DeleteElaborazioneByIdResponse> {

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param elaborazioneDad the elaborazione DAD
	 */
	public DeleteElaborazioneByIdService(ConfigurationHelper configurationHelper, ElaborazioneDad elaborazioneDad) {
		super(configurationHelper, elaborazioneDad);
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
