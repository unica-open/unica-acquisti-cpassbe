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

import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetRupsBySettoreIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetRupsBySettoreIdResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Utente;

/**
 * Retrieves an GetRupsByInterventoId by its id
 */
public class GetRupsBySettoreIdService extends BaseService<GetRupsBySettoreIdRequest, GetRupsBySettoreIdResponse> {

    private UtenteDad utenteDad;
	public GetRupsBySettoreIdService(ConfigurationHelper configurationHelper, UtenteDad utenteDad) {
		super(configurationHelper);
		this.utenteDad = utenteDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getId(), "id");
	}

	@Override
	protected void execute() {
		List<Utente> rups = utenteDad.getRupsBySettoreId(request.getId());
		response.setUtenteRup(rups);
	}

}
