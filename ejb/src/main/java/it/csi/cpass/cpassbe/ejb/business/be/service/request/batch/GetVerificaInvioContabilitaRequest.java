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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.batch;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

/**
 * The Class GetVerificaQuoteDocumentiRequest.
 */
public class GetVerificaInvioContabilitaRequest implements BaseRequest {
	String enteCodice;


	/**
	 * @param enteCodice
	 */
	public GetVerificaInvioContabilitaRequest(String enteCodice) {
		super();
		this.enteCodice = enteCodice;
	}


	/**
	 * @return the enteCodice
	 */
	public String getEnteCodice() {
		return enteCodice;
	}


}