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

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class NumberUtility {

	public static void main(String[] args) {
		new BigDecimal("1.125");
	}

	public static Double toDouble(BigDecimal bd) {
		if(bd==null) {
			return 0.00;
		}
		return bd.doubleValue();
	}


	@Deprecated
	public static double arrotondaDueDecimali(double d) {
		// System.out.println(d);
		final double a = Math.round(d * 100);
		// System.out.println(a);
		final double darr = a / 100;
		// System.out.println(darr);
		return darr;
	}
	/**
	 * 
	 * @param value
	 * @return BigDecimal
	 */
	public static BigDecimal arrotondaDueDec (BigDecimal value ) {
		return arrotonda ( value , 2) ;
	}
	/**
	 * 
	 * @param value
	 * @param numeroDecimali
	 * @return BigDecimal
	 */
	public static BigDecimal arrotonda (BigDecimal value , int numeroDecimali) {
		if(value==null) {
			return BigDecimal.ZERO;
		}
		final MathContext m = new MathContext(34, RoundingMode.HALF_UP);
		final BigDecimal ris = value.setScale(numeroDecimali, m.getRoundingMode());
		return ris;
	}
	/**
	 * 
	 * @param importoAnnoPrimo
	 * @return
	 */
	public static BigDecimal StringToBigDecimal(String importoAnnoPrimo) {
		BigDecimal ris = null;
		if(importoAnnoPrimo == null || importoAnnoPrimo.trim().length() == 0) {
			ris = BigDecimal.ZERO;
		}else {
			ris = new BigDecimal(importoAnnoPrimo.replace(",", "."));
		}
		return ris;
	}
	/**
	 * 
	 * @param value
	 * @return
	 */
	public static Boolean isNumber(String value) {
		try {
			Integer.parseInt(value);
			return Boolean.TRUE;
		}catch(final Exception e) {
			return Boolean.FALSE;
		}
	}

}
