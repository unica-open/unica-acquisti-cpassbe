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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.ente;

import it.csi.cpass.cpassbe.ejb.business.be.dad.EnteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;

/**
 * Base class for services for /utente path
 * @param <Q> the request type
 * @param <R> the response type
 */
public abstract class BaseEnteService<Q extends BaseRequest, R extends BaseResponse> extends BaseService<Q, R> {

	/** Data Access Delegate for utente */
	protected final EnteDad enteDad;

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param enteDad the ente dad
	 */
	protected BaseEnteService(ConfigurationHelper configurationHelper, EnteDad enteDad) {
		super(configurationHelper);
		this.enteDad = enteDad;
	}

}
