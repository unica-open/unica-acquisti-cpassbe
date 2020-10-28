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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;

/**
 * Request for posting the Intervento
 */
public class PostInterventiCopiaRequest implements BaseRequest {

	private List<Intervento> interventi = new ArrayList<>();

	private String interventoCopiaTipo;
	private String interventoImportoCopiaTipo;
	private UUID idProgramma;

	/**
	 * Constructor
	 * @param intervento the intervento
	 * @param interventoCopiaTipo 
	 * @param interventoImportoCopiaTipo 
	 * @param idProgramma 
	 */
	public PostInterventiCopiaRequest(List<Intervento> interventi,String interventoCopiaTipo,String interventoImportoCopiaTipo, UUID idProgramma) {
		this.interventi = interventi;
		this.interventoCopiaTipo=interventoCopiaTipo;
		this.interventoImportoCopiaTipo=interventoImportoCopiaTipo;
		this.idProgramma = idProgramma;
	}


	/**
	 * @return the idProgramma
	 */
	public UUID getIdProgramma() {
		return idProgramma;
	}

	/**
	 * @return the interventoCopiaTipo
	 */
	public String getInterventoCopiaTipo() {
		return interventoCopiaTipo;
	}

	/**
	 * @return the interventoImportoCopiaTipo
	 */
	public String getInterventoImportoCopiaTipo() {
		return interventoImportoCopiaTipo;
	}


	/**
	 * @return the interventi
	 */
	public List<Intervento> getInterventi() {
		return interventi;
	}
	
}
