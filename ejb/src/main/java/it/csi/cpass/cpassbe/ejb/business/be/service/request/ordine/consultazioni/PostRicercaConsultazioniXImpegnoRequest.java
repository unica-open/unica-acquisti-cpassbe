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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.consultazioni;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.ord.consultazioni.RicercaXConsultazioni;

/**
 * Response for reading TestataOrdine by its id.
 */
//public class PostRicercaConsultazioniXImpegnoRequest extends BasePagedRequest {
public class PostRicercaConsultazioniXImpegnoRequest implements BaseRequest {


	private final RicercaXConsultazioni ricercaXConsultazioni;
	private List<Impegno> listImpegnoEsercizio;
	private List<Impegno> listImpegnoEsercizioProssimo;

	public PostRicercaConsultazioniXImpegnoRequest(RicercaXConsultazioni ricercaXConsultazioni
			, List<Impegno> listImpegnoEsercizio
			, List<Impegno> listImpegnoEsercizioProssimo
			//, Integer page
			//, Integer size
			) {
		//super(size, page, null, null);
		this.ricercaXConsultazioni 			= ricercaXConsultazioni;
		this.listImpegnoEsercizio 			= listImpegnoEsercizio;
		this.listImpegnoEsercizioProssimo 	= listImpegnoEsercizioProssimo;

	}

	/**
	 * @return the ricercaXConsultazioni
	 */
	public RicercaXConsultazioni getRicercaXConsultazioni() {
		return ricercaXConsultazioni;
	}

	/**
	 * @return the listImpegnoEsercizio
	 */
	public List<Impegno> getListImpegnoEsercizio() {
		return listImpegnoEsercizio;
	}

	/**
	 * @param listImpegnoEsercizio the listImpegnoEsercizio to set
	 */
	public void setListImpegnoEsercizio(List<Impegno> listImpegnoEsercizio) {
		this.listImpegnoEsercizio = listImpegnoEsercizio;
	}

	/**
	 * @return the listImpegnoEsercizioProssimo
	 */
	public List<Impegno> getListImpegnoEsercizioProssimo() {
		return listImpegnoEsercizioProssimo;
	}

	/**
	 * @param listImpegnoEsercizioProssimo the listImpegnoEsercizioProssimo to set
	 */
	public void setListImpegnoEsercizioProssimo(List<Impegno> listImpegnoEsercizioProssimo) {
		this.listImpegnoEsercizioProssimo = listImpegnoEsercizioProssimo;
	}


}
