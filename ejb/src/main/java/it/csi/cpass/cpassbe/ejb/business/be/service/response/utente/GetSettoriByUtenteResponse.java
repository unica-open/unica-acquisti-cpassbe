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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.utente;

import java.util.ArrayList;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.Settore;

/**
 * Response for reading lista settori by the id of the parent utente.
 */
public class GetSettoriByUtenteResponse extends BaseGetResponse<List<Settore>> {

	private List<Settore> settori = new ArrayList<>();



	/**
	 * @return the settori
	 */
	public List<Settore> getSettori() {
		return settori;
	}



	/**
	 * @param settori the settori to set
	 */
	public void setSettori(List<Settore> settori) {
		this.settori = settori;
	}



	@Override
	protected List<Settore> getEntity() {
		return settori;
	}

}
