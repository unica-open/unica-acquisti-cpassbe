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
package it.csi.cpass.cpassbe.lib.dto.ord;

import java.io.Serializable;

public class EsitoInvioDocumento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7043471263487087944L;
	private String urnDocumento;

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

}
