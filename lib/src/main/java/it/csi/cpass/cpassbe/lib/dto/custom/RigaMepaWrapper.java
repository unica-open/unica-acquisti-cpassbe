/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto.custom;

import it.csi.cpass.cpassbe.lib.dto.Ods;
import it.csi.cpass.cpassbe.lib.dto.ord.mepa.ScaricoMepaRiga;

public class RigaMepaWrapper {
	private ScaricoMepaRiga scaricoMepaRiga;
	private Ods ods;
	// non mappo il flag showIconaGestisci in quanto serve solo a FE

	public ScaricoMepaRiga getScaricoMepaRiga() {
		return scaricoMepaRiga;
	}

	public void setScaricoMepaRiga(ScaricoMepaRiga scaricoMepaRiga) {
		this.scaricoMepaRiga = scaricoMepaRiga;
	}

	public Ods getOds() {
		return ods;
	}

	public void setOds(Ods ods) {
		this.ods = ods;
	}
}
