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

public class EsitoInvioDocumento implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 7043471263487087944L;
	private String codErrore;
	private String descErrore;
	private String urnDocumento;
	private Documento documento;


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
	 * @return the urnDocumento
	 */
	public String getUrnDocumento() {
		return urnDocumento;
	}

	/**
	 * @param urnDocumento the urnDocumento to set
	 */
	public void setUrnDocumento(String urnDocumento) {
		this.urnDocumento = urnDocumento;
	}

	/**
	 * @return the documento
	 */
	public Documento getDocumento() {
		return documento;
	}

	/**
	 * @param documento the documento to set
	 */
	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

}
