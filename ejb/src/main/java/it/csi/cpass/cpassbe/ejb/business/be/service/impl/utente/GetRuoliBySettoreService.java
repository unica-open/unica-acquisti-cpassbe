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
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetRuoliBySettoreRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetRuoliBySettoreResponse;
import it.csi.cpass.cpassbe.ejb.util.CpassThreadLocalContainer;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Ruolo;

/**
 * Retrieves ruoli of utente by settore
 */
public class GetRuoliBySettoreService extends BaseUtenteService<GetRuoliBySettoreRequest, GetRuoliBySettoreResponse> {

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param utenteDad the utente DAD
	 */
	public GetRuoliBySettoreService(ConfigurationHelper configurationHelper, UtenteDad utenteDad) {
		super(configurationHelper, utenteDad);
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getSettoreId(), "settore id");
	}

	@Override
	protected void execute() {
		UUID utenteId = CpassThreadLocalContainer.UTENTE_CONNESSO.get().getId();
		List<Ruolo> ruoli = utenteDad.getRuoliByUtenteSettore(utenteId, request.getSettoreId());
		response.setRuoli(ruoli);
	}

}
