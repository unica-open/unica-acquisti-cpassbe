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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.common;

import java.util.ArrayList;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.GestioneCampo;

/**
 * The Class GetUfficioResponse.
 */
public class GetGestioneCampiResponse extends BaseGetResponse<List<GestioneCampo>> {

	/** The lingue. */
	private List<GestioneCampo> list = new ArrayList<>();




	/**
	 * @return the list
	 */
	public List<GestioneCampo> getList() {
		return list;
	}




	/**
	 * @param list the list to set
	 */
	public void setList(List<GestioneCampo> list) {
		this.list = list;
	}




	@Override
	protected List<GestioneCampo> getEntity() {
		return list;
	}


}
