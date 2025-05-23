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
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetGestioneRuoloPermessoListRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetGestioneRuoloPermessoListResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.RuoloPermesso;

public class GetGestioneRuoloPermessoListService extends BaseUtenteService<GetGestioneRuoloPermessoListRequest, GetGestioneRuoloPermessoListResponse>{

	public GetGestioneRuoloPermessoListService(ConfigurationHelper configurationHelper, UtenteDad utenteDad) {
		super(configurationHelper, utenteDad);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void execute() {
		final List<RuoloPermesso> ruoloPermessi = utenteDad.getRuoloPermessoList();
		response.setRuoloPermesso(ruoloPermessi);
	}

}
