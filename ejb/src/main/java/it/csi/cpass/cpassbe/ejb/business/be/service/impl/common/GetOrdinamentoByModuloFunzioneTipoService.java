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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.common;

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.GetOrdinamentoByModuloFunzioneTipoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.GetOrdinamentoByModuloFunzioneTipoResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;

public class GetOrdinamentoByModuloFunzioneTipoService extends BaseCommonService<GetOrdinamentoByModuloFunzioneTipoRequest, GetOrdinamentoByModuloFunzioneTipoResponse> {

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 */
	public GetOrdinamentoByModuloFunzioneTipoService(ConfigurationHelper configurationHelper, CommonDad commonDad, SystemDad systemDad) {
		super(configurationHelper, commonDad);
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getModulo(),"funzione");
		checkNotNull(request.getFunzione(),"funzione");
		checkNotNull(request.getTipo(),"funzione");
		checkNotNull(request.getListMetadatiFunzione(),"metadati funzione");
	}

	@Override
	protected void execute() {
		log.info("GetOrdinamentoByModuloFunzioneTipoService", "Begin");
		final String ordinamento = commonDad.getOrdinamentoByModuloFunzioneTipo(request.getModulo(), request.getFunzione(),request.getTipo(),request.getListMetadatiFunzione());
		response.setOrdinamento(ordinamento);
	}

}
