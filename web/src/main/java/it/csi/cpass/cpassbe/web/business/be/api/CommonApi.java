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

import it.csi.cpass.cpassbe.lib.dto.DocumentoSpesa;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.dto.ListinoFornitore;
import it.csi.cpass.cpassbe.lib.dto.MetadatiFunzione;
import it.csi.cpass.cpassbe.lib.dto.Settore;

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
	@Path("settore/{id-ente}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getSettoreTreeByEnte(
			@PathParam("id-ente") UUID enteId,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	

	@POST
	@Path("settore/ricerca")
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

	@POST
	@Path("settore/ricerca/indirizzi-settore")
	@Produces({MediaType.APPLICATION_JSON})
	public Response postRicercaSettoreIndirizzo(
		@Context SecurityContext securityContext,
		@Context HttpHeaders httpHeaders,
		@Context HttpServletRequest httpRequest,
		Settore settore);
	
	@POST
	@Path("ricerca/fornitore")
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
	public Response postRicercaDocumentoSpesa(
			DocumentoSpesa ds,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("ricerca/documento-spesa-ripartibile")
	@Produces({MediaType.APPLICATION_JSON})
	public Response postRicercaDocumentoSpesaRipartibile(
			DocumentoSpesa ds,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@POST
	@Path("ricerca/listino-fornitore")
	@Produces({MediaType.APPLICATION_JSON})
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
	@Produces({MediaType.APPLICATION_JSON})
	public Response postListinoFornitore(
			ListinoFornitore listinoFornitore,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@POST
	@Path("ricerca/fornitore-interno")
	@Produces({MediaType.APPLICATION_JSON})
	public Response postRicercaFornitoreInterno(Fornitore fornitore, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest);
	
	@POST
	@Path("ricerca/elaborazione-programma")
	@Produces({MediaType.APPLICATION_JSON})
	public Response postRicercaElaborazioneProgramma(Elaborazione elaborazione, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest);
	
	@GET
	@Path("metadati/{modulo}/{funzione}")
	@Produces({MediaType.APPLICATION_JSON})
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

}
