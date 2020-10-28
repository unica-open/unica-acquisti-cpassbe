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

public class ConstantsDecodifiche {

	public enum TipoOrdineEnum {
		INTERNO("INT");

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

}
