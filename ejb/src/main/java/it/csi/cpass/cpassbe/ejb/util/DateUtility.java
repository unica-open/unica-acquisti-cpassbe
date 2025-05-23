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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class DateUtility {

	public static void main(String[] args) {}
	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDateWithoutTime (Date date) {
		if (date == null) {
			return null;
		}
		final Instant inst = date.toInstant();
		final LocalDate localDate = inst.atZone(ZoneId.systemDefault()).toLocalDate();
		final Instant dayInst = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
		return Date.from(dayInst);
	}
	/**
	 * 
	 * @param d
	 * @param days
	 * @return
	 */
	public static Date addDays(Date d, int days){
		final Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.DATE, days);
		d.setTime( c.getTime().getTime() );
		return d;
	}

	/**
	 * @param stringDate
	 * @param date formatted
	 * @return
	 */
	public static Date stringToDate(String stringDate) {
		Date ris = null;
		if (stringDate != null) {
			final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				ris = format.parse(stringDate);
			} catch (final ParseException e) {
				//log.error("stringToDate", "ParseException ",e);
				ris = null;
			}
		}
		return ris;
	}

	/**
	 * @param stringDate
	 * @param date formatted
	 * @return
	 */
	public static Date stringToDate(String stringDate,String formato) {
		Date ris = null;
		if (stringDate != null) {
			final SimpleDateFormat format = new SimpleDateFormat(formato);
			try {
				ris = format.parse(stringDate);
			} catch (final ParseException e) {
				//log.error("stringToDate", "ParseException ",e);
				ris = null;
			}
		}
		return ris;
	}

	/**
	 * @param stringDate
	 * @param String formatted
	 * @return
	 */
	public static String dateToString(Date stringDate) {
		String ris = null;
		if (stringDate != null) {
			final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			try {
				ris = format.format(stringDate);
			} catch (final Exception e) {
				ris = null;
			}
		}
		return ris;
	}
	/**
	 * 
	 * @return
	 */
	public static Integer getAnnoCorrente() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

}
