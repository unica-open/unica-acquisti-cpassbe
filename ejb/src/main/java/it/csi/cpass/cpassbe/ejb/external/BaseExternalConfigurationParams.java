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
package it.csi.cpass.cpassbe.ejb.external;

import it.csi.cpass.cpassbe.lib.external.itf.ConfigurationParam;

/**
 * Basic external configuration parameters
 */
public enum BaseExternalConfigurationParams implements ConfigurationParam {

	/** The implementor type */
	IMPLEMENTOR("IMPLEMENTOR"),
	/** The implementor EJB name */
	IMPLEMENTOR_EJB_NAME("IMPLEMENTOR_EJB_NAME"),
	/** The implementor CDI name */
	IMPLEMENTOR_CDI_NAME("IMPLEMENTOR_CDI_NAME"),
	/** The implementor POJO name */
	IMPLEMENTOR_POJO_NAME("IMPLEMENTOR_POJO_NAME"),
	;

	private final String paramName;

	private BaseExternalConfigurationParams(String paramName) {
		this.paramName = paramName;
	}

	@Override
	public String getParamName() {
		return paramName;
	}
}
