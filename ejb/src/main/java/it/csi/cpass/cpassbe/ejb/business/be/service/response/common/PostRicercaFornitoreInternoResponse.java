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
import it.csi.cpass.cpassbe.lib.dto.Fornitore;

/**
 * Response for reading list of Fornitore.
 */
public class PostRicercaFornitoreInternoResponse extends BaseGetResponse<List<Fornitore>> {

	private List<Fornitore> list = new ArrayList<>();

	/**
	 * @return the fornitori
	 */
	public List<Fornitore> getFornitori() {
		return list;
	}

	/**
	 * @param fornitori the fornitori to set
	 */
	public void setFornitori(List<Fornitore> fornitori) {
		this.list = fornitori != null ? fornitori : new ArrayList<>();
	}

	@Override
	protected List<Fornitore> getEntity() {
		return list;
	}

}
