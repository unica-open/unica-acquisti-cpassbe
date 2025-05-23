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
import it.csi.cpass.cpassbe.lib.dto.ord.SalvaImpegni;

/**
 * Request for posting the TestataOrdine
 */
public class PostImpegniRequest implements BaseRequest {

	private final SalvaImpegni salvaImpegni;

	/**
	 * Constructor
	 *
	 * @param salvaImpegni the salvaImpegni
	 */
	public PostImpegniRequest(SalvaImpegni salvaImpegni) {
		this.salvaImpegni = salvaImpegni;
	}

	/**
	 * @return the salvaImpegni
	 */
	public SalvaImpegni getSalvaImpegni() {
		return salvaImpegni;
	}

}
