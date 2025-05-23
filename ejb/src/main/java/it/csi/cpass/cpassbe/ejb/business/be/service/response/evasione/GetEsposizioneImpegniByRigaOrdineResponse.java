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

public class GetEsposizioneImpegniByRigaOrdineResponse extends BaseGetResponse<List<ImpegnoEvasione>> {

	private List<ImpegnoEvasione> listImpegnoEvasione;

	/**
	 * @return the listImpegnoEvasione
	 */
	public List<ImpegnoEvasione> getListImpegnoEvasione() {
		return listImpegnoEvasione;
	}

	/**
	 * @param listImpegno the listImpegno to set
	 */
	public void setListImpegnoEvasione(List<ImpegnoEvasione> listImpegnoEvasione) {
		this.listImpegnoEvasione = listImpegnoEvasione;
	}

	@Override
	protected List<ImpegnoEvasione> getEntity() {
		return listImpegnoEvasione;
	}

}
