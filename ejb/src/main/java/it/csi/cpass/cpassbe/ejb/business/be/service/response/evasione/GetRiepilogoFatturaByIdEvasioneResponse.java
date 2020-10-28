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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.RiepilogoFatturaEvasione;

/**
 * Response for reading TestataEvasione by its id.
 */
public class GetRiepilogoFatturaByIdEvasioneResponse extends BaseGetResponse<List<RiepilogoFatturaEvasione>> {

	private List<RiepilogoFatturaEvasione> listaRiepilogoFatturaEvasione;


	/**
	 * @return the listaRiepilogoFatturaEvasione
	 */
	public List<RiepilogoFatturaEvasione> getListaRiepilogoFatturaEvasione() {
		return listaRiepilogoFatturaEvasione;
	}


	/**
	 * @param listaRiepilogoFatturaEvasione the listaRiepilogoFatturaEvasione to set
	 */
	public void setListaRiepilogoFatturaEvasione(List<RiepilogoFatturaEvasione> listaRiepilogoFatturaEvasione) {
		this.listaRiepilogoFatturaEvasione = listaRiepilogoFatturaEvasione;
	}


	@Override
	protected List<RiepilogoFatturaEvasione> getEntity() {
		return listaRiepilogoFatturaEvasione;
	}

}
