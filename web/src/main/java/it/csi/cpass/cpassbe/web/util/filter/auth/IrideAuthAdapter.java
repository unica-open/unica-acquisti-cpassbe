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
		//demo ANNA
		//identita.setCodFiscale("TLLNRT69E56D122C");
		//demo Paolo bertolotti
		//identita.setCodFiscale("BRTPFR80C14L013A");
		//demo 21
		identita.setCodFiscale("AAAAAA00A11B000J");
		//SABATINO AURORA
		//identita.setCodFiscale("SBTRRA03M42L219R");
		//demo 24
		//identita.setCodFiscale("AAAAAA00A11E000M");
		//demo 25
		//identita.setCodFiscale("AAAAAA00A11F000N");
		//demo 26
		//identita.setCodFiscale("AAAAAA00A11G000O");
		//demo 27
		//identita.setCodFiscale("AAAAAA00A11H000P");
		//demo 28
		//identita.setCodFiscale("AAAAAA00A11I000Q");
		//demo 20
		//identita.setCodFiscale("AAAAAA00B77B000F");
		//demo 29
		//identita.setCodFiscale("AAAAAA00A11J000R");
		//BARBERO
		//identita.setCodFiscale("BRBFRZ64A01L219J");
		//demo 40
		//identita.setCodFiscale("AAAAAA00A11P000X");
		//demo 34
		//identita.setCodFiscale("AAAAAA00A11O000W");
		//demo 22
		//identita.setCodFiscale("AAAAAA00A11C000K");
		//demo 23
		//identita.setCodFiscale("AAAAAA00A11D000L");
		//demo 35
		//identita.setCodFiscale("AAAAAA00A11R000Z");
		return identita;
	}

}


