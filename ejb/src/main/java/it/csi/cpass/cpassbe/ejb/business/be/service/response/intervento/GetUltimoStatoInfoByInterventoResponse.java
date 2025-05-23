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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.custom.StatoInterventoInfo;

public class GetUltimoStatoInfoByInterventoResponse extends BaseGetResponse<List<StatoInterventoInfo>> {

	private List<StatoInterventoInfo> listastatoInterventoInfo;

	/**
	 * @return the listastatoInterventoInfo
	 */
	public List<StatoInterventoInfo> getListastatoInterventoInfo() {
		return listastatoInterventoInfo;
	}

	/**
	 * @param listastatoInterventoInfo the listastatoInterventoInfo to set
	 */
	public void setListastatoInterventoInfo(List<StatoInterventoInfo> listastatoInterventoInfo) {
		this.listastatoInterventoInfo = listastatoInterventoInfo;
	}

	@Override
	protected List<StatoInterventoInfo> getEntity() {
		return listastatoInterventoInfo;
	}

}
