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
package it.csi.cpass.cpassbe.ejb.business.be.facade;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseResponse;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.ejb.util.mail.MailHelper;
import it.csi.cpass.cpassbe.lib.dto.BaseDto;
import it.csi.cpass.cpassbe.lib.util.log.LogUtil;

/**
 * Base fa&ccedil;ade class
 */
public abstract class BaseFacade {

	/** Configuration helper */
	@EJB protected ConfigurationHelper configurationHelper;
	/** The mail helper */
	@EJB protected MailHelper mailHelper;
	/** Lookup helper for external services */
	@Inject protected ExternalHelperLookup externalHelperLookup;
	/** Session context */
	@Resource private SessionContext sessionContext;
	/** Logger */
	protected final LogUtil log = new LogUtil(getClass());

	/**
	 * Executes the service
	 * @param <Q> the request type
	 * @param <R> the response type
	 * @param request the request
	 * @param service the service
	 * @return the response
	 * @deprecated use the normal version so as to return EJB data from the EJB component
	 */
	@Deprecated
	protected <Q extends BaseRequest, R extends BaseResponse> Response executeServiceWithResponse(Q request, BaseService<Q, R> service) {
		final R res = executeService(request, service);
		return res.composeResponse();
	}

	/**
	 * Executes the service
	 * @param <Q> the request type
	 * @param <R> the response type
	 * @param request the request
	 * @param service the service
	 * @return the response
	 */
	protected <Q extends BaseRequest, R extends BaseResponse> R executeService(Q request, BaseService<Q, R> service) {
		return service.execute(request);
	}

	/**
	 * Sets the id in the model and returns it
	 * @param <M> the model type
	 * @param id the id
	 * @param model the model
	 * @return the model
	 */
	protected <K, M extends BaseDto<K>> M setId(K id, M model) {
		if(model != null) {
			model.setId(id);
		}
		return model;
	}

}
