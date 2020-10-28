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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;

/**
 * Request for posting the Intervento
 */
public class PostInterventoRequest implements BaseRequest {

	private final Intervento intervento;

	/**
	 * Constructor
	 * @param intervento the intervento
	 */
	public PostInterventoRequest(Intervento intervento) {
		this.intervento = intervento;
	}

	/**
	 * @return the intervento
	 */
	public Intervento getIntervento() {
		return intervento;
	}
}
