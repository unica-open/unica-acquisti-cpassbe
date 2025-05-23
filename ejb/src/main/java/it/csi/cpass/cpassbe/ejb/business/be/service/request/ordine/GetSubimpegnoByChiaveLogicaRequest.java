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
import it.csi.cpass.cpassbe.lib.dto.Subimpegno;

public class GetSubimpegnoByChiaveLogicaRequest implements BaseRequest {

	private final Subimpegno subimpegno;

	/**
	 * Constructor
	 *
	 * @param idRiga the riga id
	 */
	public GetSubimpegnoByChiaveLogicaRequest(Subimpegno subimpegno) {
		this.subimpegno = subimpegno;
	}

	public Subimpegno getSubimpegno() {
		return subimpegno;
	}

}
