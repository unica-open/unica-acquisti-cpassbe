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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.common;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;

/**
 * Request for posting the Fornitore
 */
public class PostRicercaFornitoreRequest implements BaseRequest {

	private final Fornitore fornitore;

	/**
	 * Constructor
	 *
	 * @param fornitore the fornitore
	 */
	public PostRicercaFornitoreRequest(Fornitore fornitore) {
		this.fornitore = fornitore;
	}

	/**
	 * @return the fornitore
	 */
	public Fornitore getFornitore() {
		return fornitore;
	}

}
