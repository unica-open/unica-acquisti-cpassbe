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

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.GetRicercaInterventiRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.GetRicercaInterventiResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.MetadatiFunzione;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;

/**
 * Retrieves an Interventos
 */
public class GetRicercaInterventiService extends BaseInterventoService<GetRicercaInterventiRequest, GetRicercaInterventiResponse> {

	CommonDad commonDad;
	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param interventoDad the intervento DAD
	 */
	public GetRicercaInterventiService(ConfigurationHelper configurationHelper, InterventoDad interventoDad, CommonDad commonDad) {
		super(configurationHelper, interventoDad);
		this.commonDad =  commonDad;
	}

	@Override
	protected void execute() {
		String ordinamento = "";
		final List<MetadatiFunzione> listmetadati = request.getIntervento().getListMetadatiFunzione();
		if(listmetadati != null && listmetadati.size()>0) {
			ordinamento = commonDad.getOrdinamentoByModuloFunzioneTipo("PBA", "RICERCA_INTERVENTO", "JPQL",listmetadati);
		}


		final PagedList<Intervento> interventi = interventoDad.getRicercaInterventi(
				request.getPage(),
				request.getSize(),
				request.getSort(),
				request.getIntervento(),
				ordinamento
				);
		response.setInterventi(interventi);
	}

}
