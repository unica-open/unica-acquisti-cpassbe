/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione;


import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DocumentoTrasporto;

public class GetDocumentoTrasportoByEvasioneResponse extends BaseGetResponse<DocumentoTrasporto>{


	private DocumentoTrasporto documentoTrasporto;


	public DocumentoTrasporto getDocumentoTrasporto() {
		return documentoTrasporto;
	}


	public void setDocumentoTrasporto(DocumentoTrasporto documentoTrasporto) {
		this.documentoTrasporto = documentoTrasporto;
	}


	@Override
	protected DocumentoTrasporto getEntity() {
		return documentoTrasporto;
	}

}
