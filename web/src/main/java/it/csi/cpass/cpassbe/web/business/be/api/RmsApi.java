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

import java.util.List;
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

import it.csi.cpass.cpassbe.lib.dto.rms.RigaRms;
import it.csi.cpass.cpassbe.lib.dto.rms.TestataRms;
import it.csi.cpass.cpassbe.web.dto.RicercaRms;
import it.csi.cpass.cpassbe.web.dto.RicercaRmsDaSmistare;
import it.csi.cpass.cpassbe.web.util.annotation.LoadSettore;


/**
 * API interface for /rms path
 */
@Path("rms/testata")
@Produces({MediaType.APPLICATION_JSON})
public interface RmsApi {

	/**
	 * Gets a TestataRms by Id
	 *
	 * @param testataRmsId
	 * @param securityContext
	 * @param httpHeaders
	 * @param httpRequest
	 * @return
	 */
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getTestataRmsById(
		@PathParam("id") UUID testataRmsId,
		@Context SecurityContext securityContext,
		@Context HttpHeaders httpHeaders,
		@Context HttpServletRequest httpRequest);

	/**
	 *
	 * @param anno
	 * @param numero
	 * @param securityContext
	 * @param httpHeaders
	 * @param httpRequest
	 * @return
	 */
	@GET
	@Path("ricerca/{anno}/{numero}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response getRicercaRmsByAnnoENum(
			@PathParam("anno") Integer anno,
			@PathParam("numero") Integer numero,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Posts an TestataRms
	 * @param testataRms the  TestataRms to save
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response postTestataRms(
			TestataRms testataRms,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Puts an Rms by its id
	 * @param id the id
	 * @param testataRms the TestataRms to update
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@PUT
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response putTestataRmsById(
		@PathParam("id") UUID id,
		TestataRms testataRms,
		@Context SecurityContext securityContext,
		@Context HttpHeaders httpHeaders,
		@Context HttpServletRequest httpRequest);

	/**
	 * Deletes an Rms by its id
	 * @param id the id
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@DELETE
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response deleteTestataRmsById(
			@PathParam("id") UUID id,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("ricerca")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response getRicercaRms(
			@Min(0) @QueryParam("offset") Integer offset,
			@Min(1) @Max(100) @QueryParam("limit") Integer limit,
			@QueryParam("sort") @DefaultValue("") String sort,
			@QueryParam("direction") @DefaultValue("asc") String direction,
			RicercaRms ricercaRms,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);


	@POST
	@Path("riga-rms")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response postRigaRms(
		RigaRms rigaRms,
		@Context SecurityContext securityContext,
		@Context HttpHeaders httpHeaders,
		@Context HttpServletRequest httpRequest);

	@PUT
	@Path("riga-rms")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response putRigaRms(
		RigaRms rigaRms,
		@Context SecurityContext securityContext,
		@Context HttpHeaders httpHeaders,
		@Context HttpServletRequest httpRequest);
	/**
	 * Puts an Rms by its id
	 * @param id the id
	 * @param testataRms the TestataRms to update
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@PUT
	@Path("{id}/stato/{stato}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response putCambioStatoRms(
		@PathParam("id") UUID id,
		@PathParam("stato") String statoCode,
		TestataRms testataRms,
		@Context SecurityContext securityContext,
		@Context HttpHeaders httpHeaders,
		@Context HttpServletRequest httpRequest);

	@DELETE
	@Path("riga-rms/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response deleteRigaRms(
			@PathParam("id") UUID id,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@PUT
	@Path("riga-rms/cambio-stato/{stato}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response putCambioStatoRigaRms(
		@PathParam("stato") String statoCode,
		List<RigaRms> rigaRmsList,
		@Context SecurityContext securityContext,
		@Context HttpHeaders httpHeaders,
		@Context HttpServletRequest httpRequest);

	@POST
	@Path("ricerca-rms-da-smistare")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response getRicercaRmsDaSmistare(
			@Min(0) @QueryParam("offset") Integer offset,
			@Min(1) @Max(100) @QueryParam("limit") Integer limit,
			@QueryParam("sort") @DefaultValue("") String sort,
			@QueryParam("direction") @DefaultValue("asc") String direction,
			RicercaRmsDaSmistare ricercaRmsDaSmistare,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);


}
