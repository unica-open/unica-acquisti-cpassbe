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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente;

import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetSettoriByUtenteRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetSettoriByUtenteResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Retrieves an settori
 */
public class GetSettoriByUtenteService  extends BaseUtenteService<GetSettoriByUtenteRequest, GetSettoriByUtenteResponse> {

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param utenteDad the utente DAD
	 */
	public GetSettoriByUtenteService(ConfigurationHelper configurationHelper, UtenteDad utenteDad) {
		super(configurationHelper, utenteDad);
	}

	@Override
	protected void execute() {
		final UUID utenteId = CpassThreadLocalContainer.UTENTE_CONNESSO.get().getId();
		final List<Settore> settori = utenteDad.getSettoriByUtente(utenteId);
		response.setSettori(settori);
	}

}
