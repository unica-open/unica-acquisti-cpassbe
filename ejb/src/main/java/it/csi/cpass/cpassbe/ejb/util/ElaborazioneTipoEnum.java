/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.util;

/**
 * Enum stati
 */
public enum ElaborazioneTipoEnum {
	/** CARICAMENTO_FONTE_ESTERNA */
	CARICAMENTO_FONTE_ESTERNA("CARICAMENTO_FONTE_ESTERNA"),
	
	/** TRASMISSIONE_PROGRAMMA_MIT */
	TRASMISSIONE_PROGRAMMA_MIT("TRASMISSIONE_PROGRAMMA_MIT"),
	
	/** INVIO_EVASIONE */
	INVIO_EVASIONE("INVIO_EVASIONE"),

	;
	
	private final String costante;
	
	private ElaborazioneTipoEnum(String costante) {
		this.costante = costante;
	}
	
	private ElaborazioneTipoEnum() {
		this.costante = this.name();
	}
	
	/**
	 * @return the costante
	 */
	public String getCostante() {
		return costante;
	}

}
