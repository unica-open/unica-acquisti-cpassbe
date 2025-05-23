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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.system;

import java.util.ArrayList;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.Comunicazione;

/**
 * The Class GetComunicazioneResponse.
 */
public class GetComunicazioneResponse extends BaseGetResponse<List<Comunicazione>> {

	/** The comunicaziones. */
	private List<Comunicazione> comunicaziones = new ArrayList<>();

	/**
	 * @return the comunicaziones
	 */
	public List<Comunicazione> getComunicaziones() {
		return comunicaziones;
	}

	/**
	 * @param comunicaziones the comunicaziones to set
	 */
	public void setComunicaziones(List<Comunicazione> comunicaziones) {
		this.comunicaziones = comunicaziones;
	}

	@Override
	protected List<Comunicazione> getEntity() {
		return comunicaziones;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("GetComunicazioneResponse [comunicaziones=").append(comunicaziones).append(", apiErrors=").append(getApiErrors()).append("]");
		return builder.toString();
	}

}
