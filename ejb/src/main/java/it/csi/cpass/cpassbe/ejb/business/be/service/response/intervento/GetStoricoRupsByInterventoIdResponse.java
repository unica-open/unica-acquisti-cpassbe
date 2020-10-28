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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.pba.StoricoInterventoRup;

/**
 * Response for reading rups by its id.
 */
public class GetStoricoRupsByInterventoIdResponse extends BaseGetResponse<List<StoricoInterventoRup>> {

	private List<StoricoInterventoRup> storicoInterventoRup;





	/**
	 * @return the interventoStoricoRup
	 */
	public List<StoricoInterventoRup> getInterventoStoricoRup() {
		return storicoInterventoRup;
	}





	/**
	 * @param storicoInterventoRup the interventoStoricoRup to set
	 */
	public void setInterventoStoricoRup(List<StoricoInterventoRup> storicoInterventoRup) {
		this.storicoInterventoRup = storicoInterventoRup;
	}





	@Override
	protected List<StoricoInterventoRup> getEntity() {
		return storicoInterventoRup;
	}

}
