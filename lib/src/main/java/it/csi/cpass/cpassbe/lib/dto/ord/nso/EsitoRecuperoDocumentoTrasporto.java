/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto.ord.nso;

import java.io.Serializable;

import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DocumentoTrasporto;

public class EsitoRecuperoDocumentoTrasporto implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 7043471263487087944L;
	private String codErrore;
	private String descErrore;
	private DocumentoTrasporto documentoTrasporto;


	/**
	 * @return the codErrore
	 */
	public String getCodErrore() {
		return codErrore;
	}

	/**
	 * @param codErrore the codErrore to set
	 */
	public void setCodErrore(String codErrore) {
		this.codErrore = codErrore;
	}

	/**
	 * @return the descErrore
	 */
	public String getDescErrore() {
		return descErrore;
	}

	/**
	 * @param descErrore the descErrore to set
	 */
	public void setDescErrore(String descErrore) {
		this.descErrore = descErrore;
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
