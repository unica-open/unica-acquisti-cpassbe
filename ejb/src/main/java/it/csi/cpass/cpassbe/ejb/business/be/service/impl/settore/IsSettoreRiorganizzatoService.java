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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.settore;

import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.IsSettoreRiorganizzatoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.IsSettoreRiorganizzatoResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;

/**
 * Retrieves an Moduli
 */
public class IsSettoreRiorganizzatoService extends BaseSettoreService<IsSettoreRiorganizzatoRequest, IsSettoreRiorganizzatoResponse> {

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param utenteDad the utente DAD
	 */
	public IsSettoreRiorganizzatoService(ConfigurationHelper configurationHelper, SettoreDad settoreDad) {
		super(configurationHelper, settoreDad);
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getIdSettoreAttuale(), "settoreId attuale");
		checkNotNull(request.getIdSettorePrecedente(), "ssettoreId precedente");
	}

	@Override
	protected void execute() {
		final boolean ris = settoreDad.isSettoreRiorganizzato(request.getIdSettoreAttuale(), request.getIdSettorePrecedente());
		response.setIsParent(ris);
	}

}
