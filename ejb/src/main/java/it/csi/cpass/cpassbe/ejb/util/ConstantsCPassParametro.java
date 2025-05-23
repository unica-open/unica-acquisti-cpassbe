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

public class ConstantsCPassParametro {

	public enum ChiaveEnum {
		DATA_ORDINI_FUTURI(),
		CTRL_CLASSE_SOGG(),
		ORD_TOLLERANZA_IVA(),
		ASSOC_IMPEGNI_ORD(),
		SISTEMA_DOCUMENTALE(),
		ANNULLO_ORDINE_PROTOCOLLATO(),
		VERIFICA_STORICO_FORNITORI(),
		// EVASIONE_TOLLERANZA(),
		TOLLERANZA_EVASIONE(),
		STATO_FATTURA_RIPARTIBILE("STATO_FATTURA_RIPARTIBILE"), // anche presente in SiacConfigurationParams
		MODALITA_INVIO_EVASIONE("MODALITA_INVIO_EVASIONE"),
		SERVIZIO_VERIFICA_EVASIONE("SERVIZIO_VERIFICA_EVASIONE"),
		MOTIVAZIONE_NON_RIPROPOSTO_DEFAULT("MOTIVAZIONE_NON_RIPROPOSTO_DEFAULT"),
		PATH_IMPEGNI_CSV("PATH_IMPEGNI_CSV"),
		PATH_SUBIMPEGNI_CSV("PATH_SUBIMPEGNI_CSV"),
		POLL_TIME_NOTIFICATIONS("POLL_TIME_NOTIFICATIONS"),
		VISTO_RAGIONERIA("VISTO_RAGIONERIA"),
		VERIFICA_STRUTTURA_PROVVEDIMENTO("VERIFICA_STRUTTURA_PROVVEDIMENTO"),
		GG_FINE_NOTIFICA("GG_FINE_NOTIFICA"),
		UTENTE_BATCH("UTENTE_BATCH"),
		STORICO_DDT("STORICO_DDT"),
		STORICO_NSO("STORICO_NSO"),
		GIORNI_STORICO_NSO("GIORNI_STORICO_NSO"),
		GIORNI_STORICO_DDT("GIORNI_STORICO_DDT"),
		GESTIONE_ACQUISTO_VERS_DEFINITIVA("GESTIONE_ACQUISTO_VERS_DEFINITIVA"),
		SOGLIA_DI_NON_INVIO_MIT(),
		SOGLIA_IVA_OBBLIGATORIA("SOGLIA_IVA_OBBLIGATORIA"),
		ALGORITMO_CONFRONTO_PER_DIREZIONE("ALGORITMO_CONFRONTO_PER_DIREZIONE"),
		LOGIN_INVIO_CONTABILITA("LOGIN_INVIO_CONTABILITA"),
		DURATA_PROGRAMMA("DURATA_PROGRAMMA"),
		report_endpoint("report.endpoint"),
		report_multi_endpoint("report.multi.endpoint"),
		AVVIO_CON_ANNO_MAGGIORE("AVVIO_CON_ANNO_MAGGIORE")

		;

		private final String costante;

		private ChiaveEnum(String costante) {
			this.costante = costante;
		}

		private ChiaveEnum() {
			this.costante = this.name();
		}

		/**
		 * @return the costante
		 */
		public String getCostante() {
			return costante;
		}
	}

	public enum ValoreEnum {
		ERRORE("ERRORE"),
		CTRL_CLASSE_SOGG_ERRORE("ERRORE"),
		CTRL_CLASSE_SOGG_AVVISO("AVVISO"),
		MODALITA_INVIO_EVASIONE_ASINCRONA("ASINCRONA"),
		SERVIZIO_VERIFICA_EVASIONE_FALSE("FALSE"),
		VERIFICA_STRUTTURA_PROVVEDIMENTO_A("A"),
		VERIFICA_STRUTTURA_PROVVEDIMENTO_E("E"),
		ERRORE_SMISTAMENTO_RMS("ERRORE_SMISTAMENTO_RMS"),
		ERRORE_OPERAZIONI_CHIUSURA("ERRORE_OPERAZIONI_CHIUSURA"),
		ALL("ALL"),
		SOGLIA_IVA_OBBLIGATORIA("1000000"),
		AGGIORNAMENTO_ODS("AGGIORNAMENTO_ODS"),
		;

		private final String costante;

		private ValoreEnum(String costante) {
			this.costante = costante;
		}

		private ValoreEnum() {
			this.costante = this.name();
		}

		/**
		 * @return the costante
		 */
		public String getCostante() {
			return costante;
		}
	}

	public enum RiferimentoEnum {
		FORNITORE(),
		IMPEGNO(),
		PROVVEDIMENTO("PROVVEDIMENTO"),
		RIGA_ORDINE("RIGA-ORDINE"),
		EVASIONE(),
		DOCUMENTO_SPESA("DOCUMENTO-SPESA"),
		IMPEGNOEXT("IMPEGNOEXT"),
		NOTIFICA("NOTIFICA"),
		BATCH("BATCH"),
		NULL(null),
		STORICO_DDT("STORICO_DDT"),
		STORICO_NSO("STORICO_NSO"),
		PBA("PBA"),
		ACTA("ACTA"),
		;

		private final String costante;

		private RiferimentoEnum(String costante) {
			this.costante = costante;
		}

		private RiferimentoEnum() {
			this.costante = this.name();
		}

		/**
		 * @return the costante
		 */
		public String getCostante() {
			return costante;
		}
	}

}
