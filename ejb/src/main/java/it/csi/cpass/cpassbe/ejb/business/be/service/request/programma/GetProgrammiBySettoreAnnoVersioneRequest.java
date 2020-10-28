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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.programma;

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

/**
 * Request for reading the Programma by its id
 */
public class GetProgrammiBySettoreAnnoVersioneRequest implements BaseRequest {

	private final UUID    settoreId;
	private final Integer anno;
	private final Integer versione;
	private Boolean solovalidi;
	/**
	 * Constructor
	 * @param settoreId 
	 * @param anno 
	 * @param versione 
	 */
	public GetProgrammiBySettoreAnnoVersioneRequest(UUID settoreId, Integer anno, Integer versione) {
		this.settoreId = settoreId;
		this.anno = anno;
		this.versione = versione;
	}

	/**
	 * @return the id
	 */
	public UUID getSettoreId() {
		return settoreId;
	}
	
	/**
	 * @return the anno
	 */
	public Integer getAnno() {
		return anno;
	}

	/**
	 * @return the anno
	 */
	public Integer getVersione() {
		return versione;
	}

	/**
	 * @return the solovalidi
	 */
	public Boolean getSolovalidi() {
		return solovalidi;
	}

	/**
	 * @param solovalidi the solovalidi to set
	 */
	public void setSolovalidi(Boolean solovalidi) {
		this.solovalidi = solovalidi;
	}
	
	
}
