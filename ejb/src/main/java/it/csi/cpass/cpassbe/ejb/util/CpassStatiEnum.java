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

/*
ANNULLATO --> CANCELLATO    INTERVENTO
ANNULLATO --> CANCELLATO    PROGRAMMA
APPROVATO --> VALIDATO      INTERVENTO
APPROVATO --> CONFERMATO    PROGRAMMA
*/

/**
 * Enum stati
 */
public enum CpassStatiEnum {
	INT_VISTO("VISTO"), 
	INT_VALIDATO("VALIDATO"),
	INT_CANCELLATO("CANCELLATO"),
	INT_RIFIUTATO("BOZZA DA RIFIUTO"),
	INT_BOZZA("BOZZA"),
	PRO_CANCELLATO("CANCELLATO"),
	PRO_BOZZA("BOZZA"),
	PRO_CONFERMATO("CONFERMATO")
	;
	
	private final String costante;
	
	private CpassStatiEnum(String costante) {
		this.costante = costante;
	}
	
	private CpassStatiEnum() {
		this.costante = this.name();
	}
	
	/**
	 * @return the costante
	 */
	public String getCostante() {
		return costante;
	}

}
