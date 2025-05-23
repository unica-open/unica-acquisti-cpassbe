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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.ImpegnoEvasione;

public class GetRicercaImpegniByRigaEvasioneResponse extends BaseGetResponse<List<ImpegnoEvasione>> {

	private List<ImpegnoEvasione> listImpegno;

	/**
	 * @return the listImpegno
	 */
	public List<ImpegnoEvasione> getListImpegno() {
		return listImpegno;
	}

	/**
	 * @param listImpegno the listImpegno to set
	 */
	public void setListImpegno(List<ImpegnoEvasione> listImpegno) {
		this.listImpegno = listImpegno;
	}

	@Override
	protected List<ImpegnoEvasione> getEntity() {
		return listImpegno;
	}

}
