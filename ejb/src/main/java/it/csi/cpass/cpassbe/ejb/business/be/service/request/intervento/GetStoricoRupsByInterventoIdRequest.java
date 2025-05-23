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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento;

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

/**
 * Request for posting the Intervento
 */
public class GetStoricoRupsByInterventoIdRequest implements BaseRequest {
	private final UUID idIntervento;
	//private List<Utente> utenti;

	/**
	 * Constructor
	 * @param intervento the intervento
	 */
	public GetStoricoRupsByInterventoIdRequest(UUID idIntervento) {//, List<Utente> utenti) {
		this.idIntervento = idIntervento;
		//this.utenti = utenti;
	}

	/**
	 * @return the idIntervento
	 */
	public UUID getIdIntervento() {
		return idIntervento;
	}

	/**
	 * @return the cpvs
	 */
	/*
	public List<Utente> getUtenti() {
		return utenti;
	}
	 */
}
