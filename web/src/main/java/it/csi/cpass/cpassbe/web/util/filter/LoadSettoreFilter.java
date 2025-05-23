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
package it.csi.cpass.cpassbe.web.util.filter;

import java.io.IOException;
import java.util.UUID;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.error.CoreError;
import it.csi.cpass.cpassbe.lib.util.log.LogUtil;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;
import it.csi.cpass.cpassbe.web.util.annotation.LoadSettore;

/**
 * Read the Settore by the existing header in the request
 * L'annotation deve stare nell'interfaccia sul metodo del servizio che utilizza il filtro
 */
@Provider
@LoadSettore
@Priority(Priorities.USER)
public class LoadSettoreFilter implements ContainerRequestFilter {

	/** Logger */
	private static final LogUtil LOG = new LogUtil(LoadSettoreFilter.class);
	private static final String SETTORE_HEADER = "X-SETTORE";

	@Inject private SettoreDad settoreDad;

	@Override
	@SuppressWarnings("resource")
	public void filter(ContainerRequestContext requestContext) throws IOException {
		final String methodName = "filter";
		String idSettore = requestContext.getHeaderString(SETTORE_HEADER);
		if(idSettore == null) {
			LOG.trace(methodName, "Null settore");
			requestContext.abortWith(Response.status(Status.BAD_REQUEST).entity(CoreError.REQUIRED_PARAMETER_OMITTED.getError("parameter", "settore")).build());
			return;
		}
		UUID uuidSettore;
		try {
			uuidSettore = UUID.fromString(idSettore);
		} catch(IllegalArgumentException iae) {
			LOG.debug(methodName, "Invalid UUID for settore: \"" + idSettore + "\"");
			requestContext.abortWith(Response.status(Status.BAD_REQUEST).entity(CoreError.REQUIRED_PARAMETER_OMITTED.getError("parameter", "settore")).build());
			return;
		}
		Settore settore = settoreDad.findOne(uuidSettore);
		if(settore == null) {
			LOG.debug(methodName, "Settore not found for UUID \"" + idSettore + "\"");
			requestContext.abortWith(Response.status(Status.BAD_REQUEST).entity(CoreError.UNMAPPED_ENTITY.getError("entity", "settore")).build());
			return;
		}
		LOG.trace(methodName, () -> "Loaded settore " + settore);
		CpassThreadLocalContainer.SETTORE_UTENTE.set(settore);
	}

}
