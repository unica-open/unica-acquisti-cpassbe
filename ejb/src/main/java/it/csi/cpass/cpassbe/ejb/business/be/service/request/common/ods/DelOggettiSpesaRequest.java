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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.common.ods;


import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

public class DelOggettiSpesaRequest implements BaseRequest {

	private final Integer id;
	private final String controllo;



	/**
	 * @param id
	 */
	public DelOggettiSpesaRequest(Integer id,String controllo) {
		this.id = id;
		this.controllo = controllo;
	}



	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}



	/**
	 * @return the controllo
	 */
	public String getControllo() {
		return controllo;
	}


}
