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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.stampe;

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

/**
 * Request for reading the Programma
 */
public class StampaAllegatoInterventoByProgrammaRequest implements BaseRequest {
	private UUID idProgramma;
	private String formatFile;//= "xlsx";
	
	
	/**
	 * Constructor
	 * @param idProgramma the id idProgramma
	 * @param formatFile the format file
	 */
	public StampaAllegatoInterventoByProgrammaRequest(UUID idProgramma, String formatFile) {
		this.idProgramma = idProgramma;
		this.formatFile  = formatFile;
	}


	/**
	 * @return the formatFile
	 */
	public String getFormatFile() {
		return formatFile;
	}

	/**
	 * @param formatFile the formatFile to set
	 */
	public void setFormatFile(String formatFile) {
		this.formatFile = formatFile;
	}


	/**
	 * @return the idProgramma
	 */
	public UUID getIdProgramma() {
		return idProgramma;
	}


	/**
	 * @param idProgramma the idProgramma to set
	 */
	public void setIdProgramma(UUID idProgramma) {
		this.idProgramma = idProgramma;
	}

}
