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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;

/**
 * Response for reading TestataEvasione by its id.
 */
public class GetTestateOrdineByEvasioneIdResponse extends BaseGetResponse<List<TestataOrdine>> {

	private List<TestataOrdine> listaTestataOrdine;

	/**
	 * @return the listaTestataOrdine
	 */
	public List<TestataOrdine> getListaTestataOrdine() {
		return listaTestataOrdine;
	}

	/**
	 * @param listaTestataOrdine the listaTestataOrdine to set
	 */
	public void setListaTestataOrdine(List<TestataOrdine> listaTestataOrdine) {
		this.listaTestataOrdine = listaTestataOrdine;
	}



	@Override
	protected List<TestataOrdine> getEntity() {
		return listaTestataOrdine;
	}


}
