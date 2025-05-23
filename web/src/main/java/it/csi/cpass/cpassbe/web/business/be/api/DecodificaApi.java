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

import it.csi.cpass.cpassbe.lib.dto.Ods;
import it.csi.cpass.cpassbe.web.util.annotation.LoadSettore;

/**
 * API interface for /decodifica path
 */
@Path("decodifica")
@Produces({MediaType.APPLICATION_JSON})
public interface DecodificaApi {

	/**
	 * Gets the Cpvs
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@GET
	@Path("cpv")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getCpv(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Gets the Cpvs as tree
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@GET
	@Path("cpv-tree")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getCpvTree(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Gets the Cpvs as tree
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@GET
	@Path("cpv-ods-tree")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getCpvOdsTree(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Gets the ModalitaAffidamentos
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@GET
	@Path("modalita-affidamento")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getModalitaAffidamento(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Gets the Nuts
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@GET
	@Path("nuts")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getNuts(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Gets the Prioritas
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@GET
	@Path("priorita")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getPriorita(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Gets the Risorsas
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@GET
	@Path("risorsa")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getRisorse(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Gets the RisorsaInterventis
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@GET
	@Path("settore-interventi")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getSettoreInterventi(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Gets the Ausa
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@GET
	@Path("ausa")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getAusas(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);


	/**
	 * Gets the RicompresoTipos
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@GET
	@Path("ricompreso-tipo")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getRicompresoTipos(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	// Acquisto variato
	/**
	 * Gets the Ricompresos
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@GET
	@Path("acquisto-variato")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getAcquistiVariati(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("tipo-ordine")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getTipoOrdine(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("tipo-ordine-no-type/{noTypeCode}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getListaTipoOrdineExcludeCode(
			@PathParam("noTypeCode") String noTypeCode,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("tipo-procedura-ord")
	@LoadSettore
	@Produces({MediaType.APPLICATION_JSON})
	public Response getTipoProceduraOrd(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("tipo-procedura-pba")
	@LoadSettore
	@Produces({MediaType.APPLICATION_JSON})
	public Response getTipoProceduraPba(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("aliquote-iva")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getAliquoteIva(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("unita-misura")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getUnitaMisura(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);



	/**
	 *
	 * @param offset
	 * @param limit
	 * @param sort
	 * @param direction
	 * @param ods
	 * @param securityContext
	 * @param httpHeaders
	 * @param httpRequest
	 * @return
	 */
	@POST
	@Path("ricerca/cpv-oggetti-spesa")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response getRicercaCpvOggettiSpesa(
			@Min(0) @QueryParam("offset") Integer offset,
			@Min(1) @Max(100) @QueryParam("limit") Integer limit,
			@QueryParam("sort") @DefaultValue("") String sort,
			@QueryParam("direction") @DefaultValue("asc") String direction,
			Ods ods,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("stato/{tipo}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getStatoByTipo(
			@PathParam("tipo") String tipo,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("stato-nso/{tipo}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getStatoNsoByTipo(
			@PathParam("tipo") String tipo,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("causale-sospensione-evasione-valide")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getAllCausaleSospensioneEvasioneValide(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("tipo-evasione")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getTipoEvasione(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Gets the Ausa
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@GET
	@Path("tipo-acquisto")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getTipoAcquistos(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("tipo-provvedimento")
	@LoadSettore
	@Produces({MediaType.APPLICATION_JSON})
	public Response getProvvedimentoTipo(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("motivi-esclusione-cig")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getMotiviEsclusioneCig(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("tipo-settore")
	@LoadSettore
	@Produces({MediaType.APPLICATION_JSON})
	public Response getTipoSettore(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
}
