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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.Settore;

public class PostRicercaSettoreIndirizzoRequest implements BaseRequest {

	private final Settore settore;

	/**
	 * Constructor
	 *
	 * @param settore the fornitore
	 */
	public PostRicercaSettoreIndirizzoRequest(Settore settore) {
		this.settore = settore != null ? settore : new Settore();
	}

	/**
	 * @return the fornitore
	 */
	public Settore getSettore() {
		return this.settore;
	}

}
