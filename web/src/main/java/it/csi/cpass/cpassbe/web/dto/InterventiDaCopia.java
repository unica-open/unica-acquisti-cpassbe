/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - WAR submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.web.dto;

import java.util.List;

import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;

/**
 * Interventi da copia
 */
public class InterventiDaCopia {

	private List<Intervento> interventi;
	private String interventoCopiaTipo;
	private String interventoImportoCopiaTipo;
	/**
	 * @return the interventi
	 */
	public List<Intervento> getInterventi() {
		return interventi;
	}
	/**
	 * @param interventi the interventi to set
	 */
	public void setInterventi(List<Intervento> interventi) {
		this.interventi = interventi;
	}
	/**
	 * @return the interventoCopiaTipo
	 */
	public String getInterventoCopiaTipo() {
		return interventoCopiaTipo;
	}
	/**
	 * @param interventoCopiaTipo the interventoCopiaTipo to set
	 */
	public void setInterventoCopiaTipo(String interventoCopiaTipo) {
		this.interventoCopiaTipo = interventoCopiaTipo;
	}
	/**
	 * @return the interventoImportoCopiaTipo
	 */
	public String getInterventoImportoCopiaTipo() {
		return interventoImportoCopiaTipo;
	}
	/**
	 * @param interventoImportoCopiaTipo the interventoImportoCopiaTipo to set
	 */
	public void setInterventoImportoCopiaTipo(String interventoImportoCopiaTipo) {
		this.interventoImportoCopiaTipo = interventoImportoCopiaTipo;
	}
	


}
