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
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetPermessiBySettoreRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetPermessiBySettoreResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Permesso;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Retrieves an permessis
 */
public class GetPermessiBySettoreService extends BaseUtenteService<GetPermessiBySettoreRequest, GetPermessiBySettoreResponse> {

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param utenteDad the utente DAD
	 */
	public GetPermessiBySettoreService(ConfigurationHelper configurationHelper, UtenteDad utenteDad) {
		super(configurationHelper, utenteDad);
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getSettoreId(), "settoreId");
	}

	@Override
	protected void execute() {
		final UUID utenteId = CpassThreadLocalContainer.UTENTE_CONNESSO.get().getId();
		final List<Permesso> permessi = utenteDad.getPermessiByUtenteAndSettore(utenteId, request.getSettoreId());
		response.setPermessi(permessi);
	}

}
