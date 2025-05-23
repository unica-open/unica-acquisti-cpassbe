/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.dad.sort;

public enum TestataRdaSort implements JpaSort {

	ANNO_RDA("anno", "trda.anno"),
	NUMERO_RDA("numero", "trda.numero"),
	DATA_INS("dataCreazione", "trda.dataCreazione"),
	/** The descrizione. */
	DESCRIZIONE("descrizione", "tord.descrizione"),
	/** The stato. */
	STATO("stato", "tord.cpassDStato.statoCodice")
	;

	/** The model name. */
	private final String modelName;

	/** The query name. */
	private final String queryName;

	private TestataRdaSort(String modelName, String queryName) {
		this.modelName = modelName;
		this.queryName = queryName;
	}

	@Override
	public String getModelName() {
		return modelName;
	}

	@Override
	public String getQueryName() {
		return queryName;
	}


	/**
	 * Retrieves the Sort by its model name.
	 *
	 * @param modelName the model name
	 * @return the sort
	 */
	public static TestataRdaSort byModelName(String modelName) {
		for(final TestataRdaSort is : TestataRdaSort.values()) {
			if(is.modelName.equalsIgnoreCase(modelName)) {
				return is;
			}
		}
		//		return TestataOrdineSort.ID;
		return null;
	}

}
