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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.common;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BasePostResponse;
import it.csi.cpass.cpassbe.lib.dto.ListinoFornitore;

public class PostListinoFornitoreResponse extends BasePostResponse<Integer, ListinoFornitore> {

	private ListinoFornitore listinoFornitore;

	/**
	 * @return the listinoFornitore
	 */
	public ListinoFornitore getListinoFornitore() {
		return listinoFornitore;
	}

	/**
	 * @param listinoFornitore the listinoFornitore to set
	 */
	public void setListinoFornitore(ListinoFornitore listinoFornitore) {
		this.listinoFornitore = listinoFornitore;
	}

	@Override
	protected ListinoFornitore getEntity() {
		return listinoFornitore;
	}

	@Override
	protected String getBaseUri() {
		return "listinoFornitore";
	}

}
