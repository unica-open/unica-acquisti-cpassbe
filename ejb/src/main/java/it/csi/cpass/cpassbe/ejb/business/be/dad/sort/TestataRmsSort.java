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
 * Sort mapper for TestataRms.
 */
public enum TestataRmsSort implements JpaSort {

	CPV("cpv",  "riga.cpassDOggettiSpesa.cpassDCpv.cpvCodice" ),
	ODS("ods",  "riga.cpassDOggettiSpesa.oggettiSpesaCodice" ),
	ANNO_RMS("annoRms", "tRms.rmsAnno"),
	NUMERO_RMS("numeroRms", "tRms.rmsNumero"),
	DATA_CREAZIONE("dataCreazione", "trms.dataCreazione"),
	/** The descrizione. */
	DESCRIZIONE("descrizione", "trms.rmsDescrizione"),
	/** The stato. */
	STATO("stato", "trms.cpassDStato.statoCodice"),
	/** The settore. */
	SETTORE("settoreEmittente", "trms.cpassTSettoreEmittente.settoreId"),
	SETTORE_DESTINATARIO("settoreDestinatario", "trms.cpassTSettoreDestinatario.settoreId"),
	/** The id. */
	ID("id", "trms.testataRmsId"),
	QUANTITA("quantita",  "riga.quantita" ),
	FORNITORE("fornitore",  "trms.cpassTSettoreEmittente.settoreCodice" ),
	DESTINATARIO("destinatario", "trms.cpassTSettoreDestinatario.settoreCodice"),
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
	private TestataRmsSort(String modelName, String queryName) {
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
	public static TestataRmsSort byModelName(String modelName) {
		for(final TestataRmsSort is : TestataRmsSort.values()) {
			if(is.modelName.equalsIgnoreCase(modelName)) {
				return is;
			}
		}
		//		return TestataOrdineSort.ID;
		return null;
	}

}
