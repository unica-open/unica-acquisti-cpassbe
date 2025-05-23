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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.base;

public class BaseInviaOrdineNSOResponse extends BaseGetResponse<Object> {

	private String esitoInvio;

	/**
	 * @return the esitoInvio
	 */
	public String getEsitoInvio() {
		return esitoInvio;
	}

	/**
	 * @param esitoInvio the esitoInvio to set
	 */
	public void setEsitoInvio(String esitoInvio) {
		this.esitoInvio = esitoInvio;
	}

	@Override
	protected Object getEntity() {
		// TODO Auto-generated method stub
		return esitoInvio;
	}

}
