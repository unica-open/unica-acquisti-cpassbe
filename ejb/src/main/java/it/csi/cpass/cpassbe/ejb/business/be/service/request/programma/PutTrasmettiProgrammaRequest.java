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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.programma;

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.pba.Programma;

/**
 * Request for putting the stato Programma
 */
public class PutTrasmettiProgrammaRequest implements BaseRequest {
	private final Programma programma;
	private final UUID idUtente;
	private final String modalitaInvio;

	/**
	 * Constructor
	 *
	 * @param idProgramma
	 * @param idUtente
	 * @param modalitaInvio
	 */
	public PutTrasmettiProgrammaRequest(Programma programma, UUID idUtente, String modalitaInvio) {
		this.programma = programma;
		this.idUtente = idUtente;
		this.modalitaInvio = modalitaInvio;
	}

	/**
	 * @return the idProgramma
	 */
	public Programma getProgramma() {
		return programma;
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
