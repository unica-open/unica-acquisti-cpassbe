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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.utente;

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

/**
 * Request for reading the Permessi
 */
public class GetPermessiBySettoreAndModuloRequest implements BaseRequest {
	private final UUID settoreId;
	private final Integer idModulo;

	/**
	 * Constructor
	 * @param settoreId the id settore
	 * @param idModulo the id modulo
	 */
	public GetPermessiBySettoreAndModuloRequest(UUID settoreId, Integer idModulo) {
		this.settoreId = settoreId;
		this.idModulo = idModulo;
	}

	/**
	 * @return the settoreId
	 */
	public UUID getSettoreId() {
		return settoreId;
	}

	/**
	 * @return the idModulo
	 */
	public Integer getIdModulo() {
		return idModulo;
	}

}
