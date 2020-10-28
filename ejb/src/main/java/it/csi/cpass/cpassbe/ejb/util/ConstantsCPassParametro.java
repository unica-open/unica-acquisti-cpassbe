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
		MOTIVAZIONE_NON_RIPROPOSTO_DEFAULT("MOTIVAZIONE_NON_RIPROPOSTO_DEFAULT")
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
		CTRL_CLASSE_SOGG_ERRORE("ERRORE"), 
		CTRL_CLASSE_SOGG_AVVISO("AVVISO"),
		
		MODALITA_INVIO_EVASIONE_ASINCRONA("ASINCRONA"),
		
		SERVIZIO_VERIFICA_EVASIONE_FALSE("FALSE")
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
		PROVVEDIMENTO(),
		RIGA_ORDINE("RIGA-ORDINE"),
		EVASIONE(),
		DOCUMENTO_SPESA("DOCUMENTO-SPESA"),
		NULL(null)
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
