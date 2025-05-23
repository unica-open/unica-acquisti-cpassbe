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
package it.csi.cpass.cpassbe.lib.dto;

import java.io.Serializable;
import java.util.UUID;

/**
 * The Class InvioQuoteDocumento.
 */
public class InvioQuoteDocumento extends BaseDto<UUID> implements Serializable {

	private static final long serialVersionUID = 1L;

	protected String codiceTipoDocumento;
	protected String contenutoDocumento;

	/**
	 * @return the codiceTipoDocumento
	 */
	public String getCodiceTipoDocumento() {
		return codiceTipoDocumento;
	}

	/**
	 * @param codiceTipoDocumento the codiceTipoDocumento to set
	 */
	public void setCodiceTipoDocumento(String codiceTipoDocumento) {
		this.codiceTipoDocumento = codiceTipoDocumento;
	}

	/**
	 * @return the contenutoDocumento
	 */
	public String getContenutoDocumento() {
		return contenutoDocumento;
	}

	/**
	 * @param contenutoDocumento the contenutoDocumento to set
	 */
	public void setContenutoDocumento(String contenutoDocumento) {
		this.contenutoDocumento = contenutoDocumento;
	}

}
