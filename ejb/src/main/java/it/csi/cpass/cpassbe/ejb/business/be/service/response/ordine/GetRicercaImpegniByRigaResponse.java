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

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.Impegno;

public class GetRicercaImpegniByRigaResponse extends BaseGetResponse<List<Impegno>> {

	private List<Impegno> listImpegno;

	/**
	 * @return the listImpegno
	 */
	public List<Impegno> getListImpegno() {
		return listImpegno;
	}

	/**
	 * @param listImpegno the listImpegno to set
	 */
	public void setListImpegno(List<Impegno> listImpegno) {
		this.listImpegno = listImpegno;
	}

	@Override
	protected List<Impegno> getEntity() {
		return listImpegno;
	}

}
