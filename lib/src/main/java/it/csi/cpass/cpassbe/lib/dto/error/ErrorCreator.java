/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto.error;

import java.util.HashMap;
import java.util.Map;

import it.csi.cpass.cpassbe.lib.dto.ApiError;

/**
 * An error creator
 */
public interface ErrorCreator {

	/** Prefix for grouped properties */
	public static final String GROUP = "group.";

	/**
	 * @return the code
	 */
	public String getCode();
	/**
	 * @return the code
	 */
	public String getGroup();
	/**
	 * @return the message
	 */
	public String getMessage();

	/**
	 * @return the type
	 */
	public String getType();

	/**
	 * Instantiates the ApiError
	 * @param params the parameters
	 * @return the error message
	 */
	default ApiError getError(Object... params) {
		// params deve avere numero pari di dati, nella forma <stringa>, <valore>
		// TODO: lanciare eccezione?
		Map<String, Object> paramsMap = new HashMap<>();
		Map<String, Object> groupParamsMap = new HashMap<>();

		for(int i = 0; i < params.length; i += 2) {
			String paramName = (String) params[i];
			if (paramName.startsWith(GROUP)) {
				groupParamsMap.put(paramName.substring(GROUP.length()), params[i + 1]);
			} else {
				paramsMap.put(paramName, params[i + 1]);
			}
		}

		ApiError apiError = new ApiError();
		apiError.setCode(getCode());
		apiError.setType(getType());
		apiError.setGroup(getGroup());
		apiError.setErrorMessage(getMessage());
		apiError.setParams(paramsMap);
		apiError.setGroupParams(groupParamsMap);
		return apiError;
	}
}
