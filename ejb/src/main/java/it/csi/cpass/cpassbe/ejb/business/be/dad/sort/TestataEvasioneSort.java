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
public enum TestataEvasioneSort implements JpaSort {

	ANNO_EVASIONE("annoEvasione", "tev.evasioneAnno"),
	NUMERO_EVASIONE("numeroEvasione", "tev.evasioneNumero"),
	DATA_INSERIMENTO("dataInserimento", "tev.dataInserimento"),
	FORNITORE("fornitore", "tev.cpassTFornitore.codice"),
	/** The descrizione. */
	DESCRIZIONE("descrizione", "tev.descrizione"),
	/** The stato. */
	STATO("stato", "tev.cpassDStato.statoCodice"),
	TIPO_EVASIONE("tipoEvasione", "tev.cpassDOrdTipoEvasione.tipoEvasioneCodice"),
	/** The settore. */
	SETTORE("enteCompetente", "tev.cpassTSettore.settoreId"),
	/** The id. */
	ID("id", "tev.testataEvasioneId")
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
	private TestataEvasioneSort(String modelName, String queryName) {
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
	public static TestataEvasioneSort byModelName(String modelName) {
		for(final TestataEvasioneSort is : TestataEvasioneSort.values()) {
			if(is.modelName.equalsIgnoreCase(modelName)) {
				return is;
			}
		}
		//		return TestataOrdineSort.ID;
		return null;
	}

}
