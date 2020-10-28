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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.decodifica;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

/**
 * The Class GetStatoElOrdineByTipoRequest.
 */
public class GetStatoElOrdineByTipoRequest implements BaseRequest {
	private String tipo;
	/**
	 * Constructor
	 * @param tipo the tipo
	 * @param idProgramma 
	 */
	public GetStatoElOrdineByTipoRequest(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
}
