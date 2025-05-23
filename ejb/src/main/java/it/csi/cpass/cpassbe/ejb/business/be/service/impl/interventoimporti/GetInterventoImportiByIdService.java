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
import it.csi.cpass.cpassbe.ejb.business.be.service.request.interventoimporti.GetInterventoImportiByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.interventoimporti.GetInterventoImportiByIdResponse;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.pba.InterventoImporti;

/**
 * Retrieves an InterventoImporti by its id
 */
public class GetInterventoImportiByIdService extends BaseInterventoImportiService<GetInterventoImportiByIdRequest, GetInterventoImportiByIdResponse> {

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param interventoImportiDad the intervento importi DAD
	 */
	public GetInterventoImportiByIdService(ConfigurationHelper configurationHelper, InterventoImportiDad interventoImportiDad) {
		super(configurationHelper, interventoImportiDad);
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getId(), "uuid");
	}

	@Override
	protected void execute() {
		final InterventoImporti interventoImporti = interventoImportiDad.getInterventoImporti(request.getId())
				.orElseThrow(() -> new NotFoundException("interventoImporti"));
		response.setInterventoImporti(interventoImporti);
	}

}
