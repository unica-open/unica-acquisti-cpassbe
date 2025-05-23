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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.magazzino;

import java.util.ArrayList;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.mag.Magazzino;

/**
 * Response for reading Magazzini.
 */
public class GetMagazziniResponse extends BaseGetResponse<List<Magazzino>> {

	private List<Magazzino> magazzini = new ArrayList<>();

	@Override
	protected List<Magazzino> getEntity() {
		// TODO Auto-generated method stub
		return magazzini;
	}

	/**
	 * @return the magazzini
	 */
	public List<Magazzino> getMagazzini() {
		return magazzini;
	}

	/**
	 * @param magazzini the magazzini to set
	 */
	public void setMagazzini(List<Magazzino> magazzini) {
		this.magazzini = magazzini;
	}



}
