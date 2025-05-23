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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.utente;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.Utente;

/**
 * Request for posting the utente
 */
public class PutSpostaUtentiSettoreRequest implements BaseRequest {

	private List<Utente> utenti = new ArrayList<>();
	private final UUID   idSettoreOld;
	private final UUID   idSettoreNew;
	private final String controllo;
	/**
	 * Constructor
	 * @param utente the utente
	 */
	public PutSpostaUtentiSettoreRequest(UUID idSettoreOld,UUID idSettoreNew,List<Utente> utenti,String controllo) {
		this.utenti = utenti;
		this.idSettoreOld = idSettoreOld;
		this.idSettoreNew = idSettoreNew;
		this.controllo = controllo;
	}

	/**
	 * @return the Utente
	 */
	public List<Utente> getUtenti() {
		return utenti;
	}


	/**
	 * @return the controllo
	 */
	public String getControllo() {
		return controllo;
	}

	/**
	 * @return the idSettoreOld
	 */
	public UUID getIdSettoreOld() {
		return idSettoreOld;
	}

	/**
	 * @return the idSettoreNew
	 */
	public UUID getIdSettoreNew() {
		return idSettoreNew;
	}


}
