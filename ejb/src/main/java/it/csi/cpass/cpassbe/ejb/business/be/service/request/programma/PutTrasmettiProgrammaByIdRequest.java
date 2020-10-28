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
 * Request for putting the stato Programma
 */
public class PutTrasmettiProgrammaByIdRequest implements BaseRequest {
	private final UUID idProgramma;
	private final UUID idUtente;
	private final String modalitaInvio;

	/**
	 * Constructor
	 * 
	 * @param idProgramma
	 * @param idUtente
	 * @param modalitaInvio
	 */
	public PutTrasmettiProgrammaByIdRequest(UUID idProgramma, UUID idUtente, String modalitaInvio) {
		this.idProgramma = idProgramma;
		this.idUtente = idUtente;
		this.modalitaInvio = modalitaInvio;
	}

	/**
	 * @return the idProgramma
	 */
	public UUID getIdProgramma() {
		return idProgramma;
	}

	/**
	 * @return the idUtente
	 */
	public UUID getIdUtente() {
		return idUtente;
	}

	/**
	 * @return modalitaInvio
	 */
	public String getModalitaInvio() {
		return modalitaInvio;
	}

}
