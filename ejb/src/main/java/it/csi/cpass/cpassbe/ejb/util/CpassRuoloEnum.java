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
package it.csi.cpass.cpassbe.ejb.util;

/**
 * Enum for Cpass
 */
public enum CpassRuoloEnum {
	REFP("REFP","REFERENTE DI PROGRAMMA"),
	RUP("RUP","RESPONSABILE UNICO PROCEDIMENTO (ACQUISTI)"),
	OPPROC("OPPROC","OPERATORE PROCEDIMENTO"),
	OPPROG("OPPROG","OPERATORE PROGRAMMA"),
	OSS("OSS","OSSERVATORE"),
	ADMIN("ADMIN","AMMINISTRATORE"),
	ADMIN_ENTE("ADMIN_ENTE","AMMINISTRATORE ENTE"),
	DELEGATO_REFP("DELEGATO_REFP","DELEGATO REFERENTE DI PROGRAMMA"),
	DELEGATO_RUP("DELEGATO_RUP","DELEGATO RUP"),
	SMISTATORE_RMS("SMISTATORE_RMS","UTENTE ABILITATO ALLO SMISTAMENTO MANUALE DELLE RMS"),
	COMPILATORE("COMPILATORE","COMPILATORE"),
	OSSERVATORE_RMS("OSSERVATORE_RMS","OSSERVATORE_RMS"),
	;


	private final String codice;
	private final String descrizione;

	private CpassRuoloEnum(String codice,String descrizione) {
		this.codice = codice;
		this.descrizione = descrizione;
	}

	public String getCodice() {
		return codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

}
