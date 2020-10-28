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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.common;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;

/**
 * The Class GetOrdinamentoByModuloFunzioneTipoResponse.
 */
public class GetOrdinamentoByModuloFunzioneTipoResponse extends BaseGetResponse<String> {

	private String ordinamento;




	/**
	 * @return the ordinamento
	 */
	public String getOrdinamento() {
		return ordinamento;
	}




	/**
	 * @param ordinamento the ordinamento to set
	 */
	public void setOrdinamento(String ordinamento) {
		this.ordinamento = ordinamento;
	}




	@Override
	protected String getEntity() {
		return ordinamento;
	}



}
