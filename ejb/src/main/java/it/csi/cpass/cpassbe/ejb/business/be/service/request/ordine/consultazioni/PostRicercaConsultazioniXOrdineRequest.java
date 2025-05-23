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

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.ord.consultazioni.RicercaXConsultazioni;

/**
 * Response for reading TestataOrdine by its id.
 */
public class PostRicercaConsultazioniXOrdineRequest implements BaseRequest {

	private final RicercaXConsultazioni ricercaXConsultazioni;
	//Integer page;
	//Integer size;
	public PostRicercaConsultazioniXOrdineRequest(RicercaXConsultazioni ricercaXConsultazioni) {
		//public PostRicercaConsultazioniXOrdineRequest(Integer page, Integer size,RicercaXConsultazioni ricercaXConsultazioni) {
		//super(size, page, null, null);
		this.ricercaXConsultazioni = ricercaXConsultazioni;
	}

	/**
	 * @return the ricercaXConsultazioni
	 */
	public RicercaXConsultazioni getRicercaXConsultazioni() {
		return ricercaXConsultazioni;
	}

}
