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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.magazzino;

import it.csi.cpass.cpassbe.ejb.business.be.dad.MagazzinoDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;

/**
 * Base class for services for /rms path
 * @param <Q> the request type
 * @param <R> the response type
 */
public abstract class BaseMagazzinoService<Q extends BaseRequest, R extends BaseResponse> extends BaseService<Q, R> {

	/** Data Access Delegate for magazzinoDad  */
	protected final MagazzinoDad magazzinoDad;

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param rmsDad the rms dad
	 */
	protected BaseMagazzinoService(ConfigurationHelper configurationHelper, MagazzinoDad magazzinoDad) {
		super(configurationHelper);
		this.magazzinoDad = magazzinoDad;
	}

}
