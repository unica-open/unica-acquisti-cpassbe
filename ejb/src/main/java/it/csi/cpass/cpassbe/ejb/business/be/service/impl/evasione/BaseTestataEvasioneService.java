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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.evasione;

import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;

/**
 * Base class for services for /testataEvasione path
 *
 * @param <Q> the request type
 * @param <R> the response type
 */
public abstract class BaseTestataEvasioneService<Q extends BaseRequest, R extends BaseResponse> extends BaseService<Q, R> {

	/** Data Access Delegate for TestataEvasione */
	protected final TestataEvasioneDad testataEvasioneDad;

	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 * @param testataEvasioneDad  the testataEvasione dad
	 */
	protected BaseTestataEvasioneService(ConfigurationHelper configurationHelper, TestataEvasioneDad testataEvasioneDad) {
		super(configurationHelper);
		this.testataEvasioneDad = testataEvasioneDad;
	}

}
