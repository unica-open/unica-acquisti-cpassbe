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

import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.dto.ord.mepa.ScaricoMepaTestata;

public class PostOrdineMepaBodyWrapper {
	private FormTestataOrdineMepa formTestataOrdineMepa;
	private ScaricoMepaTestata scaricoMepaTestata;
	private Fornitore fornitore;

	public FormTestataOrdineMepa getFormTestataOrdineMepa() {
		return formTestataOrdineMepa;
	}

	public void setFormTestataOrdineMepa(FormTestataOrdineMepa formTestataOrdineMepa) {
		this.formTestataOrdineMepa = formTestataOrdineMepa;
	}

	public ScaricoMepaTestata getScaricoMepaTestata() {
		return scaricoMepaTestata;
	}

	public void setScaricoMepaTestata(ScaricoMepaTestata scaricoMepaTestata) {
		this.scaricoMepaTestata = scaricoMepaTestata;
	}

	public Fornitore getFornitore() {
		return fornitore;
	}

	public void setFornitore(Fornitore fornitore) {
		this.fornitore = fornitore;
	}
}
