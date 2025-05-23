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
	/**
	 * 
	 * @param systemDad
	 * @param riferimento
	 * @param enteId
	 * @return
	 */
	public static Map<String, String> readParams(SystemDad systemDad, String riferimento, UUID enteId) {
		final List<Parametro> parameters = systemDad.getParametriList(null, riferimento, null, enteId);
		final Map<String, String> params = new HashMap<>();
		for (final Parametro parametro : parameters) {
			params.put(parametro.getChiave(), parametro.getValore());
		}
		return params;
	}
	/*
	public static void main (String[] args) {
		String SHIBBOLETH_COOKIE_PREFIX ="_shibsession_";
		String name = "_shibsession_cpass-paliv1wrup";
		System.out.println("wawa-->" + name.replace(SHIBBOLETH_COOKIE_PREFIX,""));
	}
	 */
}
