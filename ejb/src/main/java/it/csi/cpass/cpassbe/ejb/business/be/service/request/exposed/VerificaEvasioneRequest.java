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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.exposed;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.exposed.dto.Evasione;

/**
 * The Class VerificaEvasioneRequest.
 */
public class VerificaEvasioneRequest implements BaseRequest {
	private final Evasione evasioni;

	public VerificaEvasioneRequest(Evasione evasioni) {
		this.evasioni = evasioni;
	}
	/**
	 * @return the evasioni
	 */
	public Evasione getEvasioni() {
		return evasioni;
	}

}
