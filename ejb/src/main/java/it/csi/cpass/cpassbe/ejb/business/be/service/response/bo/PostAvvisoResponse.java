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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.bo;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BasePostResponse;
import it.csi.cpass.cpassbe.lib.dto.Notifica;

/**
 * Response for reading Notifica by its id.
 */
public class PostAvvisoResponse extends BasePostResponse<Integer, Notifica> {

	private Notifica notifica;

	/**
	 * @return the notifica
	 */
	public Notifica getNotifica() {
		return notifica;
	}

	/**
	 * @param elaborazione the notifica to set
	 */
	public void setNotifica(Notifica notifica) {
		this.notifica = notifica;
	}

	@Override
	protected Notifica getEntity() {
		return notifica;
	}

	@Override
	protected String getBaseUri() {
		return "notifica";
	}

}
