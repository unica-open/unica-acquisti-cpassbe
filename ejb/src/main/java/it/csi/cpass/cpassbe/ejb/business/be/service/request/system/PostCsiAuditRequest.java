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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.system;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

/**
 * The Class GetComunicazioneRequest.
 */
public class PostCsiAuditRequest implements BaseRequest {

	private final String cf;
	private final String azione;

	
	public String getCf() {
		return cf;
	}
	
	public String getAzione() {
		return azione;
	}

	public PostCsiAuditRequest(String cf,String azione) {
		super();
		this.cf = cf;
		this.azione = azione;		
	}
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("PostLogOutRequest []");
		return builder.toString();
	}

}
