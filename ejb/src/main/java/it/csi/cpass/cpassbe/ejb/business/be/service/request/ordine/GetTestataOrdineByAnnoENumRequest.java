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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine;

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

public class GetTestataOrdineByAnnoENumRequest implements BaseRequest {

	private final Integer anno;
	private final Integer numero;
	private final UUID enteId;

	/**
	 * Constructor
	 * @param anno the anno
	 * @param numero the numero
	 */
	public GetTestataOrdineByAnnoENumRequest(Integer anno, Integer numero, UUID enteId) {
		this.anno = anno;
		this.numero = numero;
		this.enteId = enteId;
	}

	public Integer getAnno() {
		return anno;
	}

	public Integer getNumero() {
		return numero;
	}

	public UUID getEnteId() {
		return enteId;
	}

}
