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
 * Constants for Risorsa
 */
public class ConstantsCPassRisorsa {

	public enum TipoEnum {
		BILANCIO("BILANCIO"), 
		CAPITALE_PRIVATO("CAPITALE PRIVATO");

		private final String tipo;

		private TipoEnum(String tipo) {
			this.tipo = tipo;
		}

		public String getTipo() {
			return tipo;
		}
	}

	public enum RisorsaEnum {
		RISORSE_PRIVATI("BILANCIO", "risorsePrivati"),
		RISORSE_MUTUO("BILANCIO", "risorseMutuo"),
		RISORSE_VINCOLATE_PER_LEGGE("BILANCIO", "risorseVincolatePerLegge"),
		RISORSE_BILANCIO("BILANCIO", "risorseBilancio"),
		RISORSE_ART3("BILANCIO", "risorseArt3_"),
		RISORSE_IMMOBILI("BILANCIO", "risorseImmobili"),
		RISORSE_ALTRO("BILANCIO", "risorseAltro")
		;

		private final String tipo;
		private final String tagTrasmissione;

		private RisorsaEnum(String tipo, String tagTrasmissione) {
			this.tipo = tipo;
			this.tagTrasmissione = tagTrasmissione;
		}

		public String getTipo() {
			return tipo;
		}

		public String getTagTrasmissione() {
			return tagTrasmissione;
		}
	}

}
