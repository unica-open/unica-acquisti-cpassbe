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
import it.csi.cpass.cpassbe.lib.dto.Provvedimento;

/**
 * Request for posting the Provvedimento
 */
public class PostRicercaProvvedimentoRequest implements BaseRequest {

	private final Provvedimento provvedimento;

	/**
	 * Constructor
	 *
	 * @param provvedimento the provvedimento
	 */
	public PostRicercaProvvedimentoRequest(Provvedimento provvedimento) {
		this.provvedimento = provvedimento;
	}

	/**
	 * @return the provvedimento
	 */
	public Provvedimento getProvvedimento() {
		return provvedimento;
	}

}
