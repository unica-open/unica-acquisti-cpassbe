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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.SettoreIndirizzo;


public class PostRicercaSettoreIndirizzoResponse extends BaseGetResponse<List<SettoreIndirizzo>> {

	private List<SettoreIndirizzo> list;

	/**
	 * @return the settori
	 */
	public List<SettoreIndirizzo> getSettoreIndirizzi() {
		return list;
	}

	/**
	 * @param settoreIndirizzi the settori to set
	 */
	public void setSettoreIndirizzi(List<SettoreIndirizzo> settoreIndirizzi) {
		this.list = settoreIndirizzi;
	}

	@Override
	protected List<SettoreIndirizzo> getEntity() {
		return list;
	}
}
