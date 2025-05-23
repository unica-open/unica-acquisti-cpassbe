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
 * Sort mapper for Intervento.
 */
public enum UtenteSort implements JpaSort {

	UTENTE_NOME("utenteNome", "ute.utenteNome"),
	UTENTE_COGNOME("utenteCognome", "ute.utenteCognome"),
	UTENTE_CF("codiceFiscale", "ute.codiceFiscale"),
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
	private UtenteSort(String modelName, String queryName) {
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

	public static UtenteSort byModelName(String modelName) {
		for(final UtenteSort is : UtenteSort.values()) {
			if(is.modelName.equalsIgnoreCase(modelName)) {
				return is;
			}
		}
		return null; //UtenteSort.UTENTE_NOME;
	}

}
