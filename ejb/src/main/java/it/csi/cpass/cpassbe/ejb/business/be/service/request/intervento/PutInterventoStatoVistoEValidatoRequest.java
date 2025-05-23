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

import java.util.ArrayList;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;

/**
 * Request for putting the stato Intervento
 */
public class PutInterventoStatoVistoEValidatoRequest implements BaseRequest {

	//	private final Intervento intervento;
	private List<Intervento> interventi = new ArrayList<>();

	/**
	 * Constructor
	 * @param intervento the intervento
	 */
	public PutInterventoStatoVistoEValidatoRequest(List<Intervento> interventi) {
		this.interventi = interventi;
	}

	/**
	 * @return the interventi
	 */
	public List<Intervento> getInterventi() {
		return interventi;
	}

}
