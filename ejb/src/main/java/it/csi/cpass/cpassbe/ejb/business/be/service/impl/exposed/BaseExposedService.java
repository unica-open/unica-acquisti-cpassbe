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

package it.csi.cpass.cpassbe.ejb.business.be.service.impl.exposed;

import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;

/**
 * Base service implementation for the exposed
 * @param <Q> the request type
 * @param <R> the response type
 */
public abstract class BaseExposedService<Q extends BaseRequest, R extends BaseResponse> extends BaseService<Q, R> {

	/** Data Access Delegate for exposed */

	/**
	 * Constructor
	 * @param configurationHelper the helper for the configuration
	 */
	protected BaseExposedService(ConfigurationHelper configurationHelper) {//, CommonDad commonDad) {
		super(configurationHelper);
	}
}
