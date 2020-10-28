/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto.ord.evasione;

import java.io.Serializable;

import it.csi.cpass.cpassbe.lib.dto.BaseDto;


public class DocumentoTrasportoRiga  extends BaseDto<Integer> implements Serializable {
	private static final long serialVersionUID = 1L;
	private DocumentoTrasporto documentoTrasporto;


	public DocumentoTrasportoRiga() {
	}


	/**
	 * @return the documentoTrasporto
	 */
	public DocumentoTrasporto getDocumentoTrasporto() {
		return documentoTrasporto;
	}


	/**
	 * @param documentoTrasporto the documentoTrasporto to set
	 */
	public void setDocumentoTrasporto(DocumentoTrasporto documentoTrasporto) {
		this.documentoTrasporto = documentoTrasporto;
	}



}
