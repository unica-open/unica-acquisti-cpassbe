/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.SalvaEvasione;

/**
 * Request for posting the TestataEvasione
 */
public class PostTestataEvasioneRequest implements BaseRequest {

	private final SalvaEvasione salvaEvasione;

	/**
	 * Constructor
	 * 
	 * @param salvaEvasione the salvaEvasione
	 */
	public PostTestataEvasioneRequest(SalvaEvasione salvaEvasione) {
		this.salvaEvasione = salvaEvasione;
	}

	/**
	 * @return the testataEvasione
	 */
	public SalvaEvasione getSalvaEvasione() {
		return salvaEvasione;
	}

}
