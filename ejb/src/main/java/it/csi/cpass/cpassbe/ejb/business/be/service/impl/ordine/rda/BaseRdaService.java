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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.rda;


import it.csi.cpass.cpassbe.ejb.business.be.dad.RdaDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;

/**
 * Base class for services for /testataOrdine path
 * @param <Q> the request type
 * @param <R> the response type
 */
public abstract class BaseRdaService<Q extends BaseRequest, R extends BaseResponse> extends BaseService<Q, R> {

	/** Data Access Delegate for TestataOrdine */
	protected final RdaDad rdaDad;

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad the testataOrdine dad
	 */
	protected BaseRdaService(ConfigurationHelper configurationHelper, RdaDad rdaDad) {
		super(configurationHelper);
		this.rdaDad = rdaDad;
	}

}
