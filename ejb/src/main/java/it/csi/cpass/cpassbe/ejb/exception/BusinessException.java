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
package it.csi.cpass.cpassbe.ejb.exception;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.ApplicationException;

import it.csi.cpass.cpassbe.lib.dto.ApiError;

/**
 * Exception signaling a failed business check
 */
@ApplicationException(rollback = true)
public class BusinessException extends ServiceException {

	/** For serialization */
	private static final long serialVersionUID = 4398533896440369883L;
	// private final ApiError error;
	private final List<ApiError> listApiError;

	/**
	 * @see RuntimeException#RuntimeException(String)
	 */
	public BusinessException(String message) {
		super(message);
		this.listApiError = null;
	}

	/**
	 * @see RuntimeException#RuntimeException(String, Throwable)
	 */
	public BusinessException(String message, Throwable cause) {
		super(message, cause);
		this.listApiError = null;
	}

	/**
	 * @param apiError the api error
	 * @see RuntimeException#RuntimeException(String)
	 */
	public BusinessException(ApiError apiError) {
		super(apiError.getFullErrorMessage());
		final List<ApiError> tmp = new ArrayList<>();
		tmp.add(apiError);
		this.listApiError = Collections.unmodifiableList(tmp);
	}
	/**
	 * @param message the message
	 * @param apiErrors the api errors
	 */
	public BusinessException(String message, List<ApiError> apiErrors) {
		super(message);
		this.listApiError = Collections.unmodifiableList(new ArrayList<>(apiErrors));
	}

	/**
	 * @param apiError the api error
	 * @param cause the cause
	 * @see RuntimeException#RuntimeException(String, Throwable)
	 */
	public BusinessException(ApiError apiError, Throwable cause) {
		super(apiError.getFullErrorMessage(), cause);
		final List<ApiError> tmp = new ArrayList<>();
		tmp.add(apiError);
		this.listApiError = Collections.unmodifiableList(tmp);
	}

	/**
	 * @param message the message
	 * @param apiError the api error
	 * @see RuntimeException#RuntimeException(String)
	 */
	public BusinessException(String message, ApiError apiError) {
		super(message);
		final List<ApiError> tmp = new ArrayList<>();
		tmp.add(apiError);
		this.listApiError = Collections.unmodifiableList(tmp);
	}

	/**
	 * @param message the message
	 * @param apiError the api error
	 * @param cause the cause
	 * @see RuntimeException#RuntimeException(String, Throwable)
	 */
	public BusinessException(String message, ApiError apiError, Throwable cause) {
		super(message, cause);
		final List<ApiError> tmp = new ArrayList<>();
		tmp.add(apiError);
		this.listApiError = Collections.unmodifiableList(tmp);
	}
	/**
	 * @param message the message
	 * @param apiErrors the api errors
	 * @param cause the cause
	 */
	public BusinessException(String message, List<ApiError> apiErrors, Throwable cause) {
		super(message, cause);
		this.listApiError = Collections.unmodifiableList(new ArrayList<>(apiErrors));
	}

	/**
	 * @return the error
	 */
	public List<ApiError> getErrors() {
		return listApiError;
	}

}
