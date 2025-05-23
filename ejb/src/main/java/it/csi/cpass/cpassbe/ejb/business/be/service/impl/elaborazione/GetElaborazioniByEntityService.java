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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.elaborazione;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.elaborazione.GetElaborazioniByEntityRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.elaborazione.GetElaborazioniByEntityResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;

public class GetElaborazioniByEntityService extends BaseElaborazioneService<GetElaborazioniByEntityRequest, GetElaborazioniByEntityResponse> {

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param elaborazioneDad the elaborazione DAD
	 */
	public GetElaborazioniByEntityService(ConfigurationHelper configurationHelper, ElaborazioneDad elaborazioneDad) {
		super(configurationHelper, elaborazioneDad);
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getEntityId(), "entityId");
	}

	@Override
	protected void execute() {
		final List<Elaborazione> elaborazioni = elaborazioneDad.getElaborazioniByEntityId(request.getEntityId());
		response.setElaborazioni(elaborazioni);
	}

}
