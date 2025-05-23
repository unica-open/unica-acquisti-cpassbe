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

import it.csi.cpass.cpassbe.lib.dto.ord.rda.RigaRda;
import it.csi.cpass.cpassbe.lib.dto.ord.rda.TestataRda;
import it.csi.cpass.cpassbe.web.dto.RicercaRda;
import it.csi.cpass.cpassbe.web.dto.RicercaRmsDaEvadere;
import it.csi.cpass.cpassbe.web.util.annotation.LoadSettore;

/**
 * API interface for /rda path
 */
@Path("rda")
@Produces({MediaType.APPLICATION_JSON})
public interface RdaApi {

	@POST
	@Path("ricerca/rms-da-evadere")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	Response getRicercaRmsDaEvadere (
		@Min(0) @QueryParam("offset") Integer offset,
		@Min(1) @Max(100) @QueryParam("limit") Integer limit,
		@QueryParam("sort") @DefaultValue("") String sort,
		@QueryParam("direction") @DefaultValue("asc") String direction,
		RicercaRmsDaEvadere ricercaRmsDaEvadere,
		@Context SecurityContext securityContext,
		@Context HttpHeaders httpHeaders,
		@Context HttpServletRequest httpRequest);

	/**
	 * Gets an TestataRda by its id
	 * @param id the id
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@GET
	@Path("testata/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response getTestataRdaById(
			@PathParam("id") UUID id,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);


	@GET
	@Path("testata-rda/ricerca/{anno}/{numero}/{idEnte}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response getRicercaTestataRdaByAnnoENum(
			@PathParam("anno") Integer anno,
			@PathParam("numero") Integer numero,
			@PathParam("idEnte") UUID enteId,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("ricerca")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response getRicercaRda(
			@Min(0) @QueryParam("offset") Integer offset,
			@Min(1) @Max(100) @QueryParam("limit") Integer limit,
			@QueryParam("sort") @DefaultValue("") String sort,
			@QueryParam("direction") @DefaultValue("asc") String direction,
			RicercaRda ricercaRda,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Posts an Rda
	 * @param rda the rda to save
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response postTestataRda(
			TestataRda testataRda,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@PUT
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response putTestataRdaById(
		@PathParam("id") UUID id,
		TestataRda testataRda,
		@Context SecurityContext securityContext,
		@Context HttpHeaders httpHeaders,
		@Context HttpServletRequest httpRequest);

	@PUT
	@Path("riga-rda/check/{ods-confermato}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response putRigaRda(
		@PathParam("ods-confermato") Boolean checkOdsConfermato,
		RigaRda rigaRda,
		@Context SecurityContext securityContext,
		@Context HttpHeaders httpHeaders,
		@Context HttpServletRequest httpRequest);

	/**
	 * Delete a TestataOrdine by its id
	 * @param id the id
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@DELETE
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response deleteTestataRdaById(
			@PathParam("id") UUID id,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Delete a TestataOrdine by its id
	 * @param id the id
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@DELETE
	@Path("riga-rda/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response deleteRigaRdaById(
			@PathParam("id") UUID id,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@PUT
	@Path("testata-rda/{id}/stato/{statoCodice}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response putCambioStatoRdaRda(
		@PathParam("id") UUID id,
		@PathParam("statoCodice") String statoCodice,
		//TestataRda testataRda,
		@Context SecurityContext securityContext,
		@Context HttpHeaders httpHeaders,
		@Context HttpServletRequest httpRequest);

}
