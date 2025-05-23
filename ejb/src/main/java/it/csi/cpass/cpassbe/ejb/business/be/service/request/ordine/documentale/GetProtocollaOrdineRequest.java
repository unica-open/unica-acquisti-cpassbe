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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.documentale;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.ord.ProtocolloOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;

public class GetProtocollaOrdineRequest implements BaseRequest {

	private final TestataOrdine testataOrdine;
	private final ProtocolloOrdine protocolloOrdine;

	/**
	 * @param testataOrdineId
	 */
	public GetProtocollaOrdineRequest(TestataOrdine testataOrdine,ProtocolloOrdine protocolloOrdine) {
		super();
		this.testataOrdine = testataOrdine;
		this.protocolloOrdine = protocolloOrdine;
	}

	/**
	 * @return the testataOrdine
	 */
	public TestataOrdine getTestataOrdine() {
		return testataOrdine;
	}

	/**
	 * @return the protocolloOrdine
	 */
	public ProtocolloOrdine getProtocolloOrdine() {
		return protocolloOrdine;
	}



}
