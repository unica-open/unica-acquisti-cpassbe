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

import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.Cpv;

/**
 * Request for posting the Intervento
 */
public class PostCpvsByInterventoIdRequest implements BaseRequest {
	private final UUID idIntervento;
	private final List<Cpv> cpvs;
	//private final Intervento intervento;




	/**
	 * Constructor
	 * @param intervento the intervento
	 */
	public PostCpvsByInterventoIdRequest(UUID idIntervento, List<Cpv> cpvs) {
		this.idIntervento = idIntervento;
		this.cpvs = cpvs;
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
	public List<Cpv> getCpvs() {
		return cpvs;
	}

}
