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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.lib.dto.Parametro;

public class UtilityParametri {

	/**
	 * Gets the parameter
	 * 
	 * @param params
	 * @param paramName
	 * @return
	 */
	public static String getParameter(Map<String, String> params, String paramName) {
		return params.get(paramName);
	}

	public static Map<String, String> readParams(SystemDad systemDad, String riferimento, UUID enteId) {
		List<Parametro> parameters = systemDad.getParametriList(null, riferimento, null, enteId);
		Map<String, String> params = new HashMap<String, String>();
		for (Parametro parametro : parameters) {
			params.put(parametro.getChiave(), parametro.getValore());
		}
		return params;
	}

}
