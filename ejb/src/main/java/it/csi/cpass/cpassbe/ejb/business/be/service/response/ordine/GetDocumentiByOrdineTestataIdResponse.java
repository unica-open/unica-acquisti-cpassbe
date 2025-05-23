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
import it.csi.cpass.cpassbe.lib.dto.ord.DocumentiOrdine;

public class GetDocumentiByOrdineTestataIdResponse extends BaseGetResponse<List<DocumentiOrdine>> {

	private List<DocumentiOrdine> lista;

	/**
	 * @return the lista
	 */
	public List<DocumentiOrdine> getLista() {
		return lista;
	}

	/**
	 * @param lista the lista to set
	 */
	public void setLista(List<DocumentiOrdine> lista) {
		this.lista = lista;
	}

	@Override
	protected List<DocumentiOrdine> getEntity() {
		return lista;
	}

}
