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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione;

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DocumentoTrasporto;

public class PostTestataEvasioneFromDTRequest implements BaseRequest {
	private final DocumentoTrasporto documentoTrasporto;
	private final UUID idSettore;

	/**
	 * Constructor
	 * @param documentoTrasporto
	 */
	public PostTestataEvasioneFromDTRequest(DocumentoTrasporto documentoTrasporto, UUID idSettore) {
		this.documentoTrasporto = documentoTrasporto;
		this.idSettore = idSettore;
	}

	/**
	 *
	 * @return Documento di Trasporto object
	 */
	public DocumentoTrasporto getDocumentoTrasporto() {
		return documentoTrasporto;
	}

	/**
	 *
	 * @return the idSettore of the connected user
	 */
	public UUID getIdSettore() {
		return idSettore;
	}


}
