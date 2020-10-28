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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.batch;

import java.util.ArrayList;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;

/**
 * The Class GetMetadatiFunzioneResponse.
 */
public class GetVerificaQuoteDocumentiResponse extends BaseGetResponse<List<Intervento>> {

	/** The lingue. */
	private List<Intervento> lista = new ArrayList<>();


	/**
	 * @return the lista
	 */
	public List<Intervento> getLista() {
		return lista;
	}


	/**
	 * @param lista the lista to set
	 */
	public void setLista(List<Intervento> lista) {
		this.lista = lista;
	}


	@Override
	protected List<Intervento> getEntity() {
		return lista;
	}

	

}
