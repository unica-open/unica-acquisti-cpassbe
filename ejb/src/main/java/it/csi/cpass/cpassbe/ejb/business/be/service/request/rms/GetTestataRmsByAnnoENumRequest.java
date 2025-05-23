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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.rms;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

public class GetTestataRmsByAnnoENumRequest implements BaseRequest {

	private final Integer anno;
	private final Integer numero;

	/**
	 * Constructor
	 * @param anno the anno
	 * @param numero the numero
	 */
	public GetTestataRmsByAnnoENumRequest(Integer anno, Integer numero) {
		this.anno = anno;
		this.numero = numero;
	}

	public Integer getAnno() {
		return anno;
	}

	public Integer getNumero() {
		return numero;
	}

}
