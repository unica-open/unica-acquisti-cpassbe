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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.system;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.system.GetComunicazioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.system.GetComunicazioneResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Comunicazione;

/**
 * Lettura Comunicazione
 */

public class GetComunicazioneService extends BaseService<GetComunicazioneRequest, GetComunicazioneResponse> {
	
	private final SystemDad systemDad;
	
	/**
	 * Constructor
	 * @param configurationHelper the helper for the configuration
	 * @param systemDad the DAD for the system
	 */
	public GetComunicazioneService(ConfigurationHelper configurationHelper, SystemDad systemDad) {
		super(configurationHelper);
		this.systemDad = systemDad;
	}

	@Override
	protected void execute() {
		List<Comunicazione> comunicaziones = systemDad.getComunicaziones();
		response.setComunicaziones(comunicaziones);
	}

}
