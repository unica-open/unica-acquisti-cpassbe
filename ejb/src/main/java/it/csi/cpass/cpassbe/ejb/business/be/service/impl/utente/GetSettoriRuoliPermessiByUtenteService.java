/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente;

import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetSettoriRuoliPermessiByUtenteRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetSettoriRuoliPermessiByUtenteResponse;
import it.csi.cpass.cpassbe.ejb.util.CpassThreadLocalContainer;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.SettoreRuoliPermessi;

/**
 * Retrieves an settori
 */
public class GetSettoriRuoliPermessiByUtenteService  extends BaseUtenteService<GetSettoriRuoliPermessiByUtenteRequest, GetSettoriRuoliPermessiByUtenteResponse> {

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param utenteDad the utente DAD
	 */
	public GetSettoriRuoliPermessiByUtenteService(ConfigurationHelper configurationHelper, UtenteDad utenteDad) {
		super(configurationHelper, utenteDad);
	}

	@Override
	protected void execute() {
		
		UUID utenteId = CpassThreadLocalContainer.UTENTE_CONNESSO.get().getId();
		List<SettoreRuoliPermessi> srp = utenteDad.getSettoriRuoliPermessiByUtenteService(utenteId);
		response.setSettoriRuoliPermessi(srp);
		
	}

}
