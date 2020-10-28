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

public class ConstantsCPassOrdStatoNso {

	public enum StatoEnum {
		TRASMESSO("TRA"),
		IN_ATTESA_DI_TRASMISSIONE("ATT"),
		OK(),
		KO()
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
		ORDINE()
		;

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
