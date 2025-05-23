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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetGestioneRuoloPermessoNonAttListRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetGestioneRuoloPermessoNonAttListResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ModuloRuoloPermesso;

public class GetGestioneRuoloPermessoNonAttListService extends BaseUtenteService<GetGestioneRuoloPermessoNonAttListRequest, GetGestioneRuoloPermessoNonAttListResponse> {

	public GetGestioneRuoloPermessoNonAttListService(ConfigurationHelper configurationHelper, UtenteDad utenteDad) {
		super(configurationHelper, utenteDad);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void execute() {
		final List<ModuloRuoloPermesso> ruoloPermessi = utenteDad.getRuoloPermessoNonAttiviList();
		response.setRuoloPermesso(ruoloPermessi);

	}

}
