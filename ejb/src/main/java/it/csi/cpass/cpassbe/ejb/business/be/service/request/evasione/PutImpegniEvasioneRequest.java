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
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.SalvaImpegniEvasione;

public class PutImpegniEvasioneRequest implements BaseRequest {

	private final SalvaImpegniEvasione salvaImpegniEvasione;

	/**
	 * Constructor
	 * 
	 * @param salvaImpegniEvasione
	 */
	public PutImpegniEvasioneRequest(SalvaImpegniEvasione salvaImpegniEvasione) {
		this.salvaImpegniEvasione = salvaImpegniEvasione;
	}

	/**
	 * @return the salvaImpegniEvasione
	 */
	public SalvaImpegniEvasione getSalvaImpegniEvasione() {
		return salvaImpegniEvasione;
	}

}
