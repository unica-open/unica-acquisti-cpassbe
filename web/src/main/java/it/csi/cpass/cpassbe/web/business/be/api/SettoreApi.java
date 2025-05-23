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
/**********************************************
 * CSI PIEMONTE
 **********************************************/
package it.csi.cpass.cpassbe.web.business.be.api;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.web.util.annotation.LoadSettore;

/**
 * API interface for /common path
 */
@Path("bo/settore")
@Produces({MediaType.APPLICATION_JSON})
public interface SettoreApi {

	@GET
	@Path("{id-settore}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getSettoreById(
			@PathParam("id-settore") UUID settoreId,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	@GET
	@Path("sezioni/settore/{id-settore}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getSezioniBySettoreById(
			@PathParam("id-settore") UUID settoreId,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@DELETE
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response deleteSettoreById(
			@PathParam("id") UUID id,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@PUT
	@Path("{id}")
	@LoadSettore
	@Produces({MediaType.APPLICATION_JSON})
	public Response putSettore(
			@PathParam("id") UUID id,
			Settore settore,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@LoadSettore
	@Produces({MediaType.APPLICATION_JSON})
	public Response postSettore(
			Settore settore,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("ricerca")
	@LoadSettore
	@Produces({MediaType.APPLICATION_JSON})
	public Response postRicercaSettore(
		@Min(0) @QueryParam("offset") Integer offset,
		@Min(1) @Max(100) @QueryParam("limit") Integer limit,
		@QueryParam("sort") @DefaultValue("") String sort,
		@QueryParam("direction") @DefaultValue("asc") String direction,
		Settore settore,
		@Context SecurityContext securityContext,
		@Context HttpHeaders httpHeaders,
		@Context HttpServletRequest httpRequest);

	@GET
	@Path("cdc")
	@LoadSettore
	@Produces({MediaType.APPLICATION_JSON})
	public Response getCdcValidi(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("cdc/settore/{id-settore}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getCdcBySettore(
			@PathParam("id-settore") UUID settoreId,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

}
