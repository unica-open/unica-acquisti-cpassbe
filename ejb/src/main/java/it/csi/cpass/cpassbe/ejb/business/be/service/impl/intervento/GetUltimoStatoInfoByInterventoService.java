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
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.GetUltimoStatoInfoByInterventoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.GetUltimoStatoInfoByInterventoResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.custom.StatoInterventoInfo;

public class GetUltimoStatoInfoByInterventoService extends BaseService<GetUltimoStatoInfoByInterventoRequest, GetUltimoStatoInfoByInterventoResponse> {

	private final InterventoDad interventoDad;

	public GetUltimoStatoInfoByInterventoService(ConfigurationHelper configurationHelper, InterventoDad interventoDad) {
		super(configurationHelper);
		this.interventoDad = interventoDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getId(), "id");
	}

	@Override
	protected void execute() {
		final List<StatoInterventoInfo> listastatoInfo = interventoDad.getUltimoStatoInfoByIntervento(request.getId());
		response.setListastatoInterventoInfo(listastatoInfo);
	}
}
