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

import it.csi.cpass.cpassbe.lib.dto.ord.evasione.ControllaEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DestinatarioEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DocumentoTrasporto;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.RigaEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.SalvaEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.SalvaImpegniEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.TestataEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.custom.RicercaDdt;
import it.csi.cpass.cpassbe.lib.exposed.dto.Evasione;
import it.csi.cpass.cpassbe.web.dto.RicercaEvasioni;
import it.csi.cpass.cpassbe.web.util.annotation.LoadSettore;

@Path("ord/evasione")
@Produces({MediaType.APPLICATION_JSON})
public interface EvasioneApi {

	@GET
	@Path("ordine/{anno}/{numero}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response getRigheOrdineDaEvadereByOrdineAnnoNumero(
			@PathParam("anno") Integer anno,
			@PathParam("numero") Integer numero,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Posts a TestataEvasione
	 * @param salvaEvasione
	 * @param securityContext
	 * @param httpHeaders
	 * @param httpRequest
	 * @return
	 */
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response postTestataEvasione(
			SalvaEvasione salvaEvasione,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Posts a TestataEvasione from DocumentoTrasporto
	 * @param documentoTrasporto
	 * @param securityContext
	 * @param httpHeaders
	 * @param httpRequest
	 * @return
	 */
	@POST
	@Path("insertFromDT/{idSettore}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	Response postTestataEvasioneFromDocumentoTrasporto(
		@PathParam("idSettore") UUID idSettore,
		DocumentoTrasporto documentoTrasporto,
		@Context SecurityContext securityContext,
		@Context HttpHeaders httpHeaders,
		@Context HttpServletRequest httpRequest);

	/**
	 * Puts an TestataEvasione by its id
	 * @param id the id
	 * @param testataEvasione the testataEvasione to update
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@PUT
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response putTestataEvasioneById(
			@PathParam("id") UUID id,
			TestataEvasione testataEvasione,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Puts an TestataEvasione for Riepilogo Fattura
	 * @param id the id
	 * @param testataEvasione the testataEvasione to update
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@PUT
	@Path("aggiorna-riepilogo-fattura/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response putTestataEvasionePerRiepilogoFattura(
			@PathParam("id") UUID id,
			TestataEvasione testataEvasione,
			@QueryParam("bypass") Boolean bypassControls,
			@QueryParam("bypassFornitoreControl") Boolean bypassFornitoreControls,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Gets an TestataEvasione by its id
	 * @param id the id
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response getTestataEvasioneById(
			@PathParam("id") UUID id,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("ricerca/righe-destinatario-evasione/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response getRicercaRigheByDestinatarioEvasione(
			@PathParam("id") UUID id,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("ricerca/impegni-riga-evasione/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response getRicercaImpegniByRigaEvasione(
			@PathParam("id") UUID id,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("documento-trasporto/ricerca")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response postRicercaDdtEvasione(
		@Min(0) @QueryParam("offset") Integer offset,
		@Min(1) @Max(100) @QueryParam("limit") Integer limit,
		@QueryParam("sort") @DefaultValue("") String sort,
		@QueryParam("direction") @DefaultValue("asc") String direction,
		RicercaDdt ricercaDDT,
		@Context SecurityContext securityContext,
		@Context HttpHeaders httpHeaders,
		@Context HttpServletRequest httpRequest);

	/**
	 * Gets detail elaborazione of selected DocumentoTrasporto
	 * @param idDocumentoTrasporto the id
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@GET
	@Path("documento-trasporto/scartato/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	Response getElaborazioneDocumentoTrasportoScartatoById(
		@PathParam("id") Integer idDocumentoTrasporto,
		@Context SecurityContext securityContext,
		@Context HttpHeaders httpHeaders,
		@Context HttpServletRequest httpRequest);

	@GET
	@Path("documento-trasporto/ricerca-by-evasione/{idEvasione}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response getDocumentoTrasportoByEvasione(
			@PathParam("idEvasione") UUID idEvasione,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);


	@GET
	@Path("riepilogo-fattura/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response getRiepilogoFatturaByIdEvasione(
			@PathParam("id") UUID id,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("esposizione-impegni/{id}/{bDistribuzioneTotaleRigaSugliImpegni}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response getEsposizioneImpegniByRigaOrdine(
			@PathParam("id") UUID id,
			@PathParam("bDistribuzioneTotaleRigaSugliImpegni") Boolean bDistribuzioneTotaleRigaSugliImpegni,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("ricerca/{anno}/{numero}/{idEnte}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response getRicercaTestataEvasioneByAnnoENum(
			@PathParam("anno") Integer anno,
			@PathParam("numero") Integer numero,
			@PathParam("idEnte") UUID idEnte,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@PUT
	@Path("destinatario")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response putEvasioneDestinatario(
		DestinatarioEvasione destinatario,
		@Context SecurityContext securityContext,
		@Context HttpHeaders httpHeaders,
		@Context HttpServletRequest httpRequest);

	@POST
	@Path("impegni-evasione")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response postImpegniEvasione(
			SalvaImpegniEvasione salvaImpegniEvasione,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@PUT
	@Path("impegni-evasione")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response putImpegniEvasione(
			SalvaImpegniEvasione salvaImpegniEvasione,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("ricerca")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response getRicercaEvasioni(
			@Min(0) @QueryParam("offset") Integer offset,
			@Min(1) @Max(100) @QueryParam("limit") Integer limit,
			@QueryParam("sort") @DefaultValue("") String sort,
			@QueryParam("direction") @DefaultValue("asc") String direction,
			RicercaEvasioni ricercaEvasioni,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@DELETE
	@Path("destinatario-evasione/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response deleteDestinatarioEvasione(
			@PathParam("id") UUID id,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@DELETE
	@Path("riga-evasione/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response deleteRigaEvasione(
			@PathParam("id") UUID id,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@DELETE
	@Path("impegni-evasione/{riga-evasione-id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response deleteImpegniEvasioneByRiga(
			@PathParam("riga-evasione-id") UUID rigaEvasioneId,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@DELETE
	@Path("evasione/{testata-evasione-id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response deleteEvasione(
			@PathParam("testata-evasione-id") UUID testataEvasioneId,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@PUT
	@Path("verifiche-preliminari-annulla/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response putEvasioneVerifichePreliminariAnnullaById(
			@PathParam("id") UUID id,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@PUT
	@Path("annulla/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response putEvasioneAnnullaById(
			@PathParam("id") UUID id,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@PUT
	@Path("controlla/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response putEvasioneControllaById(
			@PathParam("id") UUID id,
			ControllaEvasione controllaEvasione,
			@QueryParam("fAuthorize") Boolean perAutorizzazione,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@PUT
	@Path("autorizza/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response putEvasioneAutorizzaById(
			@PathParam("id") UUID id,
			TestataEvasione testataEvasione,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	@PUT
	@Path("conferma/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response putEvasioneConfermaById(
			@PathParam("id") UUID id,
			TestataEvasione testataEvasione,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@PUT
	@Path("invia-contabilita/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response putEvasioneInviaContabilitaById(
			@PathParam("id") UUID id,
			@QueryParam("bypassControls") Boolean bypassControls,
			@QueryParam("saltaVerificaCongruenzaTotali") Boolean saltaVerificaCongruenzaTotali,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("collegate-fattura/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response getEvasioniCollegatePerFattura(
			@PathParam("id") UUID id,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	// necessario per testare il servizio verifica_evasione esposto come rest
	@POST
	@Path("verifica-evasione")
	@Produces({MediaType.APPLICATION_JSON})
	public Response verificaEvasione(
			Evasione evasioni,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Puts an TestataEvasione by its id
	 * @param id the id
	 * @param testataEvasione the testataEvasione to update
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@PUT
	@Path("riga-evasione/{id}/qta-da-evadere/{quaDaEvadere}/totaliCoerenti/{totaliCoerenti}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response putRigaEvasioneById(
			@PathParam("id") UUID idRiga,
			@PathParam("quaDaEvadere")   Integer quaDaEvadere,
			@PathParam("totaliCoerenti") String totaliCoerenti,
			RigaEvasione rigaEvasione,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("destinatario-evasione/controllo/{id-destinatario-evasione}")
	@LoadSettore
	@Produces({MediaType.APPLICATION_JSON})
	public Response getControlloSettoreAttivoSuDestinatario(
			@PathParam("id-destinatario-evasione") UUID   idDestinatarioEvasione,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
}
