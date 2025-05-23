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

public class ConstantsCPassStato {

	public enum TipoStatoEnum {
		PROGRAMMA(),
		INTERVENTO(),
		// RMS
		RMS(),
		RIGA_RMS(),
		// RDA
		RDA(),
		RIGA_RDA(),
		// ordine
		ORDINE(),
		DEST_ORDINE(),
		RIGA_ORDINE(),
		ORDINE_MEPA,
		// evasione
		EVASIONE(),
		DESTINATARIO_EVASIONE(),
		RIGA_EVASIONE(),
		DOCUMENTO_DI_TRASPORTO(),
		;

		private final String costante;

		private TipoStatoEnum(String costante) {
			this.costante = costante;
		}

		private TipoStatoEnum() {
			this.costante = this.name();
		}

		/**
		 * @return the costante
		 */
		public String getCostante() {
			return costante;
		}
	}


	public enum StatoInterventiEnum {
		BOZZA("BOZZA"),
		VALIDATO("VALIDATO"),
		VISTATO_VALIDATO("VISTATO E VALIDATO"),
		VISTO("VISTO"),
		CANCELLATO("CANCELLATO"),
		RIFIUTATO("BOZZA DA RIFIUTO"),
		BOZZA_DA_RIFIUTO("BOZZA DA RIFIUTO"),
		BOZZA_DA_RIFIUTO_RAGIONERIA("BOZZA DA RIFIUTO RAGIONERIA"),
		VISTO_DA_RAGIONERIA("VISTO DA RAGIONERIA"),
		RIPORTATO_IN_BOZZA("RIPORTATO IN BOZZA"),
		AVVIATO("AVVIATO");

		private final String costante;

		private StatoInterventiEnum(String costante) {
			this.costante = costante;
		}

		private StatoInterventiEnum() {
			this.costante = this.name();
		}

		/**
		 * @return the costante
		 */
		public String getCostante() {
			return costante;
		}
	}

	public enum StatoProgrammaEnum {
		BOZZA("BOZZA"),
		CONFERMATO("CONFERMATO"),
		CANCELLATO("CANCELLATO"),
		TRASMESSO("TRASMESSO"),
		;

		private final String costante;

		private StatoProgrammaEnum(String costante) {
			this.costante = costante;
		}

		private StatoProgrammaEnum() {
			this.costante = this.name();
		}

		/**
		 * @return the costante
		 */
		public String getCostante() {
			return costante;
		}
	}

	public enum StatoDocumentoTrasportoEnum {
		DA_ABBINARE("DA_ABBINARE"),
		ABBINATO("ABBINATO"),
		SCARTATO("SCARTATO")
		;

		private final String costante;

		private StatoDocumentoTrasportoEnum(String costante) {
			this.costante = costante;
		}

		private StatoDocumentoTrasportoEnum() {
			this.costante = this.name();
		}

		/**
		 * @return the costante
		 */
		public String getCostante() {
			return costante;
		}
	}

	public enum StatoOrdineEnum {
		BOZZA("BOZZA"),
		CONFERMATO("CONFERMATO"),
		ANNULLATO("ANNULLATO"),
		AUTORIZZATO("AUTORIZZATO"),
		IN_FIRMA("IN_FIRMA")
		;

		private final String costante;

		private StatoOrdineEnum(String costante) {
			this.costante = costante;
		}

		private StatoOrdineEnum() {
			this.costante = this.name();
		}

		public String getCostante() {
			return costante;
		}
	}


	public enum StatoEnum {
		BOZZA("BOZZA")
		;

		private final String costante;

		private StatoEnum(String costante) {
			this.costante = costante;
		}

		private StatoEnum() {
			this.costante = this.name();
		}

		public String getCostante() {
			return costante;
		}
	}

	public enum StatoRmsEnum {
		BOZZA(),
		CONFERMATA(),
		AUTORIZZATA(),
		ANNULLATA(),
		RIFIUTATA(),
		EVASA_MANUALMENTE();

		private final String costante;

		private StatoRmsEnum(String costante) {
			this.costante = costante;
		}

		private StatoRmsEnum() {
			this.costante = this.name();
		}

		/**
		 * @return the costante
		 */
		public String getCostante() {
			return costante;
		}
	}

	public enum StatoRigaRmsEnum {
		NEW(),
		DAE(),
		EVT(),
		EVP(),
		WIP(),
		RIF(),
		CEP(),
		CDE(),
		ADE(),
		EVM(),
		ALL(),
		IAG();

		private final String costante;

		private StatoRigaRmsEnum(String costante) {
			this.costante = costante;
		}

		private StatoRigaRmsEnum() {
			this.costante = this.name();
		}

		/**
		 * @return the costante
		 */
		public String getCostante() {
			return costante;
		}
	}

	public enum StatoRdaEnum {
		BOZZA(),
		AUTORIZZATA(),
		ANNULLATA();

		private final String costante;

		private StatoRdaEnum(String costante) {
			this.costante = costante;
		}

		private StatoRdaEnum() {
			this.costante = this.name();
		}

		/**
		 * @return the costante
		 */
		public String getCostante() {
			return costante;
		}
	}

	public enum StatoRigaRdaEnum {
		DAE(),
		ADE(),
		EVT();
		private final String costante;

		private StatoRigaRdaEnum(String costante) {
			this.costante = costante;
		}

		private StatoRigaRdaEnum() {
			this.costante = this.name();
		}

		/**
		 * @return the costante
		 */
		public String getCostante() {
			return costante;
		}
	}

	public enum StatoOrdineMepaEnum {
		DA_CARICARE("DA_CARICARE"),
		CARICATO("CARICATO");
		private final String costante;

		private StatoOrdineMepaEnum(String costante) {
			this.costante = costante;
		}

		private StatoOrdineMepaEnum() {
			this.costante = this.name();
		}

		/**
		 * @return the costante
		 */
		public String getCostante() {
			return costante;
		}
	}

	public enum StatoOrdineEvasioneEnum {
		// ordine
		//DA_EVADERE("DAE"),
		//EVASO_PARZIALMENTE("EVP"),
		//EVASO_TOTALMENTE("EVT"),
		//CHIUSO("EVX"),

		// destinatario ordine
		DEST_ORDINE_DA_EVADERE("DAE"),
		DEST_ORDINE_ANNULLATO_DA_EVADERE("ADE"), // prima AED
		DEST_ORDINE_EVASO_PARZIALMENTE("EVP"),
		DEST_ORDINE_EVASO_TOTALMENTE("EVT"),
		DEST_ORDINE_EVASO_PARZIALMENTE_CON_SCONTO("EPS"),
		DEST_ORDINE_CHIUSO_DA_EVADERE("CDE"),
		DEST_ORDINE_CHIUSO_EVASO_PARZIALMENTE("CEP"),

		// riga ordine
		RIGA_ORDINE_DA_EVADERE("DAE"),
		RIGA_ORDINE_ANNULLATA_DA_EVADERE("ADE"), // prima AER
		RIGA_ORDINE_EVASA_PARZIALMENTE("EVP"),
		RIGA_ORDINE_EVASA_PARZIALMENTE_CON_SCONTO("EPS"),
		RIGA_ORDINE_EVASA_TOTALMENTE("EVT"),
		RIGA_ORDINE_CHIUSA("EVX"),
		RIGA_ORDINE_CHIUSA_DA_EVADERE("CDE"),
		RIGA_ORDINE_CHIUSA_EVASA_PARZIALMENTE("CEP"),
		RIGA_ORDINE_EVASA_CON_SCONTO("EPS"),

		// destinatario evasione
		DESTINATARIO_EVASIONE_DA_FATTURARE("DAF"),
		DESTINATARIO_EVASIONE_ANNULLATO("ANN"),
		DESTINATARIO_EVASIONE_TOTALMENTE_FATTURATO("ETF"),
		DESTINATARIO_EVASIONE_PARZIALMENTE_FATTURATO("EPF"),

		// riga evasione
		RIGA_EVASIONE_ANNULLATA("ANN"),
		RIGA_EVASIONE_DA_FATTURARE("DAF"),
		RIGA_EVASIONE_TOTALMENTE_FATTURATA("ETF"),
		RIGA_EVASIONE_PARZIALMENTE_FATTURATA("EPF"),

		;

		private final String costante;

		private StatoOrdineEvasioneEnum(String costante) {
			this.costante = costante;
		}

		private StatoOrdineEvasioneEnum() {
			this.costante = this.name();
		}

		/**
		 * @return the costante
		 */
		public String getCostante() {
			return costante;
		}
	}



	public enum StatoEvasioneEnum {
		BOZZA("BOZZA"),
		AUTORIZZATA("AUTORIZZATA"),
		ANNULLATA("ANNULLATA"),
		INVIATA("INVIATA"),
		CONFERMATA("CONFERMATA"),
		IN_CONTABILITA("IN_CONTABILITA")
		;

		private final String costante;

		private StatoEvasioneEnum(String costante) {
			this.costante = costante;
		}

		private StatoEvasioneEnum() {
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
