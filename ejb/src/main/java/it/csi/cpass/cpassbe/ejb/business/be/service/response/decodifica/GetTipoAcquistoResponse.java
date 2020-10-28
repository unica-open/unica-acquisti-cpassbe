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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.decodifica;

import java.util.ArrayList;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.pba.TipoAcquisto;

/**
 * The Class GetTipoOrdineResponse.
 */
public class GetTipoAcquistoResponse extends BaseGetResponse<List<TipoAcquisto>> {

	/** The lingue. */
	private List<TipoAcquisto> listaTipoAcquisto = new ArrayList<>();

	/**
	 * @return the listaTipoAcquisto
	 */
	public List<TipoAcquisto> getListaTipoAcquisto() {
		return listaTipoAcquisto;
	}

	/**
	 * @param listaTipoAcquisto the listaTipoAcquisto to set
	 */
	public void setListaTipoAcquisto(List<TipoAcquisto> listaTipoAcquisto) {
		this.listaTipoAcquisto = listaTipoAcquisto;
	}

	@Override
	protected List<TipoAcquisto> getEntity() {
		return listaTipoAcquisto;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GetTipoAcquistoResponse [listaTipoAcquisto=").append(listaTipoAcquisto).append(", apiErrors=").append(getApiErrors()).append("]");
		return builder.toString();
	}

}
