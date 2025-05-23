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
package it.csi.cpass.cpassbe.ejb.business.be.dad.sort;

/**
 * Sort mapper for TestataOrdine.
 */
public enum ScaricoMepaTestataEvasioneSort implements JpaSort {
	// TODO da riadattare se mai ci fosse la necessità
	FORNITORE("fornitore", "tev.cpassTFornitore.codice"),
	DESTINATARIO("destinatario", "??????"),
	IDENTIFICATIVO_MEPA("stato", "?????"),
	STATO("stato", "tev.cpassDStato.statoCodice"),
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
	private ScaricoMepaTestataEvasioneSort(String modelName, String queryName) {
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
	public static ScaricoMepaTestataEvasioneSort byModelName(String modelName) {
		for(final ScaricoMepaTestataEvasioneSort is : ScaricoMepaTestataEvasioneSort.values()) {
			if(is.modelName.equalsIgnoreCase(modelName)) {
				return is;
			}
		}
		//		return TestataOrdineSort.ID;
		return null;
	}

}
