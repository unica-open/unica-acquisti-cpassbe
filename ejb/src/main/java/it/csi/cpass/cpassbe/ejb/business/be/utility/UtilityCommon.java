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
package it.csi.cpass.cpassbe.ejb.business.be.utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.util.log.LogUtil;

public class UtilityCommon {

	/** The logger */
	protected final static LogUtil log = new LogUtil(UtilityCommon.class);

	public static boolean isSettoreValid(SettoreDad settoreDad, UUID settoreId) {
		return isSettoreValid( settoreDad,  settoreId, new Date());
	}

	public static boolean isSettoreValid(SettoreDad settoreDad, UUID settoreId, Date data) {
		final Settore settore = settoreDad.findById(settoreId);
		if (settore.getDataCancellazione() != null && settore.getDataCancellazione().before(data)) {
			return false;
		}
		return true;
	}

	/**
	 * Gets the parameter
	 *
	 * @param params
	 * @param paramName
	 * @return
	 */
	protected static String getParameter(Map<String, String> params, String paramName) {
		return params.get(paramName);
	}


	public static int getTotalPage(Integer totaleRisultati, Integer numElementePerPagina) {
		int numPagine = totaleRisultati / numElementePerPagina;
		if (totaleRisultati % numElementePerPagina != 0) {
			numPagine++;
		}
		return numPagine;
	}

	public static <E> List<E> estraiLaPaginaScelta (List<E> list, int numPage, int elementInPage)
	{
		//		List<E> risultato = new ArrayList<E>();
		//		int lenMax =list.size();
		//		int indiceStart = numPage * elementInPage;
		//		int indiceStop  = indiceStart+elementInPage-1;
		//		if(indiceStop>=(lenMax-1)) {
		//			indiceStop = lenMax-1;
		//		}
		//		if((indiceStart > lenMax)) {
		//			return risultato;
		//		}
		//		for(int i =indiceStart;i<=indiceStop;i++){
		//			risultato.add(list.get(i));
		//		}
		//		return risultato;

		// fromIndex incluso
		// toIndex escluso
		final int fromIndex = numPage * elementInPage;
		final int toIndex = fromIndex+elementInPage > list.size() ? list.size() : fromIndex+elementInPage;
		return list.subList(fromIndex,toIndex);

	}

	public static String getDate() {
		final String pattern = "dd/MM/yyyy";
		final DateFormat df = new SimpleDateFormat(pattern);
		final Date today = Calendar.getInstance().getTime();
		final String todayAsString = df.format(today);
		return  todayAsString;
	}

}
