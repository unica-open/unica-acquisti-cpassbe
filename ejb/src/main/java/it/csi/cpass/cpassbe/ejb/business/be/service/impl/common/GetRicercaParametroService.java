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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.common;

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.GetRicercaParametroRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.GetRicercaParametroResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Parametro;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

public class GetRicercaParametroService extends BaseCommonService<GetRicercaParametroRequest,GetRicercaParametroResponse> {

	private final SystemDad systemDad;



	public GetRicercaParametroService(ConfigurationHelper configurationHelper, CommonDad commonDad,
			SystemDad systemDad) {
		super(configurationHelper, commonDad);
		this.systemDad = systemDad;
	}


	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getChiave(),"chiave parametro");
	}

	@Override
	protected void execute() {
		final String chiave = request.getChiave();
		final Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		final Ente ente = settoreCorrente.getEnte();
		final Parametro parametro = systemDad.getParametro(chiave, null, ente.getId());
		response.setParametro(parametro);
	}

}
