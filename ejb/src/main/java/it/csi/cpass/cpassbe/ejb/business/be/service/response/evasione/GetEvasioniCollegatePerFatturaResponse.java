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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.TestataEvasione;

public class GetEvasioniCollegatePerFatturaResponse extends BaseGetResponse<List<TestataEvasione>> {

	private List<TestataEvasione> listaEvasioni;

	/**
	 * @return the listaEvasioni
	 */
	public List<TestataEvasione> getListaEvasioni() {
		return listaEvasioni;
	}

	/**
	 * @param listaEvasioni the listaEvasioni to set
	 */
	public void setListaEvasioni(List<TestataEvasione> listaEvasioni) {
		this.listaEvasioni = listaEvasioni;
	}

	@Override
	protected List<TestataEvasione> getEntity() {
		return listaEvasioni;
	}

}
