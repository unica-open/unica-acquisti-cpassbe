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

/**
 * Request for reading the Interventos
 */
public class GetCigByInterventoIdRequest implements BaseRequest {
	private final UUID idIntervento;
	/**
	 * Constructor
	 * @param id the intervento id
	 */
	public GetCigByInterventoIdRequest(UUID idIntervento) {
		this.idIntervento = idIntervento;
	}
	/**
	 * @return the id
	 */
	public UUID getId() {
		return idIntervento;
	}
}
