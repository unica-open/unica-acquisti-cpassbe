/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - WAR submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.web.util.resolver;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Context resolver for the Jackson2 ObjectMapper, for reading JSON
 */
@Provider
public class ObjectMapperContextResolver implements ContextResolver<ObjectMapper> {

	private final ObjectMapper mapper;

	/** Default constructor */
	public ObjectMapperContextResolver() {
		mapper = new ObjectMapper()
				.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
				.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
				.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
				.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
				;
	}

	@Override
	public ObjectMapper getContext(Class<?> type) {
		return mapper;
	}

}
