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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.consultazioni;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.ord.consultazioni.ConsultazioniOrdine;

/**
 * Response for reading TestataOrdine by its id.
 */
public class PostRicercaConsultazioniXOrdineResponse extends  BaseGetResponse<List<ConsultazioniOrdine>> {

	private List<ConsultazioniOrdine> listaconsultazioniOrdine;
	/*
	private PagedList<ConsultazioniOrdine> consultazioneOrdine = new PagedListImpl<>();


	public PagedList<ConsultazioniOrdine> getConsultazioniOrdine() {
		return consultazioneOrdine;
	}


	public void setConsultazioniOrdine(PagedList<ConsultazioniOrdine> consultazioniOrdine) {
		this.consultazioneOrdine = consultazioniOrdine != null ? consultazioniOrdine : new PagedListImpl<>();
	}

	@Override
	protected PagedList<ConsultazioniOrdine> getEntity() {
		return consultazioneOrdine;
	}
	 */

	/**
	 * @return the listaconsultazioniOrdine
	 */
	public List<ConsultazioniOrdine> getListaconsultazioniOrdine() {
		return listaconsultazioniOrdine;
	}

	/**
	 * @param listaconsultazioniOrdine the listaconsultazioniOrdine to set
	 */
	public void setListaconsultazioniOrdine(List<ConsultazioniOrdine> listaconsultazioniOrdine) {
		this.listaconsultazioniOrdine = listaconsultazioniOrdine;
	}

	@Override
	protected List<ConsultazioniOrdine> getEntity() {
		// TODO Auto-generated method stub
		return listaconsultazioniOrdine;
	}

}
