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
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import org.apache.commons.lang3.StringUtils;

import it.csi.cpass.cpassbe.ejb.business.be.facade.UtenteFacade;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetPermessiBySettoreResponse;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.util.log.LogUtil;
import it.csi.cpass.cpassbe.lib.util.serialization.JsonUtility;
import it.csi.cpass.cpassbe.web.util.annotation.PermissionCheck;
import it.csi.cpass.cpassbe.web.util.annotation.PermissionChecks;
import it.csi.cpass.cpassbe.web.util.filter.exception.UnsupportedApiCallException;

/**
 * The filter for the authentications
 * @see <a href="https://stackoverflow.com/a/26778123/2850367">https://stackoverflow.com/a/26778123/2850367</a>
 */
@PermissionChecks
@Provider
@Priority(Priorities.AUTHENTICATION)
public class PermissionCheckFilter implements ContainerRequestFilter {

	private static final LogUtil LOG = new LogUtil(PermissionCheckFilter.class);
	private static final String SETTORE_HEADER = "X-SETTORE";

	/** The resource info */
	@Context private ResourceInfo resourceInfo;
	/** The auth DAD */
	@Inject private UtenteFacade utenteFacade;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		try {
			// Validate the token
			doFilter(requestContext);
		} catch (UnsupportedApiCallException e) {
			abortWithNotAllowed(requestContext, e);
		} catch (Exception e) {
			abortWithInternalServerError(requestContext, e);
		}
	}

	/**
	 * Executes the filtering
	 * @param requestContext the request context
	 */
	private void doFilter(ContainerRequestContext requestContext) {
		final String methodName = "doFilter";
		PermissionChecks securedAnnotation = resourceInfo.getResourceMethod().getAnnotation(PermissionChecks.class);
		if(securedAnnotation == null) {
			// Non secured, simply exit
			return;
		}
		PermissionCheck[] requiredPermissionsArray = securedAnnotation.value();
		if(requiredPermissionsArray.length == 0) {
			LOG.warn(methodName, "Security validation invoked on method declaring NO permission");
			// Should NEVER happen
			return;
		}
		List<String> requiredPermissions = Arrays.stream(requiredPermissionsArray).map(PermissionCheck::value).collect(Collectors.toList());
		UUID idSettore = getUuidSettore(requestContext);
		GetPermessiBySettoreResponse response = utenteFacade.getPermessiBySettore(idSettore);
		if(!response.getApiErrors().isEmpty()) {
			throw new RuntimeException("Error in Permessi retrieval: " + response.getApiErrors().stream().map(ApiError::getFullErrorMessage).collect(Collectors.joining("; ")));
		}
		boolean matchPermission = response.getPermessi()
				.stream()
				.anyMatch(p -> requiredPermissions.contains(p.getCodice()));
		if(!matchPermission) {
			LOG.error(methodName, "Authorization not granted for URI \"" + requestContext.getUriInfo().getPath() + ". Requested permissions: " + requiredPermissions);
			throw new UnsupportedApiCallException("Unsupported method");
		}
	}

	private UUID getUuidSettore(ContainerRequestContext requestContext) {
		String idSettore = requestContext.getHeaderString(SETTORE_HEADER);
		if(StringUtils.isBlank(idSettore)) {
			throw new UnsupportedApiCallException("Header " + SETTORE_HEADER + " not set");
		}
		try {
			return UUID.fromString(idSettore);
		} catch(IllegalArgumentException iae) {
			throw new UnsupportedApiCallException("Invalid UUID for settore: \"" + idSettore + "\"");
		}
	}
	/**
	 * <p>Abort the filter chain with a 405 status code response.
	 * @param requestContext the request context
	 */
	@SuppressWarnings("resource")
	private void abortWithNotAllowed(ContainerRequestContext requestContext, Exception e) {
		LOG.trace("abortWithNotAllowed", "Not allowed in authentication handling: " + e.getMessage());
		// Abort the filter chain with a 405 status code response
		requestContext.abortWith(
			Response.status(Response.Status.UNAUTHORIZED)
				.build());
	}

	/**
	 * <p>Abort the filter chain with a 500 status code response.
	 * @param requestContext the request context
	 * @param e the exception
	 */
	@SuppressWarnings("resource")
	private void abortWithInternalServerError(ContainerRequestContext requestContext, Exception e) {
		// Abort the filter chain with a 500 status code response
		LOG.error("abortWithInternalServerError", "Internal server error in authentication handling: " + e.getMessage(), e);

		requestContext.abortWith(
			Response
				.status(Status.INTERNAL_SERVER_ERROR)
				.type(MediaType.APPLICATION_JSON)
				.entity(JsonUtility.parseThrowable(e, false).toString())
				.build());
	}

}
