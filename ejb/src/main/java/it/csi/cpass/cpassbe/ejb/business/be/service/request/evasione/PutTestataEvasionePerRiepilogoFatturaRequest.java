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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.TestataEvasione;

public class PutTestataEvasionePerRiepilogoFatturaRequest implements BaseRequest {

	private final TestataEvasione testataEvasione;
	private final Boolean bypassControl;
	private final Boolean bypassFornitoreControl;

	/**
	 * Constructor
	 *
	 * @param testataEvasione the testataEvasione
	 */
	public PutTestataEvasionePerRiepilogoFatturaRequest(TestataEvasione testataEvasione, Boolean bypassControl, Boolean bypassFornitoreControl) {
		this.testataEvasione = testataEvasione;
		this.bypassControl = bypassControl;
		this.bypassFornitoreControl = bypassFornitoreControl;
	}

	/**
	 * @return the testataEvasione
	 */
	public TestataEvasione getTestataEvasione() {
		return testataEvasione;
	}

	/**
	 * @return the bypassControl
	 */
	public Boolean isBypassControl() {
		return bypassControl;
	}

	/**
	 * @return the bypassFornitoreControl
	 */
	public Boolean isBypassFornitoreControl() {
		return bypassFornitoreControl;
	}

}
