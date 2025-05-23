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
package it.csi.cpass.cpassbe.ejb.validatori;


import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * Validazioni varie
 */
public class Validazioni {
	// private static LogUtil log = new LogUtil(Validazioni.class);
	/** Pattern for Intervento.cui */
	private static final Pattern[] CONTROLLO_SICUREZZA_CUI = new Pattern[] {
			Pattern.compile("^[a-zA-Z0-9]{21}$"),
	};

	/**
	 * Checks if the codice fiscale is valid
	 * @param codiceFiscale the codiceFiscale
	 * @return whether the data is valid
	 */
	public boolean isValidCodiceFiscale(String codiceFiscale) {
		return isValidMask(
				codiceFiscale,
				"^[A-Z]{6}[0-9LMNPQRSTUV]{2}[A-Z][0-9LMNPQRSTUV]{2}[A-Z][0-9LMNPQRSTUV]{3}[A-Z]$",
				Pattern.CASE_INSENSITIVE)
				&& isValidControlCharCodiceFiscale(codiceFiscale);
	}

	/**
	 * Checks the control character
	 * @param codiceFiscale the codice fiscale
	 * @return whether the control character is valid
	 */
	private boolean isValidControlCharCodiceFiscale(String codiceFiscale) {
		return codiceFiscale.charAt(15) == calcControlCharCodiceFiscale(StringUtils.substring(codiceFiscale, 0, 15));
	}

	/**
	 * Computes the control character
	 * @param codiceFiscale the codice fiscale
	 * @return the control character
	 */
	public char calcControlCharCodiceFiscale(String codiceFiscale) {
		int sum = 0;

		for (int i = 0; i < codiceFiscale.length(); i++) {
			final char c = codiceFiscale.charAt(i);

			final int x = (Character.isDigit(c) ? Character.getNumericValue(c)
					: new String(new char[] { c }).getBytes()[0] - 65);

			sum += ((i + 1) % 2 == 0 ? x : new int[] { 1, 0, 5, 7, 9, 13, 15,
					17, 19, 21, 2, 4, 18, 20, 11, 3, 6, 8, 12, 14, 16, 10, 22,
					25, 24, 23 }[x]);
		}

		return (char) ((sum % 26) + 65);
	}

	/**
	 * Checks whether the mask is valid for the input
	 * @param value the value
	 * @param mask the mask
	 * @return whether the mask is valid
	 */
	public boolean isValidMask(String value, String mask) {
		return isValidMask(value, mask, 0);
	}

	/**
	 * Checks whether the mask is valid for the input
	 * @param value the value
	 * @param mask the mask
	 * @param flags the flags
	 * @return whether the mask is valid
	 */
	public boolean isValidMask(String value, String mask, int flags) {
		if (StringUtils.isNotEmpty(value)) {
			final Pattern pattern = Pattern.compile(mask, flags);
			final Matcher matcher = pattern.matcher(value);
			return matcher.find();
		}
		return true;
	}

	/**
	 * Check for CUI
	 * @param cui the cui
	 * @return whether the CUI is valid
	 */
	public boolean controlloCui(String cui) {
		for (final Pattern pattern : CONTROLLO_SICUREZZA_CUI) {
			if (!pattern.matcher(cui).matches()) {
				return false;
			}
		}
		return true;
	}









	public String isValorizzato(String campoNome ,String valore) {
		String ris = "";
		if(valore == null || valore.trim().equals("")) {
			ris  = campoNome +": valore nullo - ";
		}
		return ris;
	}

	public String isNumericoOVuoto(String campoNome ,String valore) {
		String ris = "";
		if (valore == null || valore.trim().equals("") || valore.trim().equals("0") || valore.toLowerCase().trim().equals("null")) {
			ris = "";
		}else {
			try {
				Integer.parseInt(valore);
				ris = "";
			} catch (final Exception e) {
				ris  = campoNome +": valore non numerico - ";
			}
		}
		return ris;
	}


	public String isNumerico(String campoNome ,String valore) {
		String ris = "";
		try {
			Integer.parseInt(valore);
			ris = "";
		} catch (final Exception e) {
			ris  = campoNome +": valore non numerico - ";
		}
		return ris;
	}

	public String isAnnoOVuoto(String campoNome ,String valore) {
		String ris = "";
		if (valore == null || valore.toLowerCase().trim().equals("null") || valore.trim().equals("")|| valore.trim().equals("0")) {
			ris = "";
		}else {
			try {
				final int val = Integer.parseInt(valore);
				if(val>2100 || val <1900) {
					ris  = campoNome +": valore anno improbabile-> "+ valore+" - ";
				}else {
					ris = "";
				}
			} catch (final Exception e) {
				ris  = campoNome +": valore anno non numerico - ";
			}
		}
		return ris;
	}

	public String isAnno(String campoNome ,String valore) {
		String ris = "";

		try {
			final int val = Integer.parseInt(valore);
			if(val>2100 || val <1900) {
				ris  = campoNome +": valore anno improbabile-> "+ valore+" - ";
			}else {
				ris = "";
			}
		} catch (final Exception e) {
			ris  = campoNome +": valore anno non numerico - ";
		}
		return ris;
	}


	public String isBigDecimal(String campoNome ,String valore) {
		String ris = "";
		try {
			new BigDecimal(valore.replace(",", "."));
			ris = "";
		}catch(final Exception e) {
			ris  = campoNome +": importo non valido - ";
		}
		return ris;
	}

	public String isBigDecimalONull(String campoNome ,String valore) {
		String ris = "";
		if (valore == null || valore.toLowerCase().trim().equals("null") || valore.trim().equals("")|| valore.trim().equals("0")) {
			ris = "";
		}else {
			try {
				new BigDecimal(valore.replace(",", "."));
				ris = "";
			}catch(final Exception e) {
				ris  = campoNome +": importo non valido - ";
			}
		}
		return ris;
	}


}
