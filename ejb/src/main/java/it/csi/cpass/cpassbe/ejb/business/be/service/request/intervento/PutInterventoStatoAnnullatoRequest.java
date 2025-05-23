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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento;

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;

/**
 * Request for putting the stato Intervento
 */
public class PutInterventoStatoAnnullatoRequest implements BaseRequest {

	private final Intervento intervento;
	private final UUID settoreId;

	/**
	 * Constructor
	 * @param intervento the intervento
	 */
	public PutInterventoStatoAnnullatoRequest(Intervento intervento, UUID settoreId) {
		this.intervento = intervento;
		this.settoreId = settoreId;
	}

	/**
	 * @return the intervento
	 */
	public Intervento getIntervento() {
		return intervento;
	}

	/**
	 * @return the settoreId
	 */
	public UUID getSettoreId() {
		return settoreId;
	}

}
