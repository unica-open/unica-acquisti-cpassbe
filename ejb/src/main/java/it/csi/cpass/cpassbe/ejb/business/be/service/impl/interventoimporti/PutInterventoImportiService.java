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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.interventoimporti;

import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoImportiDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.interventoimporti.PutInterventoImportiRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.interventoimporti.PutInterventoImportiResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;

/**
 * Saves an InterventoImporti
 */
public class PutInterventoImportiService extends BaseInterventoImportiService<PutInterventoImportiRequest, PutInterventoImportiResponse> {

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param interventoImportiDad the intervento importi DAD
	 */
	public PutInterventoImportiService(ConfigurationHelper configurationHelper, InterventoImportiDad interventoImportiDad) {
		super(configurationHelper, interventoImportiDad);
	}

	@Override
	protected void checkServiceParams() {
		checkModel(request.getInterventoImporti(), "interventoImporti");
		// TODO: other fields
	}

	@Override
	protected void execute() {
		// TODO: check data
		interventoImportiDad.updateInterventoImporti(request.getInterventoImporti());
	}

}
