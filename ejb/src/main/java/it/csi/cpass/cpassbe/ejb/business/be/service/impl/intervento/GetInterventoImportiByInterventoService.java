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

import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoImportiDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.GetInterventoImportiByInterventoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.GetInterventoImportiByInterventoResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;
import it.csi.cpass.cpassbe.lib.dto.pba.InterventoImporti;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;

/**
 * Retrieves an Interventos
 */
public class GetInterventoImportiByInterventoService extends BaseService<GetInterventoImportiByInterventoRequest, GetInterventoImportiByInterventoResponse> {

	private final InterventoImportiDad interventoImportiDad;
	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param interventoImportiDad the intervento importo DAD
	 */
	public GetInterventoImportiByInterventoService(ConfigurationHelper configurationHelper, InterventoImportiDad interventoImportiDad) {
		super(configurationHelper);
		this.interventoImportiDad = interventoImportiDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getId(), "id");
	}

	@Override
	protected void execute() {
		final InterventoImporti interventoImporti = new InterventoImporti();
		interventoImporti.setIntervento(new Intervento());
		interventoImporti.getIntervento().setId(request.getId());


		final PagedList<InterventoImporti> interventiImporti = interventoImportiDad.getInterventiImporti(interventoImporti, request.getPage(), request.getSize());
		response.setInterventiImporti(interventiImporti);
	}

}
