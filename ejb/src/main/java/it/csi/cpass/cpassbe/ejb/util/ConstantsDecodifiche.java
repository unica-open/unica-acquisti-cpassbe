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

public class ConstantsDecodifiche {

	public enum TipoOrdineEnum {
		MEPA("MEP"),
		INTERNO("INT"),
		SEMPLICE("SEM");

		private final String codice;

		private TipoOrdineEnum(String codice) {
			this.codice = codice;
		}

		private TipoOrdineEnum() {
			this.codice = this.name();
		}

		/**
		 * @return the costante
		 */
		public String getCodice() {
			return codice;
		}
	}

	public enum TipoEvasioneEnum {
		MANUALE("MAN");

		private final String codice;

		private TipoEvasioneEnum(String codice) {
			this.codice = codice;
		}

		private TipoEvasioneEnum() {
			this.codice = this.name();
		}

		/**
		 * @return the costante
		 */
		public String getCodice() {
			return codice;
		}
	}


	public enum SettoreInterventiEnum {
		FORNITURE("F"),
		SERVIZI("S");

		private final String codice;

		private SettoreInterventiEnum(String codice) {
			this.codice = codice;
		}

		private SettoreInterventiEnum() {
			this.codice = this.name();
		}

		/**
		 * @return the costante
		 */
		public String getCodice() {
			return codice;
		}
	}
	/*
		public enum TipoOrdineNSOEnum {
			INIZIALE("INIZIALE","ORDINE INIZIALE"),
			REVOCA("REVOCA","ORDINE DI REVOCA");

			private final String codice;
			private final String descrizione;

			private TipoOrdineNSOEnum(String codice,String descrizione) {
				this.codice = codice;
				this.descrizione = descrizione;
			}

			public String getCodice() {
				return codice;
			}

			public String getDescrizione() {
				return descrizione;
			}

		}*/

	public enum NotificaTipoEntitaEnum {
		ORDINE("ORDINE"),
		RMS("RMS"),
		PBA("PBA");

		private final String codice;

		private NotificaTipoEntitaEnum(String codice) {
			this.codice = codice;
		}

		private NotificaTipoEntitaEnum() {
			this.codice = this.name();
		}

		/**
		 * @return the costante
		 */
		public String getCodice() {
			return codice;
		}
	}
	public enum NotificaFonteEnum {
		NSO("NSO"),
		RMS("RMS"),
		BATCH("BATCH"),
		PBA("PBA")
		;

		private final String codice;

		private NotificaFonteEnum(String codice) {
			this.codice = codice;
		}

		private NotificaFonteEnum() {
			this.codice = this.name();
		}

		/**
		 * @return the costante
		 */
		public String getCodice() {
			return codice;
		}
	}
	public enum NotificaEnum {
		N0001("N0001"),
		N0002("N0002"),
		N0003("N0003"),
		N0004("N0004"),
		N0005("N0005"),
		N0006("N0006"),
		N0007("N0007"),
		;

		private final String codice;

		private NotificaEnum(String codice) {
			this.codice = codice;
		}

		private NotificaEnum() {
			this.codice = this.name();
		}

		/**
		 * @return the costante
		 */
		public String getCodice() {
			return codice;
		}
	}

	public enum ModuloEnum {
		BO("BO"),
		MAG("MAG"),
		ORD("ORD"),
		PBA("PBA"),
		RMS("RMS");

		private final String codice;

		private ModuloEnum(String codice) {
			this.codice = codice;
		}

		private ModuloEnum() {
			this.codice = this.name();
		}

		/**
		 * @return the costante
		 */
		public String getCodice() {
			return codice;
		}
	}
}
