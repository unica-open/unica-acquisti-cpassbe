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

public class PostImpegniEvasioneResponse extends BaseGetResponse<List<ImpegnoEvasione>> {

	private List<ImpegnoEvasione> impegnoEvasiones;

	/**
	 * @return the impegnoEvasiones
	 */
	public List<ImpegnoEvasione> getImpegnoEvasiones() {
		return impegnoEvasiones;
	}

	/**
	 * @param impegnoEvasiones the impegnoEvasiones to set
	 */
	public void setImpegnoEvasiones(List<ImpegnoEvasione> impegnoEvasiones) {
		this.impegnoEvasiones = impegnoEvasiones;
	}

	@Override
	protected List<ImpegnoEvasione> getEntity() {
		return impegnoEvasiones;
	}

}
