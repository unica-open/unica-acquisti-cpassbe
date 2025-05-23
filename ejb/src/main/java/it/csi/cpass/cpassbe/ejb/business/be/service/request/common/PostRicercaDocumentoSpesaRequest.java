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
import it.csi.cpass.cpassbe.lib.dto.DocumentoSpesa;

/**
 * Request for posting the Fornitore
 */
public class PostRicercaDocumentoSpesaRequest implements BaseRequest {

	private final DocumentoSpesa ds;

	/**
	 * Constructor
	 *
	 * @param DocumentoSpesa the DocumentoSpesa
	 */
	public PostRicercaDocumentoSpesaRequest(DocumentoSpesa ds) {
		this.ds = ds;
	}

	/**
	 * @return the fornitore
	 */
	public DocumentoSpesa getDocumentoSpesa() {
		return ds;
	}

}
