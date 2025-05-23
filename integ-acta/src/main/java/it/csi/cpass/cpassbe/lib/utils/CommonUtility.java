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
package it.csi.cpass.cpassbe.lib.utils;

public class CommonUtility {

	public static int getTotalPage(Integer totaleRisultati, Integer numElementePerPagina) {
		int numPagine = totaleRisultati / numElementePerPagina;
		if (totaleRisultati % numElementePerPagina != 0) {
			numPagine++;
		}
		return numPagine;
	}
	

	
}
