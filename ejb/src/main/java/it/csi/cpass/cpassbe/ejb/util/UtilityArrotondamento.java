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

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class UtilityArrotondamento {

	public static void main(String[] args) {
		// • Se la terza cifra decimale è minore di 5, si arrotonda per difetto; esempio 25,84422 = 25,84
		// • Se la terza cifra decimale è maggiore o uguale a 5, si arrotonda per eccesso; esempio 51,645 = 51,65

		double d = 25.84422;
		arrotondaDueDecimali(d);

		double dd = 51.645;
		arrotondaDueDecimali(dd);
	}

	public static double arrotondaDueDecimali(double d) {
		// System.out.println(d);

		double a = Math.round(d * 100);
		// System.out.println(a);

		double darr = a / 100;
		// System.out.println(darr);
		
		return darr;
	}
	
	public static BigDecimal arrotondaDueDecimali(BigDecimal b) {
		if (b == null) {
			return null;
		}
		return new BigDecimal(arrotondaDueDecimali(b.doubleValue()));
	}

	public static BigDecimal arrotonda (BigDecimal value ) {   
        return arrotonda ( value , 2) ;
    } 

	public static BigDecimal arrotonda (BigDecimal value , int numeroDecimali) {   
        MathContext m = new MathContext(34, RoundingMode.HALF_UP);
        BigDecimal ris = value.setScale(numeroDecimali, m.getRoundingMode());
        return ris;
    } 

}
