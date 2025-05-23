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
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;

/**
 * Request for posting the Intervento
 */
public class PutInterventoPrendiInCaricoRequest implements BaseRequest {
	private UUID idRup;
	private List<Intervento> listaInterventi;

	/**
	 * Constructor
	 * @param intervento the intervento
	 */
	public PutInterventoPrendiInCaricoRequest(UUID idRup, List<Intervento> listaInterventi) {
		this.listaInterventi = listaInterventi;
		this.idRup = idRup;
	}

	/**
	 * @return the idRup
	 */
	public UUID getIdRup() {
		return idRup;
	}

	/**
	 * @param idRup the idRup to set
	 */
	public void setIdRup(UUID idRup) {
		this.idRup = idRup;
	}

	/**
	 * @return the listaInterventi
	 */
	public List<Intervento> getListaInterventi() {
		return listaInterventi;
	}

	/**
	 * @param listaInterventi the listaInterventi to set
	 */
	public void setListaInterventi(List<Intervento> listaInterventi) {
		this.listaInterventi = listaInterventi;
	}


}
