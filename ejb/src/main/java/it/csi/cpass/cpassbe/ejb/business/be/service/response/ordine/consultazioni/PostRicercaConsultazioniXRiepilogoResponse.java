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

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.ord.consultazioni.ConsultazioniRiepilogo;

/**
 * Response for reading TestataOrdine by its id.
 */
public class PostRicercaConsultazioniXRiepilogoResponse extends BaseGetResponse<ConsultazioniRiepilogo> {

	private ConsultazioniRiepilogo consultazioniRiepilogo;


	/**
	 * @return the consultazioniRiepilogo
	 */
	public ConsultazioniRiepilogo getConsultazioniRiepilogo() {
		return consultazioniRiepilogo;
	}


	/**
	 * @param consultazioniRiepilogo the consultazioniRiepilogo to set
	 */
	public void setConsultazioniRiepilogo(ConsultazioniRiepilogo consultazioniRiepilogo) {
		this.consultazioniRiepilogo = consultazioniRiepilogo;
	}


	@Override
	protected ConsultazioniRiepilogo getEntity() {
		// TODO Auto-generated method stub
		return consultazioniRiepilogo;
	}



}
