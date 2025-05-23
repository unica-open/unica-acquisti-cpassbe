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
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.GetInterventiCapofilaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.GetInterventiCapofilaResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Retrieves an Interventos
 */
public class GetInterventiCapofilaService extends BaseService<GetInterventiCapofilaRequest, GetInterventiCapofilaResponse> {

	private final InterventoDad interventoDad;
	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param interventoImportiDad the intervento importo DAD
	 */
	public GetInterventiCapofilaService(ConfigurationHelper configurationHelper, InterventoDad interventoDad) {
		super(configurationHelper);
		this.interventoDad = interventoDad;
	}

	@Override
	protected void checkServiceParams() {
	}

	@Override
	protected void execute() {
		UUID settId = CpassThreadLocalContainer.SETTORE_UTENTE.get().getId();
		final Intervento intervpassato = request.getIntervento();
		if(intervpassato != null && intervpassato.getSettore() != null && intervpassato.getSettore().getId() != null) {
			settId = intervpassato.getSettore().getId();
		}
		final List<Intervento> interventi = interventoDad.getInterventiCapofila(request.getIntervento().getProgramma().getId(), request.getIntervento().getId(), settId);
		response.setInterventi(interventi);
	}

}
