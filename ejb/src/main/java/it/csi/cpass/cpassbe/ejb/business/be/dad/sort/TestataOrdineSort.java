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
 * Sort mapper for TestataOrdine.
 */
public enum TestataOrdineSort implements JpaSort {

	ANNO_ORDINE("annoOrdine", "tord.ordineAnno"),
	NUMERO_ORDINE("numeroOrdine", "tord.ordineNumero"),
	DATA_EMISSIONE("dataEmissione", "tord.dataEmissione"),
	FORNITORE("fornitore", "tord.cpassTFornitore.codice"),
	PROVVEDIMENTO("provvedimento", "tord.provvedimentoAnno"),
	/** The descrizione. */
	DESCRIZIONE("descrizione", "tord.descrizioneAcquisto"),
	/** The stato. */
	STATO("stato", "tord.cpassDStato.statoCodice"),
	/** The settore. */
	SETTORE("enteEmittente", "tord.cpassTSettore.settoreId"),
	/** The id. */
	ID("id", "tord.testataOrdineId")
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
	private TestataOrdineSort(String modelName, String queryName) {
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
	public static TestataOrdineSort byModelName(String modelName) {
		for(TestataOrdineSort is : TestataOrdineSort.values()) {
			if(is.modelName.equalsIgnoreCase(modelName)) {
				return is;
			}
		}
//		return TestataOrdineSort.ID;
		return null;
	}
	
}
