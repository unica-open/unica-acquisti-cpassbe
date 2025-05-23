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

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import it.csi.cpass.cpassbe.lib.dto.ModuloRuoloPermesso;
import it.csi.cpass.cpassbe.lib.dto.Notifica;
import it.csi.cpass.cpassbe.lib.dto.RuoloPermesso;
import it.csi.cpass.cpassbe.lib.dto.custom.SmistamentoManualeRms;
import it.csi.cpass.cpassbe.web.dto.WebAggiornamentoOdsFileHolder;
import it.csi.cpass.cpassbe.web.dto.WebSmistamentoRmsFileHolder;
import it.csi.cpass.cpassbe.web.util.annotation.LoadSettore;


/**
 * API interface for /rms path
 */
@Path("bo")
@Produces({MediaType.APPLICATION_JSON})
public interface BackOfficeApi {

	/**
	 * Posts the upload of a CSV
	 * @param fileHolder the file holder
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@POST
	@Path("upload/regole-smistamento-rms/csv")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response postUploadCsvRegoleSmistamentoRms(
			@MultipartForm WebSmistamentoRmsFileHolder fileHolder,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("upload/aggiornamento-ods/csv")
	@LoadSettore
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response postUploadCsvAggiornamentoOds(
			@MultipartForm WebAggiornamentoOdsFileHolder fileHolder,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@PUT
	@Path("smistamento-manuale-rms")
	@Produces({MediaType.APPLICATION_JSON})
	public Response putSmistamentoManualeRms(
			SmistamentoManualeRms smistamentoManualeRms,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@PUT
	@Path("riattiva-funzioni-gestione")
	@LoadSettore
	@Produces({MediaType.APPLICATION_JSON})
	public Response putRiattivazioneFunzioniGestione(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@PUT
	@Path("disattiva-funzioni-gestione")
	@LoadSettore
	@Produces({MediaType.APPLICATION_JSON})
	public Response putDisattivaFunzioniGestione(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@PUT
	@Path("cancella-ordini-bozza")
	@LoadSettore
	@Produces({MediaType.APPLICATION_JSON})
	public Response putCancellaOrdiniBozza(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@PUT
	@Path("annulla-ordini-confermati")
	@LoadSettore
	@Produces({MediaType.APPLICATION_JSON})
	public Response putAnnullaOrdiniConfermati(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("inserimento-avvisi")
	@Produces({MediaType.APPLICATION_JSON})
	public Response postAvviso(
			Notifica notifica,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("gestione-permessi")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getPermessiDaGestire(
			@Min(0) @QueryParam("offset") Integer offset,
			@Min(1) @Max(100) @QueryParam("limit") Integer limit,
			@QueryParam("sort") @DefaultValue("") String sort,
			@QueryParam("direction") @DefaultValue("asc") String direction,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@PUT
	@Path("gestione-permessi/modifica-attivi")
	@Produces({MediaType.APPLICATION_JSON})
	public Response putGestionePermessiAttivi(
			List<RuoloPermesso> ruoloPermessoAttivi,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@PUT
	@Path("gestione-permessi/modifica-non-attivi")
	@Produces({MediaType.APPLICATION_JSON})
	public Response putGestionePermessiNonAttivi(
			List<ModuloRuoloPermesso> moduloRuoloPermessoNonAttivi,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);


	@GET
	@Path("gestione-permessi-non-attivi")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getPermessiDaGestireNonAttivi(
			@Min(0) @QueryParam("offset") Integer offset,
			@Min(1) @Max(100) @QueryParam("limit") Integer limit,
			@QueryParam("sort") @DefaultValue("") String sort,
			@QueryParam("direction") @DefaultValue("asc") String direction,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("gestione-permessi-list-attivi")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getGestioneRuoloPermessoList(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("gestione-permessi-list-non-attivi")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getGestioneRuoloPermessoNonAttiviList(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
}
