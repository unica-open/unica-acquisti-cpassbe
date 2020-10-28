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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.common;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BasePagedRequest;
import it.csi.cpass.cpassbe.lib.dto.ListinoFornitore;

public class PostRicercaListinoFornitoreRequest  extends BasePagedRequest {

	private final ListinoFornitore listinoFornitore;

	public PostRicercaListinoFornitoreRequest(Integer page, Integer size, String sort, String direction, ListinoFornitore listinoFornitore) {
		super(size, page, sort, direction);
		this.listinoFornitore = listinoFornitore;
	}


	/**
	 * @return the listinoFornitore
	 */
	public ListinoFornitore getListinoFornitore() {
		return listinoFornitore;
	}
	
}
