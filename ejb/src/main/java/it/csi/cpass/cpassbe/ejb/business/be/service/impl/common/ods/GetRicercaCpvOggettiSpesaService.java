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

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica.BaseDecodificaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.ods.GetRicercaCpvOggettiSpesaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.ods.GetRicercaCpvOggettiSpesaResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Cpv;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;

public class GetRicercaCpvOggettiSpesaService extends BaseDecodificaService<GetRicercaCpvOggettiSpesaRequest, GetRicercaCpvOggettiSpesaResponse> {

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param interventoDad the intervento DAD
	 */
	public GetRicercaCpvOggettiSpesaService(ConfigurationHelper configurationHelper, DecodificaDad decodificaDad) {
		super(configurationHelper, decodificaDad);
	}

	@Override
	protected void execute() {
		final PagedList<Cpv> lista = decodificaDad.getRicercaCpvOggettiSpesa(
				request.getPage(),
				request.getSize(),
				request.getSort(),
				request.getOggettiSpesa()
				);
		response.setListaCpvOds(lista);
	}

}
