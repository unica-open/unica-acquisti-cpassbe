/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.mepa;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ScaricoMepaTestataDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;

/**
 * Base class for ScaricoMepa services
 * @param <Q> the request type
 * @param <R> the response type
 */
public abstract class BaseScaricoMepaService<Q extends BaseRequest, R extends BaseResponse> extends BaseService<Q,R> {

	/** Data Access Delegate for testata MEPA */
	protected ScaricoMepaTestataDad scaricoMepaTestataDad;
	/** Data Access Delegate for decodifiche */
	protected DecodificaDad decodificaDad;

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param scaricoMepaTestataDad the scarico MEPA dad
	 */
	protected BaseScaricoMepaService(ConfigurationHelper configurationHelper,
			ScaricoMepaTestataDad scaricoMepaTestataDad,
			DecodificaDad decodificaDad) {
		super(configurationHelper);
		this.scaricoMepaTestataDad = scaricoMepaTestataDad;
		this.decodificaDad = decodificaDad;
	}
}
