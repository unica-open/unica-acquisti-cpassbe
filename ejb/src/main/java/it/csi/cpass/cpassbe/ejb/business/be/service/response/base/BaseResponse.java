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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.util.log.LogUtil;
import it.csi.cpass.cpassbe.lib.util.serialization.JsonUtility;

/**
 * Base response class
 */
public abstract class BaseResponse {
	protected final LogUtil log = new LogUtil(getClass());

	/** The errors */
	protected List<ApiError> apiErrors = new ArrayList<>();
	/** The warnings */
	protected List<ApiError> apiWarnings = new ArrayList<>();

	/**
	 * @return the apiErrors
	 */
	public List<ApiError> getApiErrors() {
		return apiErrors;
	}

	/**
	 * @param apiErrors the apiErrors to set
	 */
	public void setApiErrors(List<ApiError> apiErrors) {
		this.apiErrors = apiErrors;
	}

	/**
	 * Adds an error
	 * @param apiError the error to add
	 */
	public void addApiError(ApiError apiError) {
		this.apiErrors.add(apiError);
	}

	/**
	 * @return the apiWarnings
	 */
	public List<ApiError> getApiWarnings() {
		return apiWarnings;
	}

	/**
	 * @param apiWarnings the apiWarnings to set
	 */
	public void setApiWarnings(List<ApiError> apiWarnings) {
		this.apiWarnings = apiWarnings;
	}

	/**
	 * Adds an error
	 * @param apiError the error to add
	 */
	public void addApiWarnings(ApiError apiWarnings) {
		this.apiWarnings.add(apiWarnings);
	}


	/**
	 * Adds the error messagess
	 * @param err the errors to add
	 */
	public void addApiErrors(Collection<ApiError> err) {
		this.apiErrors.addAll(err);
	}


	/**
	 * Adds the error messagess
	 * @param err the errors to add
	 */
	public void addApiWarnings(Collection<ApiError> warning) {
		this.apiWarnings.addAll(warning);
	}

	/**
	 * Composes the Response
	 * @return the response
	 */
	public Response composeResponse() {
		final String methodName = "composeResponse";
		if(apiErrors != null && !apiErrors.isEmpty()) {
			final String serialized = JsonUtility.serialize(apiErrors);
			log.trace(methodName, "JSON response: " + serialized);
			return Response
					.status(Status.BAD_REQUEST)
					.entity(serialized)
					.build();
		}
		apiErrors = null;
		return composeOwnResponse();
	}

	/**
	 * Compose own response
	 * @return the response
	 */
	protected abstract Response composeOwnResponse();

}
