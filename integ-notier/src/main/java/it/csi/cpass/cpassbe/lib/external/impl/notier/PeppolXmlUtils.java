/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - NotiER
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.external.impl.notier;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.IDType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.NameType;

public class PeppolXmlUtils {

	public static IDType getIDType(String value) {
		IDType idType = new IDType();
		idType.setValue(value);
		return idType;
	}

	public static NameType getNameType(String value) {
		NameType nameType = new NameType();
		nameType.setValue(value);
		return nameType;
	}

	public static XMLGregorianCalendar getXMLGregorianCalendar(Date date) throws DatatypeConfigurationException {
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setTime(date);

		final DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
//		XMLGregorianCalendar xmlGregorianCalendar = datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
		XMLGregorianCalendar xmlGregorianCalendar = datatypeFactory.newXMLGregorianCalendarDate(gregorianCalendar.get(Calendar.YEAR),gregorianCalendar.get(Calendar.MONTH)+1, gregorianCalendar.get(Calendar.DAY_OF_MONTH), DatatypeConstants.FIELD_UNDEFINED);
		return xmlGregorianCalendar;
	}
	
	public static BigDecimal getBigDecimalRound(BigDecimal bigDecimal) {
		return getBigDecimalRound(bigDecimal, 2);
	}
	public static BigDecimal getBigDecimalRound(BigDecimal bigDecimal, int scale) {
		return bigDecimal.setScale (scale, RoundingMode.HALF_EVEN);
	}
	
	public static String getOrderId(TestataOrdine testataOrdine, Destinatario destinatarioOrdine, int maxProgressivoInvio, boolean unicoDestinatario) {
		int destinatarioProgressivo = unicoDestinatario ? destinatarioOrdine.getProgressivo() : 0;
		return testataOrdine.getAnno() + "_" + testataOrdine.getNumero() + "_" + destinatarioProgressivo + "_" + (maxProgressivoInvio+1);
	}


}
