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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.utente;

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

/**
 * Request for get utente by settore
 */
public class GetUtenteBySettoreRuoloRequest implements BaseRequest {
	private final UUID   settoreId;
	private final String ruoloCodice;

	/**
	 * Constructor
	 * @param settoreId the settoreId
	 * @param ruoloCodice the ruolo codice
	 */
	public GetUtenteBySettoreRuoloRequest(UUID settoreId,String ruoloCodice) {
		this.settoreId = settoreId;
		this.ruoloCodice = ruoloCodice;
	}

	/**
	 * @return the settoreId
	 */
	public UUID getSettoreId() {
		return settoreId;
	}

	/**
	 * @return the ruoloCode
	 */
	public String getRuoloCodice() {
		return ruoloCodice;
	}	
}
