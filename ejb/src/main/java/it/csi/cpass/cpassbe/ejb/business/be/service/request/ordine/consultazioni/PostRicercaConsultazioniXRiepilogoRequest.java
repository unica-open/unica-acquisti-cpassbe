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
public class PostRicercaConsultazioniXRiepilogoRequest implements BaseRequest {

	private final RicercaXConsultazioni ricercaXConsultazioni;
	private final List<Impegno> listImpegnoEsercizio;
	private final List<Impegno> listImpegnoEsercizioProssimo;

	public PostRicercaConsultazioniXRiepilogoRequest(RicercaXConsultazioni ricercaXConsultazioni
			, List<Impegno> listImpegnoEsercizio
			, List<Impegno> listImpegnoEsercizioProssimo
			) {
		this.ricercaXConsultazioni = ricercaXConsultazioni;
		this.listImpegnoEsercizio = listImpegnoEsercizio;
		this.listImpegnoEsercizioProssimo = listImpegnoEsercizioProssimo;
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
	 * @return the listImpegnoEsercizioProssimo
	 */
	public List<Impegno> getListImpegnoEsercizioProssimo() {
		return listImpegnoEsercizioProssimo;
	}

}
