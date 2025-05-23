/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2025 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.dad.sort;

public enum ModuloRuoloPermessoSort implements JpaSort {
	MODULO("modulo", "rmrp.cpassDModulo.moduloCodice"),
	RUOLO("ruolo", "rmrp.cpassDRuolo.ruoloCodice"),
	PERMESSO("permesso", "rmrp.cpassDPermesso.permessoCodice")
	;

	/** The model name. */
	private final String modelName;

	/** The query name. */
	private final String queryName;

	/**
	 * Constructor.
	 *
	 * @param modelName the model name
	 * @param queryName the query name
	 */
	private ModuloRuoloPermessoSort(String modelName, String queryName) {
		this.modelName = modelName;
		this.queryName = queryName;
	}

	@Override
	public String getQueryName() {
		return queryName;
	}

	@Override
	public String getModelName() {
		return modelName;
	}

	/**
	 * Retrieves the Sort by its model name.
	 *
	 * @param modelName the model name
	 * @return the sort
	 */
	public static ModuloRuoloPermessoSort byModelName(String modelName) {
		for(final ModuloRuoloPermessoSort is : ModuloRuoloPermessoSort.values()) {
			if(is.modelName.equalsIgnoreCase(modelName)) {
				return is;
			}
		}
		return ModuloRuoloPermessoSort.MODULO;
	}


}
