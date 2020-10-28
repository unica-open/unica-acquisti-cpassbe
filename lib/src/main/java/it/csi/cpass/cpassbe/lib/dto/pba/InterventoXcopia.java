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
package it.csi.cpass.cpassbe.lib.dto.pba;

import java.io.Serializable;

import it.csi.cpass.cpassbe.lib.dto.ApiError;

/**
 * The Class InterventoXcopia.
 */
public class InterventoXcopia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2512027880331566576L;
	private Intervento intervento;
	private ApiError error;
	/**
	 * @return the intervento
	 */
	public Intervento getIntervento() {
		return intervento;
	}
	/**
	 * @param intervento the intervento to set
	 */
	public void setIntervento(Intervento intervento) {
		this.intervento = intervento;
	}
	/**
	 * @return the error
	 */
	public ApiError getError() {
		return error;
	}
	/**
	 * @param error the error to set
	 */
	public void setError(ApiError error) {
		this.error = error;
	}
	
	
}
