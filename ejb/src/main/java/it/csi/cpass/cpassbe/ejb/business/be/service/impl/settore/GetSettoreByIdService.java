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

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.settore.GetSettoreByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.settore.GetSettoreByIdResponse;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.SettoreIndirizzo;

/**
 * Retrieves an intervento by its id
 */
public class GetSettoreByIdService extends BaseSettoreService<GetSettoreByIdRequest, GetSettoreByIdResponse> {

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param interventoDad the intervento DAD
	 */
	public GetSettoreByIdService(ConfigurationHelper configurationHelper, SettoreDad settoreDad) {
		super(configurationHelper, settoreDad);
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getId(), "id");
	}

	@Override
	protected void execute() {
		final Settore settore = settoreDad.findByIdOpt(request.getId()).orElseThrow(() -> new NotFoundException("settore"));
		//List<SettoreIndirizzo> listaSettoreIndirizziValidi = settore.getSettoreIndirizzos();
		final List<SettoreIndirizzo> listaSettoreIndirizziValidi  = settore.getSettoreIndirizzos().stream().filter(x->x.getDataCancellazione()==null || x.getDataCancellazione().compareTo(new Date())>0).collect(Collectors.toList());
		settore.setSettoreIndirizzos(listaSettoreIndirizziValidi);
		response.setSettore(settore);
	}

}
