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
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.cpass.cpassbe.lib.dto.pba.Programma;

/**
 * API interface for /pba/programma path
 */
@Path("pba/programma")
@Produces({MediaType.APPLICATION_JSON})
public interface ProgrammaApi {

	/**
	 * Deletes an Programma by its id
	 * @param id the id
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@DELETE
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response deleteProgrammaById(
			@PathParam("id") UUID id,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);


	/**
	 * Gets an Programma by its id
	 * @param id the id
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getProgrammaById(
			@PathParam("id") UUID id,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	/**
	 * Gets the permessi by Struttura id
	 * @param settoreId 
	 * @param anno 
	 * @param versione 
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@GET
	@Path("settore/{id-settore}/anno/{anno}/versione/{versione}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getProgrammiBySettoreAnnoVersione(
			@PathParam("id-settore") UUID settoreId,
			@PathParam("anno") Integer anno,
			@PathParam("versione") Integer versione,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);


	/**
	 * Gets the Programmi by Settore id
	 * @param settoreId 
	 * @param validi 
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@GET
	@Path("settore/{id-settore}/validi/{validi}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getProgrammiBySettore(
			@PathParam("id-settore") UUID settoreId,
			@PathParam("validi") Boolean validi,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Gets the Programmi by Settore id
	 * @param settoreId 
	 * @param statoCode 
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@GET
	@Path("settore/{id-settore}/stato/{stato-code}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getProgrammiBySettoreAndStato(
			@PathParam("id-settore") UUID settoreId,
			@PathParam("stato-code") String statoCode,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	
	/**
	 * Posts an Programma
	 * @param programma the programma to save
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	public Response postProgramma(
			Programma programma,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Posts an Programma to copy
	 * @param programma the programma to copy
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@POST
	@Path("copia/{soloControlli}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response postProgrammaCopia(
			@PathParam("soloControlli") Boolean soloControlli,
			Programma programma,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Puts an Programma by its id
	 * @param id the id
	 * @param programma the programma to update
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@PUT
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response putProgrammaById(
			@PathParam("id") UUID id,
			Programma programma,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	/**
	 * Puts an stato Programma by its id
	 * @param id the id
	 * @param programma the programma to update
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@PUT
	@Path("stato/annullato/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response putProgrammaStatoAnnullatoById(
			@PathParam("id") UUID id,
			//@PathParam("id-settore") UUID settoreId,
			Programma programma,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Puts an stato Programma by its id
	 * @param id the id
	 * @param programma the programma to update
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@PUT
	@Path("stato/confermato/{id}/{ignoreWarning}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response putProgrammaStatoConfermatoById(
			@PathParam("id") UUID id,
			@PathParam("ignoreWarning") Boolean ignoreWarning,
			Programma programma,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	/**
	 * Puts an stato Programma by its id
	 * @param id the id
	 * @param programma the programma to update
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@PUT
	@Path("stato/riporta-in-bozza/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response putProgrammaStatoRiportaInBozzaById(
			@PathParam("id") UUID id,
			Programma programma,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	/**
	 * Gets the Programmi by Settore id
	 * @param settoreId 
	 * @param statoCode the id statoCode
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@GET
	@Path("settore/{id-settore}/stato/{stato-code}/recenti")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getUltimiProgrammiBySettoreAndStato(
			@PathParam("id-settore") UUID settoreId,
			@PathParam("stato-code") String statoCode,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	/**
	 * Gets the Programmi che si possono trasmettere al ministero
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@GET
	@Path("trasmissione-mit")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getProgrammiTrasmissioneMIT(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	/**
	 * Per trasmettere un programma al ministero
	 * @param idProgramma 
	 * @param idUtente 
	 * @param modalita-invio 
	 * @param securityContext
	 * @param httpHeaders
	 * @param httpRequest
	 * @return the response
	 */
	@PUT
	@Path("trasmetti/{id-programma}/{id-utente}/{modalita-invio}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response putTrasmettiProgrammaById(
			@PathParam("id-programma") UUID idProgramma,
			@PathParam("id-utente") UUID idUtente,
			@PathParam("modalita-invio") String modalitaInvio,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
}
