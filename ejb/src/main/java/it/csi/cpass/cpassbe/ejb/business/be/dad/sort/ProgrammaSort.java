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
package it.csi.cpass.cpassbe.ejb.business.be.dad.sort;

/**
 * Sort mapper for Programma.
 */
public enum ProgrammaSort implements JpaSort {

	/** The versione. */
	VERSIONE("versione", "programmaVersione"),
	/** The id. */
	ID("id", "programmaId");

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
	private ProgrammaSort(String modelName, String queryName) {
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
	public static ProgrammaSort byModelName(String modelName) {
		for (ProgrammaSort is : ProgrammaSort.values()) {
			if (is.modelName.equalsIgnoreCase(modelName)) {
				return is;
			}
		}
		return ProgrammaSort.ID;
	}

}
