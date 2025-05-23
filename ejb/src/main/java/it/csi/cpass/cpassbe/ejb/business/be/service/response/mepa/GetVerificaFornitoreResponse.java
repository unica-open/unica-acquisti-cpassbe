/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.service.response.mepa;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;

public class GetVerificaFornitoreResponse extends BaseGetResponse<List<Fornitore>> {
	List<Fornitore> fornitoreList;

	public List<Fornitore> getFornitoreList() {
		return fornitoreList;
	}

	public void setFornitoreList(List<Fornitore> fornitoreList) {
		this.fornitoreList = fornitoreList;
	}

	@Override
	protected List<Fornitore> getEntity() {
		return fornitoreList;
	}
}
