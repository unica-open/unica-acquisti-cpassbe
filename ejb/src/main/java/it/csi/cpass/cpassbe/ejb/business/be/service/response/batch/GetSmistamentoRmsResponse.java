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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.batch;

import java.util.ArrayList;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DocumentoTrasporto;

/**
 * The Class GetMetadatiFunzioneResponse.
 */
public class GetSmistamentoRmsResponse extends BaseGetResponse<List<DocumentoTrasporto>> {

	/** The lingue. */
	private List<DocumentoTrasporto> lista = new ArrayList<>();


	/**
	 * @return the lista
	 */
	public List<DocumentoTrasporto> getLista() {
		return lista;
	}


	/**
	 * @param lista the lista to set
	 */
	public void setLista(List<DocumentoTrasporto> lista) {
		this.lista = lista;
	}


	@Override
	protected List<DocumentoTrasporto> getEntity() {
		return lista;
	}



}
