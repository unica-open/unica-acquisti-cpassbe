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
import it.csi.cpass.cpassbe.lib.dto.pba.StatiIntervento;

public class GetStatiInterventoByInterventoIdResponse extends BaseGetResponse<List<StatiIntervento>> {

	private List<StatiIntervento> listaStatiIntervento;

	/**
	 * @return the listaStatiIntervento
	 */
	public List<StatiIntervento> getListaStatiIntervento() {
		return listaStatiIntervento;
	}

	/**
	 * @param listaStatiIntervento the listaStatiIntervento to set
	 */
	public void setListaStatiIntervento(List<StatiIntervento> listaStatiIntervento) {
		this.listaStatiIntervento = listaStatiIntervento;
	}

	@Override
	protected List<StatiIntervento> getEntity() {
		// TODO Auto-generated method stub
		return listaStatiIntervento;
	}


}
