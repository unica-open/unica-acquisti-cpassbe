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

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

/**
 * Request for reading the Intervento by its id
 */
public class GetInterventiByCuiRequest implements BaseRequest {

	private final String cui;


	/**
	 * Constructor
	 * @param cui the cui
	 * @param idProgramma
	 */
	public GetInterventiByCuiRequest(String cui) {
		this.cui = cui;
	}

	/**
	 * @return the id
	 */
	public String getCui() {
		return cui;
	}


}
