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
 * Constants for Elaborazione
 */
public class ConstantsCPassElaborazione {

	/**
	 * Tipo elaborazione
	 */
	public enum TipoEnum {
		/** Elaborazione programma */
		PROGRAMMA()
	}

	/**
	 * Stato elaborazione
	 */
	public enum StatoEnum {
		/** Da elaborare */
		DA_ELABORARE("DA ELABORARE"),
		/** In elaborazione */
		IN_ELABORAZIONE("IN ELABORAZIONE"),
		/** Elaborato */
		ELABORATO("ELABORATO"),
		/** Errore elaborazione */
		ERRORE_ELABORAZIONE("ERRORE ELABORAZIONE");

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

}
