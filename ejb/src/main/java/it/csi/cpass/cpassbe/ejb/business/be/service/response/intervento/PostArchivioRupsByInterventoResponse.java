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
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;

/**
 * Response for reading rups by its id.
 */
public class PostArchivioRupsByInterventoResponse extends BaseGetResponse<List<Intervento>> {

	private List<Intervento> interventi;

	/**
	 * @return the interventi
	 */
	public List<Intervento> getInterventi() {
		return interventi;
	}

	/**
	 * @param interventi the interventi to set
	 */
	public void setInterventi(List<Intervento> interventi) {
		this.interventi = interventi;
	}

	@Override
	protected List<Intervento> getEntity() {
		return interventi;
	}

}
