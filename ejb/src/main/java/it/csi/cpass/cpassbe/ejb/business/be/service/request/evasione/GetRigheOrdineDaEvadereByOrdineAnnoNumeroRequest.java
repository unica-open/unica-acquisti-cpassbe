/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

/**
 * Request for reading riga ordine
 */
public class GetRigheOrdineDaEvadereByOrdineAnnoNumeroRequest implements BaseRequest {

	private final Integer anno;
	private final Integer numero;

	/**
	 * Constructor
	 * 
	 * @param id the id
	 */
	public GetRigheOrdineDaEvadereByOrdineAnnoNumeroRequest(Integer anno, Integer numero) {
		this.anno = anno;
		this.numero = numero;
	}

	/**
	 * @return the anno
	 */
	public Integer getAnno() {
		return anno;
	}

	/**
	 * @return the numero
	 */
	public Integer getNumero() {
		return numero;
	}

}
