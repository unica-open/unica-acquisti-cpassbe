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
package it.csi.cpass.cpassbe.ejb.util.exception;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import it.csi.cpass.cpassbe.lib.dto.ApiError;

/**
 * Marks a class as being aware of a BusinessException check
 */
public interface BusinessExceptionAware {

	/**
	 * Checks for business condition
	 * @param condition the condition to check
	 * @param message the message to supply to the exception
	 */
	void checkBusinessCondition(boolean condition, ApiError message);
	/**
	 * Checks for business condition
	 * @param condition the condition to check
	 * @param message the message to supply to the exception
	 * @param throwException whether to throw an exception
	 */
	void checkBusinessCondition(boolean condition, ApiError message, boolean throwException);
	/**
	 * Checks for business condition
	 * @param condition supplier for the condition to check
	 * @param message the message to supply to the exception
	 */
	void checkBusinessCondition(BooleanSupplier condition, ApiError message);

	/**
	 * Checks for business condition
	 * @param condition the condition to check
	 * @param message the message to supply to the exception
	 */
	void checkBusinessCondition(boolean condition, Supplier<ApiError> message);
	/**
	 * Checks for business condition
	 * @param condition supplier for the condition to check
	 * @param message the message to supply to the exception
	 */
	void checkBusinessCondition(BooleanSupplier condition, Supplier<ApiError> message);

	void generaException(ApiError message);
}
