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
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.AnteprimaOrdineSuEvasione;

public class GetAnteprimeOrdineByEvasioneIdResponse extends BaseGetResponse<List<AnteprimaOrdineSuEvasione>> {

	private List<AnteprimaOrdineSuEvasione> listaAnteprimaOrdine;

	/**
	 * @return the listaAnteprimaOrdine
	 */
	public List<AnteprimaOrdineSuEvasione> getListaAnteprimaOrdine() {
		return listaAnteprimaOrdine;
	}

	/**
	 * @param listaAnteprimaOrdine the listaAnteprimaOrdine to set
	 */
	public void setListaTestataOrdine(List<AnteprimaOrdineSuEvasione> listaAnteprimaOrdine) {
		this.listaAnteprimaOrdine = listaAnteprimaOrdine;
	}



	@Override
	protected List<AnteprimaOrdineSuEvasione> getEntity() {
		return listaAnteprimaOrdine;
	}


}
