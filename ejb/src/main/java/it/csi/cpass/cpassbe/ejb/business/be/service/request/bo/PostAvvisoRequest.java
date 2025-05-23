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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.bo;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.Notifica;

/**
 * Request for posting the Notifica
 */
public class PostAvvisoRequest implements BaseRequest {

	private final Notifica notifica;

	/**
	 * Constructor
	 * @param elaborazioneMessaggio the notifica
	 */
	public PostAvvisoRequest(Notifica notifica) {
		this.notifica = notifica;
	}

	/**
	 * @return the notifica
	 */
	public Notifica getNotifica() {
		return notifica;
	}
}
