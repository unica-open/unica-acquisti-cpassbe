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
 * Enum for Cpass
 */
public enum CpassRuoloEnum {
	REFP("REFP","REFERENTE DI PROGRAMMA"),
	RUP("RUP","RESPONSABILE UNICO PROCEDIMENTO"),
	OPPROC("OPPROC","OPERATORE PROCEDIMENTO"),
	OPPROG("OPPROG","OPERATORE PROGRAMMA"),
	OSS("OSS","OSSERVATORE"),
	ADMIN("ADMIN","AMMINISTRATORE")
	;
	
	
	private final String codice;
	private final String descrizione;
	
	private CpassRuoloEnum(String codice,String descrizione) {
		this.codice = codice;
		this.descrizione = descrizione;
	}
	
	/*
	private CpassRuoloEnum() {
		this.codice = this.name();
		this.descrizione = descrizione;
	}*/
    
	
	public String getCodice() {
		return codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

}
