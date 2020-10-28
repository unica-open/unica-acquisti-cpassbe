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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.interventoimporti;

import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoImportiDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.interventoimporti.DeleteInterventoImportiByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.interventoimporti.DeleteInterventoImportiByIdResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;

/**
 * Deletes an InterventoImporti by its uuid
 */
public class DeleteInterventoImportiByIdService extends BaseInterventoImportiService<DeleteInterventoImportiByIdRequest, DeleteInterventoImportiByIdResponse> {

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param interventoImportiDad the intervento importi DAD
	 */
	public DeleteInterventoImportiByIdService(ConfigurationHelper configurationHelper, InterventoImportiDad interventoImportiDad) {
		super(configurationHelper, interventoImportiDad);
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getId(), "uuid");
	}

	@Override
	protected void execute() {
		interventoImportiDad.deleteInterventoImporti(request.getId());
	}

}
