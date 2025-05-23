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

import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetPermessiDaGestireRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetPermessiDaGestireResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.RuoloPermesso;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;

public class GetPermessiDaGestireService extends BaseUtenteService<GetPermessiDaGestireRequest, GetPermessiDaGestireResponse> {

	public GetPermessiDaGestireService(ConfigurationHelper configurationHelper, UtenteDad utenteDad) {
		super(configurationHelper, utenteDad);
	}

	@Override
	protected void execute() {
		final PagedList<RuoloPermesso> ruoloPermessi = utenteDad.getPermessiDaGestire(request.getPage(), request.getSize(), request.getSort());
		response.setRuoloPermesso(ruoloPermessi);
	}

}
