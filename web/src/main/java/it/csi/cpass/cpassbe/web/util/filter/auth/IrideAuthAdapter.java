/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - WAR submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.web.util.filter.auth;

import javax.enterprise.context.ApplicationScoped;

import it.csi.cpass.cpassbe.web.util.filter.auth.provider.iride.Identita;

/**
 * Auth adapter for IRIDE
 */
@ApplicationScoped
public class IrideAuthAdapter extends BaseIrideAuthAdapter {

	@Override
	protected Identita initializeDevModeIdentita() {
		Identita identita = new Identita();
		identita.setCodFiscale("AAAAAA00A11B000J");
		return identita;
	}

}
