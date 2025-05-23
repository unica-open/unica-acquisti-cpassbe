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

public class GetProtocolloOrigineByAnnoNumRequest implements BaseRequest {

	private final Integer anno;
	private final String  numero;

	/**
	 * Constructor
	 * @param anno the anno
	 * @param numero the numero
	 */
	public GetProtocolloOrigineByAnnoNumRequest(Integer anno, String numero) {
		this.anno   = anno;
		this.numero = numero;
	}

	public Integer getAnno() {
		return anno;
	}

	public String getNumero() {
		return numero;
	}

}
