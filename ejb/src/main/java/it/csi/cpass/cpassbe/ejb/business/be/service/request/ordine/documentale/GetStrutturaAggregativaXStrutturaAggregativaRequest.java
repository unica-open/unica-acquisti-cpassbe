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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.documentale;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

public class GetStrutturaAggregativaXStrutturaAggregativaRequest implements BaseRequest {

	private final String voceTitolario;
	private final String numeroFascicolo;
	private final String aooDossier;

	/**
	 * Constructor
	 * @param indiceclassificazione the indiceclassificazione
	 */
	public GetStrutturaAggregativaXStrutturaAggregativaRequest(String voceTitolario, String numeroFascicolo, String aooDossier) {
		this.voceTitolario   = voceTitolario;
		this.numeroFascicolo = numeroFascicolo;
		this.aooDossier = aooDossier;
	}

	/**
	 * @return the voceTitolario
	 */
	public String getVoceTitolario() {
		return voceTitolario;
	}

	/**
	 * @return the numeroFascicolo
	 */
	public String getNumeroFascicolo() {
		return numeroFascicolo;
	}

	/**
	 * @return the aoo
	 */
	public String getAooDossier() {
		return aooDossier;
	}

}
