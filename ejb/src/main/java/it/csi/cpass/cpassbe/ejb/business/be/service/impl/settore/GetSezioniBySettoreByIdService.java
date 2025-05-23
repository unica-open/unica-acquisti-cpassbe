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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.settore;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.settore.GetSezioniBySettoreByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.settore.GetSezioniBySettoreByIdResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ord.Sezione;

/**
 * Retrieves an intervento by its id
 */
public class GetSezioniBySettoreByIdService extends BaseSettoreService<GetSezioniBySettoreByIdRequest, GetSezioniBySettoreByIdResponse> {

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param interventoDad the intervento DAD
	 */
	public GetSezioniBySettoreByIdService(ConfigurationHelper configurationHelper, SettoreDad settoreDad) {
		super(configurationHelper, settoreDad);
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getId(), "id");
	}

	@Override
	protected void execute() {
		//Settore settore = settoreDad.findByIdOpt(request.getId()).orElseThrow(() -> new NotFoundException("settore"));
		final List<Sezione> sezioni =settoreDad.getSezioniBySettoreId(request.getId());
		response.setSezioni(sezioni);
	}

}
