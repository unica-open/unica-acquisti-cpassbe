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
 * Constants for Elaborazione
 */
public class ConstantsCPassElaborazione {

	/**
	 * Tipo elaborazione
	 */
	public enum TipoEnum {
		/** Elaborazione programma */
		PROGRAMMA("PROGRAMMA","PROGRAMMA"),
		CARICAMENTO_FONTE_ESTERNA("CARICAMENTO_FONTE_ESTERNA","CARICAMENTO_FONTE_ESTERNA"),
		TRASMISSIONE_PROGRAMMA_MIT("TRASMISSIONE_PROGRAMMA_MIT","TRASMISSIONE_PROGRAMMA_MIT"),
		INVIO_EVASIONE("INVIO_EVASIONE","INVIO_EVASIONE"),
		RICEZIONE_DDT("RICEZIONE_DDT","RICEZIONE_DDT"),
		SMISTAMENTO_RMS("SMISTAMENTO_RMS","SMISTAMENTO_RMS"),
		RICEZIONE_NOTIFICHE("RICEZIONE_NOTIFICHE","RICEZIONE NOTIFICHE"),
		STORICO_FILE_DDT("STORICO_FILE_DDT","STORICO_FILE_DDT"),
		STORICO_FILE_NSO("STORICO_FILE_NSO","STORICO_FILE_NSO"),
		AGG_STRUTTURA("AGG_STRUTTURA","AGG_STRUTTURA"),
		CHIUSURA_FINE_ANNO("CHIUSURA_FINE_ANNO", "CHIUSURA_FINE_ANNO");

		private final String codice;
		private final String descrizione;

		private TipoEnum(String codice,String descrizione) {
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

	/**
	 * Stato elaborazione
	 */
	public enum StatoEnum {
		DA_ELABORARE("DA ELABORARE"),
		IN_ELABORAZIONE("IN ELABORAZIONE"),
		ELABORATO("ELABORATO"),
		ERRORE_ELABORAZIONE("ERRORE ELABORAZIONE"),
		CONCLUSO("CONCLUSO"),
		CONCLUSO_SENZA_RIGHE_SMISTATE("CONCLUSO SENZA RIGHE"),
		CONCLUSO_CON_ERRORE("CONCLUSO_CON_ERRORE"),
		AVVIATO("AVVIATO"),
		CARICA_IMPEGNO_TMP("CARICA_IMPEGNO_TMP"),
		CARICA_SUBIMPEGNO_TMP("CARICA_SUBIMPEGNO_TMP"),
		AGG_IMPEGNO_TMP("AGG_IMPEGNO_TMP"),
		CONTROLLO_BATCH_IMPEGNI("CONTROLLO_BATCH_IMPEGNI"),
		AGG_SUBIMPEGNO_TMP("AGG_SUBIMPEGNO_TMP"),
		/** AGGIORNAMENTO_ODS */
		AGGIORNAMENTO_ODS("AGGIORNAMENTO_ODS"),
		;

		private final String statoDB;

		private StatoEnum(String costante) {
			this.statoDB = costante;
		}

		private StatoEnum() {
			this.statoDB = this.name();
		}

		/**
		 * @return the stato
		 */
		public String getStatoDB() {
			return statoDB;
		}
	}

	public enum EsitoEnum {
		SUCCESSO("SUCCESSO","SUCCESSO"),
		FALLIMENTO("FALLIMENTO","FALLIMENTO");

		private final String codice;
		private final String descrizione;

		private EsitoEnum(String codice,String descrizione) {
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

}
