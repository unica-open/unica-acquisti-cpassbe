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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.GetInterventiByCapofilaIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.GetInterventiByCapofilaIdResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;

/**
 * Retrieves an Interventos
 */
public class GetInterventiByCapofilaIdService extends BaseService<GetInterventiByCapofilaIdRequest, GetInterventiByCapofilaIdResponse> {

	private final InterventoDad interventoDad;
	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param interventoImportiDad the intervento importo DAD
	 */
	public GetInterventiByCapofilaIdService(ConfigurationHelper configurationHelper, InterventoDad interventoDad) {
		super(configurationHelper);
		this.interventoDad = interventoDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getId(), "id");
	}

	@Override
	protected void execute() {
		//Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		final List<Intervento> interventi = interventoDad.getInterventiByCapofilaId(request.getId(),null);
		response.setInterventi(interventi);
	}

}
