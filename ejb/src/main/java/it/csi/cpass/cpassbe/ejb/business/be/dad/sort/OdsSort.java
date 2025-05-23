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

public enum OdsSort implements JpaSort {
	CODICE("codice", "os.oggettiSpesaCodice"),
	DESCRIZIONE("descrizione", "os.oggettiSpesaDescrizione"),
	DATA_VALIDITA_FINE("dataValiditaFine", "os.dataValiditaFine"),
	DATA_VALIDITA_INIZIO("dataValiditaInizio", "os.dataValiditaInizio"),
	PREZZO_UNITARIO("prezzoUnitario", "os.prezzoUnitario"),
	QUANTITA_MAX_RICHIEDIBILE("quantitaMaxRichiedibile", "os.quantitaMaxRichiedibile"),
	INVENTARIABILE("inventariabile", "os.inventariabile"),
	GENERICO("generico", "os.generico"),
	ALIQUOTE_IVA_DESCRIZIONE("aliquoteIvaDescrizione","os.cpassDAliquoteIva.aliquoteIvaDescrizione"),
	UNITA_MISURA_DESCRIZIONE("unitaMisuraDescrizione","os.cpassDUnitaMisura.unitaMisuraDescrizione"),
	CPV_CODICE("cpvCodice","os.cpassDCpv.cpvCodice")
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
	private OdsSort(String modelName, String queryName) {
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
	public static OdsSort byModelName(String modelName) {
		for(final OdsSort is : OdsSort.values()) {
			if(is.modelName.equalsIgnoreCase(modelName)) {
				return is;
			}
		}
		return OdsSort.CODICE;
	}

}
