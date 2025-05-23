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

/**
 * Tipo Ordini NSO
 */
public enum TipoOrdineNSO {

	/** Message ORD-ORD-P-0001 */
	INIZIALE ("INIZIALE", "ORDINE INIZIALE"),
	REVOCA ("REVOCA", "ORDINE DI REVOCA"),
	SOSTITUTIVO ("SOSTITUTIVO", "ORDINE SOSTITUTIVO");

	private final String codice;
	private final String descrizione;

	private TipoOrdineNSO(String codice,String descrizione) {
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
