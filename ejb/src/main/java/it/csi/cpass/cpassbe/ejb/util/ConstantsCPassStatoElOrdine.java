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

public class ConstantsCPassStatoElOrdine {

	public enum StatoEnum {
		// ordine
		DA_EVADERE("DAE"),
		EVASO_PARZIALMENTE("EVP"),
		EVASO_TOTALMENTE("EVT"),
		CHIUSO("EVX"),

		// destinatario ordine
		DEST_ORDINE_DA_EVADERE("DAE"),
		DEST_ORDINE_ANNULLATO_DA_EVADERE("ADE"), // prima AED
		DEST_ORDINE_EVASO_PARZIALMENTE("EVP"),
		DEST_ORDINE_EVASO_TOTALMENTE("EVT"),

		// riga ordine
		RIGA_ORDINE_DA_EVADERE("DAE"),
		RIGA_ORDINE_ANNULLATA_DA_EVADERE("ADE"), // prima AER
		RIGA_ORDINE_EVASA_PARZIALMENTE("EVP"),
		RIGA_ORDINE_EVASA_TOTALMENTE("EVT"),
		RIGA_ORDINE_CHIUSA("EVX"),
		RIGA_ORDINE_CHIUSA_DA_EVADERE("CDE"),
		RIGA_ORDINE_CHIUSA_EVASA_PARZIALMENTE("CEP"),
		
		// destinatario evasione
		DESTINATARIO_EVASIONE_DA_FATTURARE("DAF"),
		DESTINATARIO_EVASIONE_ANNULLATO("ANN"),
		DESTINATARIO_EVASIONE_TOTALMENTE_FATTURATO("ETF"),

		// riga evasione
		RIGA_EVASIONE_ANNULLATA("ANN"),
		RIGA_EVASIONE_DA_FATTURARE("DAF"),
		RIGA_EVASIONE_TOTALMENTE_FATTURATA("ETF")
		;

		private final String costante;

		private StatoEnum(String costante) {
			this.costante = costante;
		}

		private StatoEnum() {
			this.costante = this.name();
		}

		/**
		 * @return the costante
		 */
		public String getCostante() {
			return costante;
		}
	}

	public enum TipoEnum {
		// ordine
		DEST_ORDINE(),
		RIGA_ORDINE(),

		// evasione
		DESTINATARIO_EVASIONE(),
		RIGA_EVASIONE();

		private final String costante;

		private TipoEnum(String costante) {
			this.costante = costante;
		}

		private TipoEnum() {
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
