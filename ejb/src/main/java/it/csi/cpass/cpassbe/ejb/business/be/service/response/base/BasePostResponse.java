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

import java.net.URI;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import it.csi.cpass.cpassbe.lib.dto.BaseDto;
import it.csi.cpass.cpassbe.lib.util.serialization.JsonUtility;

/**
 * Base response for POST methods
 * @param <K> the key type for the DTO
 * @param <D> the DTO type
 */
public abstract class BasePostResponse<K, D extends BaseDto<K>> extends BaseResponse {

	/**
	 * The base URI to use
	 * @return the base URI as string
	 */
	protected abstract String getBaseUri();
	/**
	 * The underlying entity
	 * @return the entity
	 */
	protected abstract D getEntity();

	@Override
	protected Response composeOwnResponse() {
		final String methodName = "composeOwnResponse";
		final String serialized = JsonUtility.serialize(getEntity());
		log.debug(methodName, "JSON response: " + serialized);
		return Response
				.status(Status.CREATED)
				.entity(serialized)
				.location(URI.create(getBaseUri() + "/" + getEntity().getId()))
				.build();
	}

}
