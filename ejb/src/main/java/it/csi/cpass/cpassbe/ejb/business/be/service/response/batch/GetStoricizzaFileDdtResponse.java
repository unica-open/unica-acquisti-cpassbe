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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.batch;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;

/**
 * The Class GetMetadatiFunzioneResponse.
 */
public class GetStoricizzaFileDdtResponse extends BaseGetResponse<String> {

	/** The lingue. */
	private String ris;




	/**
	 * @return the ris
	 */
	public String getRis() {
		return ris;
	}




	/**
	 * @param ris the ris to set
	 */
	public void setRis(String ris) {
		this.ris = ris;
	}




	@Override
	protected String getEntity() {
		return ris;
	}



}
