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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine;

import java.util.ArrayList;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.Provvedimento;

/**
 * Response for reading list of Provvedimento.
 */
public class PostRicercaProvvedimentoResponse extends BaseGetResponse<List<Provvedimento>> {

	private List<Provvedimento> provvedimenti = new ArrayList<>();

	/**
	 * @return the provvedimenti
	 */
	public List<Provvedimento> getProvvedimenti() {
		return provvedimenti;
	}

	/**
	 * @param provvedimenti the provvedimenti to set
	 */
	public void setProvvedimenti(List<Provvedimento> provvedimenti) {
		this.provvedimenti = provvedimenti;
	}

	@Override
	protected List<Provvedimento> getEntity() {
		// TODO Auto-generated method stub
		return provvedimenti;
	}





}
