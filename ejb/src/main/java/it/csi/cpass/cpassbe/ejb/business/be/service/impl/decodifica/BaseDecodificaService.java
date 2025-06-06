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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.decodifica;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;

/**
 * Base service implementation for the decodifica
 * @param <Q> the request type
 * @param <R> the response type
 */
public abstract class BaseDecodificaService<Q extends BaseRequest, R extends BaseResponse> extends BaseService<Q, R> {

	/** Data Access Delegate for decodifica */
	protected final DecodificaDad decodificaDad;

	/**
	 * Constructor
	 * @param configurationHelper the helper for the configuration
	 * @param decodificaDad the DAD for the decodifica
	 */
	protected BaseDecodificaService(ConfigurationHelper configurationHelper, DecodificaDad decodificaDad) {
		super(configurationHelper);
		this.decodificaDad = decodificaDad;
	}

}
