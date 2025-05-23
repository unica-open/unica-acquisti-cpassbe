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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.documentale;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.ord.ProtocolloOrdine;

/**
 * Response for reading ProtocolloOrigine by its id.
 */
public class GetStrutturaAggregativaXIndiceclassificazioneEstesaResponse extends BaseGetResponse<ProtocolloOrdine> {

	private ProtocolloOrdine protocolloOrdine;

	/**
	 * @return the protocolloOrdine
	 */
	public ProtocolloOrdine getProtocolloOrdine() {
		return protocolloOrdine;
	}

	/**
	 * @param protocolloOrdine the protocolloOrdine to set
	 */
	public void setProtocolloOrdine(ProtocolloOrdine protocolloOrdine) {
		this.protocolloOrdine = protocolloOrdine;
	}

	@Override
	protected ProtocolloOrdine getEntity() {
		// TODO Auto-generated method stub
		return protocolloOrdine;
	}







}
