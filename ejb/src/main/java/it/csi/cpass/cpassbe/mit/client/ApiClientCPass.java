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
package it.csi.cpass.cpassbe.mit.client;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;

import com.squareup.okhttp.Response;

import it.csi.cpass.cpassbe.lib.util.log.LogUtil;

public class ApiClientCPass extends ApiClient {
	protected final LogUtil logs = new LogUtil(getClass());

	@Override
	public <T> T handleResponse(Response response, Type returnType) throws ApiException {
		if (response.isSuccessful()) {
			if (returnType == null || response.code() == 204) {
				// returning null if the returnType is not defined,
				// or the status code is 204 (No Content)
				return null;
			} else {
				return deserialize(response, returnType);
			}
		} else {
			//	            String respBody = null;
			//	            if (response.body() != null) {
			//	                try {
			//	                    respBody = response.body().string();
			//	                } catch (IOException e) {
			//	                    throw new ApiException(response.message(), e, response.code(), response.headers().toMultimap());
			//	                }
			//	            }
			//	          throw new ApiException(response.message(), response.code(), response.headers().toMultimap(), respBody);
			return deserialize(response, returnType);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T deserialize(Response response, Type returnType) throws ApiException {
		final String methodName = "deserialize";
		if (response == null || returnType == null) {
			return null;
		}

		if ("byte[]".equals(returnType.toString())) {
			// Handle binary response (byte array).
			try {
				return (T) response.body().bytes();
			} catch (final IOException e) {
				throw new ApiException(e);
			}
		} else if (returnType.equals(File.class)) {
			// Handle file downloading.
			return (T) downloadFileFromResponse(response);
		}

		String respBody;
		try {
			if (response.body() != null) {
				respBody = response.body().string();
			} else {
				respBody = null;
			}
		} catch (final IOException e) {
			throw new ApiException(e);
		}

		logs.info(methodName, "respBody: " + respBody);
		if (respBody == null || "".equals(respBody)) {
			return null;
		}

		String contentType = response.headers().get("Content-Type");
		if (contentType == null) {
			// ensuring a default content type
			contentType = "application/json";
		}
		if (isJsonMime(contentType)) {
			// return json.deserialize(respBody, returnType);
			return getJSON().deserialize(respBody, returnType);
		} else if (returnType.equals(String.class)) {
			// Expecting string, return the raw response body.
			return (T) respBody;
		} else {
			throw new ApiException(
					"Content type \"" + contentType + "\" is not supported for type: " + returnType,
					response.code(),
					response.headers().toMultimap(),
					respBody);
		}
	}

}
