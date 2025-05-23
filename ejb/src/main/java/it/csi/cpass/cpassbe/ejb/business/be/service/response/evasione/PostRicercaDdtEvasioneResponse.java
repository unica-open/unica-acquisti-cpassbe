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

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BasePagedResponse;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DocumentoTrasporto;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedListImpl;

public class PostRicercaDdtEvasioneResponse extends BasePagedResponse<DocumentoTrasporto> {

	PagedList<DocumentoTrasporto> documentoTrasportoPagedList = new PagedListImpl<>();

	@Override
	protected PagedList<DocumentoTrasporto> getEntity() {
		return documentoTrasportoPagedList;
	}

	public PagedList<DocumentoTrasporto> getDocumentoTrasportoPagedList() {
		return documentoTrasportoPagedList;
	}

	public void setDocumentoTrasportoPagedList(PagedList<DocumentoTrasporto> documentoTrasportoPagedList) {
		this.documentoTrasportoPagedList = documentoTrasportoPagedList;
	}
}
