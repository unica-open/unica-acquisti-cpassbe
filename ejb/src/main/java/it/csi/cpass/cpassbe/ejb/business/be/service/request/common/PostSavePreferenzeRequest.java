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

import java.util.Map;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

public class PostSavePreferenzeRequest implements BaseRequest {
	
	private final Map<String, Object> preferenze;

	/**
	 * Constructor
	 * 
	 * @param preferenze the preferenze
	 */
	public PostSavePreferenzeRequest(Map<String, Object> preferenze) {
		this.preferenze = preferenze;
	}

	/**
	 * @return the preferenze
	 */
	public Map<String, Object> getPreferenze() {
		return preferenze;
	}


}
