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
public enum CpassEnum {
	/** Programma */
	PROGRAMMA("PROGRAMMA"),
	/** Start */
	START("START"),
	/** End */
	END("END"),
	/** OK */
	OK("OK"),
	/** Ko */
	KO("KO"),
	/** Errro */
	ERROR("ERROR"),
	/** Caricamento da fonte esterna */
	// CARICAMENTO_FONTE_ESTERNA("CARICAMENTO_FONTE_ESTERNA"),
	/** Errore caricamento programma csv */
	ERRORE_CARICAMENTO_PROGRAMMA_CSV("ERRORE_CARICAMENTO_PROGRAMMA_CSV"),
	/** Intervento */
	INTERVENTO("INTERVENTO"),
	/** Importi traslati */
	IMPORTI_TRASLATI("IMPORTI_TRASLATI"),
	COPIA_MANTENENDO_CUI("COPIA_MANTENENDO_CUI"),
	INTERVENTO_CUI("INTERVENTO.CUI"),
	ORDINE_TESTATA("ORDINE.TESTATA"),
	EVASIONE_TESTATA("EVASIONE.TESTATA"),
	RMS_TESTATA("RMS.TESTATA"),
	RMS_RIGA("RMS.RIGA"),
	ACQ_NON_RIPROPOSTO("ACQ_NON_RIPROPOSTO"),
	ACQ_DA_COPIA_PROGRAMMA("ACQ_DA_COPIA_PROGRAMMA"),
	UTENTE_SISTEMA_CF("AAAAAA00A11B000J"),
	UTENTE_BATCH("UTENTE_BATCH"),
	RDA_TESTATA("RDA.TESTATA"),
	RDA_RIGA("RDA.RIGA"),
	IMP_NON_TRASLATI("IMP_NON_TRASLATI"),
	ACQUISTO_GIA_AVVIATO("ACQUISTO_GIA_AVVIATO"),
	PRINCIPALE("PRINCIPALE"),
	GEN("GEN"),
	;


	private final String costante;

	private CpassEnum(String costante) {
		this.costante = costante;
	}

	private CpassEnum() {
		this.costante = this.name();
	}
	/**
	 * @return the costante
	 */
	public String getCostante() {
		return costante;
	}

}
