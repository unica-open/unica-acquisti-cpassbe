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
import java.util.Map;
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

import it.csi.cpass.cpassbe.lib.dto.DocumentoSpesa;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.dto.ListinoFornitore;
import it.csi.cpass.cpassbe.lib.dto.MetadatiFunzione;
import it.csi.cpass.cpassbe.lib.dto.Ods;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.web.util.annotation.LoadSettore;

/**
 * API interface for /common path
 */
@Path("common")
@Produces({MediaType.APPLICATION_JSON})
public interface CommonApi {

	@GET
	@Path("ufficio/{id-settore}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getUfficiBySettore(
			@PathParam("id-settore") UUID settoreId,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("uffici/validi")
	@LoadSettore
	@Produces({MediaType.APPLICATION_JSON})
	public Response getUfficiValidiByEnte(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("settore/{id-ente}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getSettoreTreeByEnte(
			@PathParam("id-ente") UUID enteId,
			@QueryParam("cod-root") String codSettoreRadice,
			@QueryParam("validita") String validita,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("settore/ricerca")
	@Produces({MediaType.APPLICATION_JSON})
	public Response postRicercaSettore(
		@QueryParam("id-settore-radice") UUID idSettoreRadice,
		@Min(0) @QueryParam("offset") Integer offset,
		@Min(1) @Max(100) @QueryParam("limit") Integer limit,
		@QueryParam("sort") @DefaultValue("") String sort,
		@QueryParam("direction") @DefaultValue("asc") String direction,
		Settore settore,
		@Context SecurityContext securityContext,
		@Context HttpHeaders httpHeaders,
		@Context HttpServletRequest httpRequest);

	@POST
	@Path("settore/ricerca/indirizzi-settore")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response postRicercaSettoreIndirizzo(
		@Context SecurityContext securityContext,
		@Context HttpHeaders httpHeaders,
		@Context HttpServletRequest httpRequest,
		Settore settore);

	@POST
	@Path("ricerca/fornitore")
	@LoadSettore
	@Produces({MediaType.APPLICATION_JSON})
	public Response postRicercaFornitore(
			@Min(0) @QueryParam("offset") Integer offset,
			@Min(1) @Max(100) @QueryParam("limit") Integer limit,
			@QueryParam("sort") @DefaultValue("") String sort,
			@QueryParam("direction") @DefaultValue("asc") String direction,
			Fornitore fornitore,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("ricerca/documento-spesa")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response postRicercaDocumentoSpesa(
			DocumentoSpesa ds,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("ricerca/documento-spesa-ripartibile")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response postRicercaDocumentoSpesaRipartibile(
			DocumentoSpesa ds,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("ricerca/listino-fornitore")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response postRicercaListinoFornitore(
			@Min(0) @QueryParam("offset") Integer offset,
			@Min(1) @Max(100) @QueryParam("limit") Integer limit,
			@QueryParam("sort") @DefaultValue("") String sort,
			@QueryParam("direction") @DefaultValue("asc") String direction,
			ListinoFornitore listinoFornitore,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Posts an ListinoFornitore
	 * @param ListinoFornitore the ListinoFornitore to save
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@POST
	@Path("listino-fornitore")
	@LoadSettore
	@Produces({MediaType.APPLICATION_JSON})
	public Response postListinoFornitore(
			ListinoFornitore listinoFornitore,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("ricerca/fornitore-interno")
	@LoadSettore
	@Produces({MediaType.APPLICATION_JSON})
	public Response postRicercaFornitoreInterno(Fornitore fornitore, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest);

	@POST
	@Path("ricerca/elaborazione-programma")
	@LoadSettore
	@Produces({MediaType.APPLICATION_JSON})
	public Response postRicercaElaborazioneProgramma(Elaborazione elaborazione, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest);

	@GET
	@Path("metadati/{modulo}/{funzione}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response getMetadatiByModuoloFunzione(
			@PathParam("modulo") String modulo,
			@PathParam("funzione") String funzione,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("preferenze/save")
	@Produces({MediaType.APPLICATION_JSON})
	public Response postSavePreferenze(
			Map<String, Object> preferenze,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("settore/{id-settore}/parente/{id-settore-parent}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response isMySectorParent(
			@PathParam("id-settore") UUID idSettore,
			@PathParam("id-settore-parent") UUID idSettoreParent,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("settore-padre/{id-settore}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getMySectorFamily(
			@PathParam("id-settore") UUID idSettore,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);


	@POST
	@Path("ordinamento/{modulo}/funzione/{funzione}/tipo/{tipo}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getOrdinamentoByModuloFunzioneTipo (
			@PathParam("modulo") String modulo,
			@PathParam("funzione") String funzione,
			@PathParam("tipo") String tipo,
			List<MetadatiFunzione> listMetadatiFunzione,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("settore-precedente/{id-settore-precedente}/settore-attuale{id_settore-attuale}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response isSettoreRiorganizzato(
			@PathParam("id-settore-precedente") UUID idSettorePrecedente,
			@PathParam("id_settore-attuale") UUID idSettoreAttuale,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	@GET
	@Path("settore-emittente/{id-settore-emittente}")
	@LoadSettore
	@Produces({MediaType.APPLICATION_JSON})
	public Response checkCompatibilitaSettori(
			@PathParam("id-settore-emittente") UUID idSettoreEmittente,
			@QueryParam("codice-struttura-proponente") String codiceStrutturaProponente,
			@QueryParam("id-settore-determina") UUID idSettoreDetermina,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("parametro/ricerca/chiave/{chiave}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response getRicercaParametro(
			@PathParam("chiave") String chiave,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Deletes an Ente by its id
	 * @param id the id
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	/*
	@DELETE
	@Path("ente/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response deleteEnteById(
			@PathParam("id") UUID id,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
*/
	/**
	 * Posts an Ente
	 * @param utente the utente to save
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	/*
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	public Response postEnte(
			Ente utente,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
*/

	/**
	 * Puts an Ente by its id
	 * @param id the id
	 * @param utente the utente to update
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
/*
	@PUT
	@Path("ente/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response putEnteById(
			@PathParam("id") UUID id,
			Ente utente,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
*/

	/**
	 * Gets an Ente by its id
	 * @param id the id
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@GET
	@Path("ente/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getEnteById(
			@PathParam("id") UUID id,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("gestione-campi")
	@LoadSettore
	@Produces({MediaType.APPLICATION_JSON})
	public Response getGestioneCampi(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("oggetti-spesa/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response getOggettiSpesaById(
			@PathParam("id") Integer id,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	/**
	 *
	 * @param offset
	 * @param limit
	 * @param sort
	 * @param direction
	 * @param oggettiSpesa
	 * @param securityContext
	 * @param httpHeaders
	 * @param httpRequest
	 * @return
	 */
	@POST
	@Path("ricerca/oggetti-spesa")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response getRicercaOggettiSpesa(
			@Min(0) @QueryParam("offset") Integer offset,
			@Min(1) @Max(100) @QueryParam("limit") Integer limit,
			@QueryParam("sort") @DefaultValue("") String sort,
			@QueryParam("direction") @DefaultValue("asc") String direction,
			Ods oggettiSpesa,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	@POST
	@Path("inserisci/oggetti-spesa")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response postOggettiSpesa(
			Ods ods,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	@PUT
	@Path("modifica/oggetti-spesa")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response putOggettiSpesa(
			Ods ods,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	@DELETE
	@Path("cancella/oggetti-spesa/{id}/controllo/{controllo}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response delOggettiSpesa(
			@PathParam("id") Integer id,
			@PathParam("controllo") String controllo,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	@GET
	@Path("caricamento-massivo/carica-tabelle-xmit/{idProgramma}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response getCaricaTabelleDaTrasmettere(
			@PathParam("idProgramma") String idProgramma,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	@GET
	@Path("chiama-token/{numeroToken}/sec/{numSec}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getToken(
			@PathParam("numeroToken") Integer numeroToken,
			@PathParam("numSec") Integer numSec,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

}


