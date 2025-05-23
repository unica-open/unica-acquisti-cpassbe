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

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;


/**
 * The Class GetMetadatiFunzioneResponse.
 */
public class GetCaricamentoAggiornamentiSubImpegniResponse extends BaseGetResponse<List<Intervento>> {

	/** The lingue. */
	private List<Intervento> lista = new ArrayList<>();
	private String esito;

	@Override
	protected Response composeOwnResponse() {
		if("KO".equals(esito)) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		return super.composeOwnResponse();
	}

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


	/**
	 * @return the esito
	 */
	public String getEsito() {
		return esito;
	}


	/**
	 * @param esito the esito to set
	 */
	public void setEsito(String esito) {
		this.esito = esito;
	}



}
