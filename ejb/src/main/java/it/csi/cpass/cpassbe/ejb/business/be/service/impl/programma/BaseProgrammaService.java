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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.programma;

import it.csi.cpass.cpassbe.ejb.business.be.dad.ProgrammaDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;

/**
 * Base class for services for /programma path
 * @param <Q> the request type
 * @param <R> the response type
 */
public abstract class BaseProgrammaService<Q extends BaseRequest, R extends BaseResponse> extends BaseService<Q, R> {

	/** Data Access Delegate for Programma */
	protected final ProgrammaDad programmaDad;

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param programmaDad the programma dad
	 */
	protected BaseProgrammaService(ConfigurationHelper configurationHelper, ProgrammaDad programmaDad) {
		super(configurationHelper);
		this.programmaDad = programmaDad;
	}

}
