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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.common;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

/**
 * The Class GetMetadatiByModuoloFunzioneRequest.
 */
public class GetMetadatiByModuoloFunzioneRequest implements BaseRequest {
	private final String modulo;
	private final String funzione;

	public GetMetadatiByModuoloFunzioneRequest(String modulo, String funzione) {
		this.modulo = modulo;
		this.funzione = funzione;
	}
	/**
	 * @return the modulo
	 */
	public String getModulo() {
		return modulo;
	}
	/**
	 * @return the funzione
	 */
	public String getFunzione() {
		return funzione;
	}

}
