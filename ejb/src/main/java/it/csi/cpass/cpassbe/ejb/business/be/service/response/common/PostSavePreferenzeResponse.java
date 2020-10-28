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

import java.util.HashMap;
import java.util.Map;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;

public class PostSavePreferenzeResponse extends BaseGetResponse<Map<String, Object>> {

	private Map<String, Object> preferenze = new HashMap<String, Object>();

	/**
	 * @return the preferenze
	 */
	public Map<String, Object> getPreferenze() {
		return preferenze;
	}

	/**
	 * @param preferenze the preferenze to set
	 */
	public void setPreferenze(Map<String, Object> preferenze) {
		this.preferenze = preferenze != null ? preferenze : new HashMap<String, Object>();
	}

	protected Map<String, Object> getEntity() {
		return preferenze;
	}
}
