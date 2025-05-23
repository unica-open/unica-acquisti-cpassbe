/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - WAR submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.web.business.be.api;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import it.csi.cpass.cpassbe.lib.dto.custom.PostOrdineMepaBodyWrapper;
import it.csi.cpass.cpassbe.web.dto.WebScaricoMepaFileHolder;
import it.csi.cpass.cpassbe.web.util.annotation.LoadSettore;


@Path("ord/scarico-mepa")
@Produces({MediaType.APPLICATION_JSON})
public interface ScaricoMepaApi {

	/**
	 * Ricerca tutti gli ordini MEPA in stato DA CARICARE
	 * in base al settore dell'utente
	 *
	 * @return lista testate ordine da caricare
	 */
	@GET
	@Path("find")
	@Produces(MediaType.APPLICATION_JSON)
	@LoadSettore
	Response getOrdiniMepaDaCaricare(@Min(0) @QueryParam("offset") Integer offset,
											@Min(1) @Max(100) @QueryParam("limit") Integer limit,
											@QueryParam("sort") @DefaultValue("") String sort,
											@QueryParam("direction") @DefaultValue("asc") String direction,
											@Context SecurityContext securityContext,
											@Context HttpHeaders httpHeaders,
											@Context HttpServletRequest httpRequest);

	/**
	 * Ricerca un ordine MEPA per id
	 *
	 * @return testata ordine mepa da caricare
	 */
	@GET
	@Path("find/{idTestataMepa}")
	@Produces(MediaType.APPLICATION_JSON)
	Response getTestataMepaById(@PathParam("idTestataMepa") Integer idTestataMepa,
									   @Context SecurityContext securityContext,
									   @Context HttpHeaders httpHeaders,
									   @Context HttpServletRequest httpRequest);

	@POST
	@Path("upload/xml")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@LoadSettore
	Response postUploadScaricoMepaXml(
			@MultipartForm WebScaricoMepaFileHolder fileHolder,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("verifica-fornitore/{id}")
	@LoadSettore
	Response getVerificaFornitore(@PathParam("id") String sellersupplierpartyEndpointId,
										 @Context SecurityContext securityContext,
										 @Context HttpHeaders httpHeaders,
										 @Context HttpServletRequest httpRequest);

	@POST
	@Path("insert-ordine")
	@Produces(MediaType.APPLICATION_JSON)
	@LoadSettore
	Response postOrdineMepa(PostOrdineMepaBodyWrapper postOrdineMepaBodyWrapper,
							@Context SecurityContext securityContext,
							@Context HttpHeaders httpHeaders,
							@Context HttpServletRequest httpRequest);

	/**
	 * Rimuove un ordine MEPA con tutti i corrispondenti record dalle tabelle:
	 * cpassTScaricoMepaXml, cpassTScaricoMepaSconti, cpassTScaricoMepaRiga e cpassTScaricoMepaTestata
	 * @param idTestataMepa
	 * @param securityContext
	 * @param httpHeaders
	 * @param httpRequest
	 * @return id della testata cancellata
	 */
	@DELETE
	@Path("{idTestataMepa}")
	Response deleteOrdineMepa(@PathParam("idTestataMepa") Integer idTestataMepa,
							  @Context SecurityContext securityContext,
							  @Context HttpHeaders httpHeaders,
							  @Context HttpServletRequest httpRequest);

}
