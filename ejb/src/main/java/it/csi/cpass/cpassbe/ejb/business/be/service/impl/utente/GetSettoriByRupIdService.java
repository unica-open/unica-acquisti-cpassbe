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

import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetSettoriByRupIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetSettoriByRupIdResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Settore;

/**
 * Retrieves an GetRupsByInterventoId by its id
 */
public class GetSettoriByRupIdService extends BaseService<GetSettoriByRupIdRequest, GetSettoriByRupIdResponse> {

	private final UtenteDad utenteDad;
	public GetSettoriByRupIdService(ConfigurationHelper configurationHelper, UtenteDad utenteDad) {
		super(configurationHelper);
		this.utenteDad = utenteDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getId(), "id");
	}

	@Override
	protected void execute() {
		final List<Settore> settori = utenteDad.getSettoriByRupId(request.getId());
		response.setSettori(settori);
	}

}
