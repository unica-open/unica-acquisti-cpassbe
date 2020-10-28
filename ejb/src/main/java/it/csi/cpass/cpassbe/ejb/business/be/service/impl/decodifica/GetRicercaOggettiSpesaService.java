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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica.GetRicercaOggettiSpesaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica.GetRicercaOggettiSpesaResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ord.OggettiSpesa;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;

public class GetRicercaOggettiSpesaService extends BaseDecodificaService<GetRicercaOggettiSpesaRequest, GetRicercaOggettiSpesaResponse> {

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param interventoDad the intervento DAD
	 */
	public GetRicercaOggettiSpesaService(ConfigurationHelper configurationHelper, DecodificaDad decodificaDad) {
		super(configurationHelper, decodificaDad);
	}

	@Override
	protected void execute() {
		PagedList<OggettiSpesa> lista = decodificaDad.getRicercaOggettiSpesa(
				request.getPage(),
				request.getSize(),
				request.getSort(),
				request.getOggettiSpesa()
				);
		response.setOggettiSpesa(lista);
	}

}
