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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.common.ods;

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.common.BaseCommonService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.ods.GetRicercaOggettiSpesaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.ods.GetRicercaOggettiSpesaResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Ods;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;

public class GetRicercaOggettiSpesaService extends BaseCommonService<GetRicercaOggettiSpesaRequest, GetRicercaOggettiSpesaResponse> {

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param interventoDad the intervento DAD
	 */
	public GetRicercaOggettiSpesaService(ConfigurationHelper configurationHelper, CommonDad commonDad) {
		super(configurationHelper, commonDad);
	}

	@Override
	protected void execute() {
		final PagedList<Ods> lista = commonDad.getRicercaOggettiSpesa(
				request.getPage(),
				request.getSize(),
				request.getSort(),
				request.getOggettiSpesa()
				);
		response.setOggettiSpesa(lista);
	}

}
