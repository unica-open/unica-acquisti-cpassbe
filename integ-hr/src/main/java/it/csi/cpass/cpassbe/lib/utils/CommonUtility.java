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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtility {

	public static int getTotalPage(Integer totaleRisultati, Integer numElementePerPagina) {
		int numPagine = totaleRisultati / numElementePerPagina;
		if (totaleRisultati % numElementePerPagina != 0) {
			numPagine++;
		}
		return numPagine;
	}
	
    public static String dateToString(Date data,String format){
        DateFormat dateFormat = new SimpleDateFormat(format);
        if(data==null) {
        	data = new Date();
        }
        return dateFormat.format(data);
    }
	
}
