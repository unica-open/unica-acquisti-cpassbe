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

public enum DocumentoTrasportoSort implements JpaSort {

	DESPATCH_ADVISE_ID("despatchAdviceId","despatchAdviceId"),
	DATA_CONSEGNA("dataConsegna","dataConsegna"),
	FORNITORE("fornitore", "cpassTFornitore.codice"),
	STATO("stato", "cpassDStato.statoCodice"),
	EVASIONE_ANNO("id", "cpassTOrdTestataEvasioneDao.evasioneAnno"),
	NOTEDESPATCH_ADVISE_ID("note","note")
	;


	/** The model name. */
	private final String modelName;

	/** The query name. */
	private final String queryName;

	private DocumentoTrasportoSort(String modelName, String queryName) {
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



	public static DocumentoTrasportoSort byModelName(String modelName) {
		for(final DocumentoTrasportoSort is : DocumentoTrasportoSort.values()) {
			if(is.modelName.equalsIgnoreCase(modelName)) {
				return is;
			}
		}
		//		return TestataOrdineSort.ID;
		return null;
	}

}
