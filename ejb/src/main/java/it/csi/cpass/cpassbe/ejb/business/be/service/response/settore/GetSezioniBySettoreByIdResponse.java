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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.settore;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.ord.Sezione;

/**
 * Response for reading Intervento by its id.
 */
public class GetSezioniBySettoreByIdResponse extends BaseGetResponse<List<Sezione>> {

	private List<Sezione> sezioni;

	/**
	 * @return the sezioni
	 */
	public List<Sezione> getSezioni() {
		return sezioni;
	}

	/**
	 * @param sezioni the sezioni to set
	 */
	public void setSezioni(List<Sezione> sezioni) {
		this.sezioni = sezioni;
	}

	@Override
	protected List<Sezione> getEntity() {
		// TODO Auto-generated method stub
		return sezioni;
	}




}
