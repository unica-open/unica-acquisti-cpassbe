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

import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.GetRicercaInterventiXCopiaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.GetRicercaInterventiXCopiaResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;

/**
 * Retrieves an Interventos
 */
public class GetRicercaInterventiXCopiaService extends BaseInterventoService<GetRicercaInterventiXCopiaRequest, GetRicercaInterventiXCopiaResponse> {

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param interventoDad the intervento DAD
	 */
	public GetRicercaInterventiXCopiaService(ConfigurationHelper configurationHelper, InterventoDad interventoDad) {
		super(configurationHelper, interventoDad);
	}

	@Override
	protected void execute() {
		final PagedList<Intervento> interventi = interventoDad.getRicercaInterventiXCopia(
				request.getPage(),
				request.getSize(),
				request.getSort(),
				request.getProgrammaIdOld(),
				request.getProgrammaIdNew(),
				request.getUtenteRupId()
				);
		response.setInterventi(interventi);
	}

}
