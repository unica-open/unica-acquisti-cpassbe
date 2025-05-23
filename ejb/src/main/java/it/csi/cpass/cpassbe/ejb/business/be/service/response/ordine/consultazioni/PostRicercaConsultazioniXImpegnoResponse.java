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
import it.csi.cpass.cpassbe.lib.dto.ord.consultazioni.ConsultazioniImpegno;

/**
 * Response for reading TestataOrdine by its id.
 */
/*
public class PostRicercaConsultazioniXImpegnoResponse extends BasePagedResponse<ConsultazioniImpegno> {

	private PagedList<ConsultazioniImpegno> consultazioneImpegno = new PagedListImpl<>();


	public PagedList<ConsultazioniImpegno> getConsultazioniImpegno() {
		return consultazioneImpegno;
	}


	public void setConsultazioniImpegno(PagedList<ConsultazioniImpegno> consultazioniImpegno) {
		this.consultazioneImpegno = consultazioniImpegno != null ? consultazioniImpegno : new PagedListImpl<>();
	}

	@Override
	protected PagedList<ConsultazioniImpegno> getEntity() {
		return consultazioneImpegno;
	}
 */

public class PostRicercaConsultazioniXImpegnoResponse extends  BaseGetResponse<List<ConsultazioniImpegno>> {

	private List<ConsultazioniImpegno> listaconsultazioniImpegno;



	/**
	 * @return the listaconsultazioniImpegno
	 */
	public List<ConsultazioniImpegno> getListaconsultazioniImpegno() {
		return listaconsultazioniImpegno;
	}



	/**
	 * @param listaconsultazioniImpegno the listaconsultazioniImpegno to set
	 */
	public void setListaconsultazioniImpegno(List<ConsultazioniImpegno> listaconsultazioniImpegno) {
		this.listaconsultazioniImpegno = listaconsultazioniImpegno;
	}



	@Override
	protected List<ConsultazioniImpegno> getEntity() {
		return listaconsultazioniImpegno;
	}


}
