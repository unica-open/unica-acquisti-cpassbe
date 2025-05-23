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

public class ConstantsCpassOrdTipoOrdine {

	public enum TipoOrdine {
		SEMPLICE("SEM"), INTERNO("INT"), MEPA("MEP");

		private final String costante;

		private TipoOrdine(String costante) {
			this.costante = costante;
		}

		private TipoOrdine() {
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
