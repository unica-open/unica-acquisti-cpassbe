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

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.ord.DocumentiOrdine;

public class GetDocumentoByIdResponse extends BaseGetResponse<DocumentiOrdine> {

	private DocumentiOrdine documento;


	/**
	 * @return the documento
	 */
	public DocumentiOrdine getDocumento() {
		return documento;
	}


	/**
	 * @param documento the documento to set
	 */
	public void setDocumento(DocumentiOrdine documento) {
		this.documento = documento;
	}


	@Override
	protected DocumentiOrdine getEntity() {
		// TODO Auto-generated method stub
		return documento;
	}



}
