/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2025 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.bo;

import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente.BaseUtenteService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetGestionePermessiNonAttiviRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetGestionePermessiNonAttiviResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ModuloRuoloPermesso;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;

public class GetGestionePermessiNonAttiviService extends BaseUtenteService<GetGestionePermessiNonAttiviRequest, GetGestionePermessiNonAttiviResponse> {

	public GetGestionePermessiNonAttiviService(ConfigurationHelper configurationHelper, UtenteDad utenteDad) {
		super(configurationHelper, utenteDad);
	}

	@Override
	protected void execute() {
		final PagedList<ModuloRuoloPermesso> moduloRuoloPermessi = utenteDad.getPermessiDaGestireNonAttivi(request.getPage(), request.getSize(), request.getSort());
		response.setRuoloPermesso(moduloRuoloPermessi);
	}

}
