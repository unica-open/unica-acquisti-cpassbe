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

import it.csi.cpass.cpassbe.lib.dto.Utente;



/**
 * API interface for /utente path
 */
@Path("utente")
@Produces({MediaType.APPLICATION_JSON})
public interface UtenteApi {

	/**
	 * Deletes an Utente by its id
	 * @param id the id
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@DELETE
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON})
//	@PermissionCheck("STAMPA_INTERVENTO")
//	@PermissionCheck("INS_INTERVENTO")
	public Response deleteUtenteById(
			@PathParam("id") UUID id,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Gets the Interventi by the query params
	 * @param offset the offset
	 * @param limit the limit
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response getUtenti(
			@Min(0) @QueryParam("offset") Integer offset,
			@Min(1) @Max(100) @QueryParam("limit") Integer limit,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);


	/**
	 * Posts an Utente
	 * @param utente the utente to save
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	public Response postUtente(
			Utente utente,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);


	/**
	 * Puts an Utente by its id
	 * @param id the id
	 * @param utente the utente to update
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@PUT
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response putUtenteById(
			@PathParam("id") UUID id,
			Utente utente,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);


	/**
	 * Gets an Utente by its id
	 * @param id the id
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getUtenteById(
			@PathParam("id") UUID id,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	/**
	 * Gets the Utente
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@GET
	@Path("self")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getUtenteSelf(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Gets the Settore by Utente id
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@GET
	@Path("settore")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getSettoriByUtente(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Gets the Modulo by Settore id
	 * @param settoreId the id settore
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@GET
	@Path("settore/{id-settore}/modulo")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getModuloBySettore(
			@PathParam("id-settore") UUID settoreId,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Gets the permessi by Struttura id
	 * @param settoreId 
	 * @param idModulo the id modulo
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@GET
	@Path("settore/{id-settore}/modulo/{id-modulo}/permesso")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getPermessiBySettoreAndModulo(
			@PathParam("id-settore") UUID settoreId,
			@PathParam("id-modulo") Integer idModulo,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Get utente by Struttura id and Ruolo
	 * @param settoreId 
	 * @param ruoloCodice 
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@GET
	@Path("settore/{id-settore}/ruolo/{ruolo-codice}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getUtenteBySettoreRuolo(
			@PathParam("id-settore") UUID settoreId,
			@PathParam("ruolo-codice") String ruoloCodice,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	
	/**
	 * Get ruoli by settore
	 * @param settoreId
	 * @param securityContext
	 * @param httpHeaders
	 * @param httpRequest
	 * @return
	 */
	@GET
	@Path("/ruoli/settore/{id-settore}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getRuoliBySettore(
			@PathParam("id-settore") UUID settoreId,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	

	/**
	 * 
	 * @param utenteId
	 * @param securityContext
	 * @param httpHeaders
	 * @param httpRequest
	 * @return
	 */
	@GET
	@Path("/settori/ruoli/permessi")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getSettoriRuoliPermessiByUtente(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	/**
	 * Gets a list of Utente RUP by the intervento id
	 * @param id the intervento id
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@GET
	@Path("/rups/settore/{id-settore}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getRupsBySettoreId(
			@PathParam("id-settore") UUID idSettore,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	/**
	 * Gets a list of Utente RUP by the intervento id
	 * @param id the intervento id
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@GET
	@Path("/rup/delegato/{id-utente-delegato}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getUtenteRupDelegante(
			@PathParam("id-utente-delegato") UUID idUtenteDelegante,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	/**
	 * Gets a list of Settori by RUP id
	 * @param id the RUP id
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@GET
	@Path("/settore-rup/{id-rup}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getSettoriByRupId(
			@PathParam("id-rup") UUID idRup,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

}
