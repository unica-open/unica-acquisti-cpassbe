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

import it.csi.cpass.cpassbe.lib.dto.Provvedimento;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.FiltroImpegni;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.SalvaImpegni;
import it.csi.cpass.cpassbe.lib.dto.ord.Sezione;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.web.dto.RicercaOrdini;
import it.csi.cpass.cpassbe.web.dto.RicercaRigheDaEvadere;
import it.csi.cpass.cpassbe.web.util.annotation.LoadSettore;

@Path("ord/testata-ordine")
@Produces({MediaType.APPLICATION_JSON})
public interface TestataOrdineApi {

	/**
	 * Posts a TestataOrdine
	 * @param testataOrdine
	 * @param securityContext
	 * @param httpHeaders
	 * @param httpRequest
	 * @return
	 */
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response postTestataOrdine(
			TestataOrdine testataOrdine,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Posts a TestataOrdine da Rda
	 * @param testataOrdine
	 * @param securityContext
	 * @param httpHeaders
	 * @param httpRequest
	 * @return
	 */
	@POST
	@Path("ordine-derivato")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response postTestataOrdineDerivato(
			TestataOrdine testataOrdine,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Puts an TestataOrdine by its id
	 * @param id the id
	 * @param testataOrdine the testataOrdine to update
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@PUT
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response putTestataOrdineById(
			@PathParam("id") UUID id,
			TestataOrdine testataOrdine,
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
	@LoadSettore
	@Produces({MediaType.APPLICATION_JSON})
	public Response deleteTestataOrdineById(
			@PathParam("id") UUID id,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Annulla una TestataOrdine
	 * @param testataOrdine
	 * @param securityContext
	 * @param httpHeaders
	 * @param httpRequest
	 * @return
	 */
	@POST
	@Path("annulla")
	@LoadSettore
	@Produces({MediaType.APPLICATION_JSON})
	public Response postAnnullaTestataOrdine(
			TestataOrdine testataOrdine,
			@QueryParam("bypassControlli") boolean bypassControlli,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Gets an TestataOrdine by its id
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
	public Response getTestataOrdineById(
			@PathParam("id") UUID id,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("ricerca/{anno}/{numero}/{idEnte}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response getRicercaTestataOrdineByAnnoENum(
			@PathParam("anno") Integer anno,
			@PathParam("numero") Integer numero,
			@PathParam("idEnte") UUID enteId,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("ricerca/destinatari-per-copia/{idOrdine}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response getRicercaDestinatariPerCopia(
			@PathParam("idOrdine") UUID idOrdine,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("ricerca/righe-destinatario/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response getRicercaRigheByDestinatario(
			@PathParam("id") UUID id,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("ricerca/impegni-riga/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response getRicercaImpegniByRiga(
			@PathParam("id") UUID id,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("ricerca/provvedimento")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response postRicercaProvvedimento(
			Provvedimento provvedimento ,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("ricerca/impegno")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response postRicercaImpegno(
		@Min(0) @QueryParam("offset") Integer offset,
		@Min(1) @Max(100) @QueryParam("limit") Integer limit,
		@QueryParam("sort") @DefaultValue("") String sort,
		@QueryParam("direction") @DefaultValue("asc") String direction,
		FiltroImpegni filtroImpegni,
		@Context SecurityContext securityContext,
		@Context HttpHeaders httpHeaders,
		@Context HttpServletRequest httpRequest);

	@POST
	@Path("destinatario")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response postOrdineDestinatario(
		Destinatario destinatario,
		@Context SecurityContext securityContext,
		@Context HttpHeaders httpHeaders,
		@Context HttpServletRequest httpRequest);

	@POST
	@Path("riga-ordine")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response postRigaOrdine(
		RigaOrdine rigaOrdine,
		@QueryParam("bypassControlloIva") Boolean bypassControlloIva,
		@Context SecurityContext securityContext,
		@Context HttpHeaders httpHeaders,
		@Context HttpServletRequest httpRequest);

	@POST
	@Path("riga-ordine/copia/{idFrom}/{idTo}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response postCopiaRighe(
		@PathParam("idFrom") UUID idFrom,
		@PathParam("idTo") UUID idTo,
		@Context SecurityContext securityContext,
		@Context HttpHeaders httpHeaders,
		@Context HttpServletRequest httpRequest);

	@PUT
	@Path("destinatario")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response putOrdineDestinatario(
		Destinatario destinatario,
		@Context SecurityContext securityContext,
		@Context HttpHeaders httpHeaders,
		@Context HttpServletRequest httpRequest);

	@PUT
	@Path("riga-ordine")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response putRigaOrdine(
		RigaOrdine rigaOrdine,
		@QueryParam("bypassControlloIva") Boolean bypassControlloIva,
		@Context SecurityContext securityContext,
		@Context HttpHeaders httpHeaders,
		@Context HttpServletRequest httpRequest);

	@DELETE
	@Path("destinatario/{id}")
	@LoadSettore
	@Produces({MediaType.APPLICATION_JSON})
	public Response deleteDestinatario(
			@PathParam("id") UUID id,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@DELETE
	@Path("riga-ordine/{id}")
	@LoadSettore
	@Produces({MediaType.APPLICATION_JSON})
	public Response deleteRigaOrdine(
			@PathParam("id") UUID id,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@DELETE
	@Path("impegni/{riga-ordine-id}")
	@LoadSettore
	@Produces({MediaType.APPLICATION_JSON})
	public Response deleteImpegniByRiga(
			@PathParam("riga-ordine-id") UUID rigaOrdineId,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("impegni")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response postImpegni(
		SalvaImpegni salvaImpegni,
		@Context SecurityContext securityContext,
		@Context HttpHeaders httpHeaders,
		@Context HttpServletRequest httpRequest);

	@GET
	@Path("riepilogo-impegni/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response getRiepilogoImpegniByOrdineId(
			@PathParam("id") UUID id,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@PUT
	@Path("controlla/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response putOrdineControllaById(
			@PathParam("id") UUID id,
			TestataOrdine testataOrdine,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@PUT
	@Path("conferma/{id}")
	@LoadSettore
	@Produces({MediaType.APPLICATION_JSON})
	public Response putOrdineConfermaById(
			@PathParam("id") UUID id,
			TestataOrdine testataOrdine,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@PUT
	@Path("autorizza/{id}")
	@LoadSettore
	@Produces({MediaType.APPLICATION_JSON})
	public Response putOrdineAutorizzaById(
			@PathParam("id") UUID id,
			TestataOrdine testataOrdine,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@PUT
	@Path("invia-nso-iniziale/{id}")
	@LoadSettore
	@Produces({MediaType.APPLICATION_JSON})
	public Response inviaOrdineInzialeNSO(
			@PathParam("id") UUID id,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@PUT
	@Path("invia-nso-revoca/{id}")
	@LoadSettore
	@Produces({MediaType.APPLICATION_JSON})
	public Response inviaOrdineRevocaNSO(
			@PathParam("id") UUID id,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@PUT
	@Path("verifiche-fattibilita-chiudi/{id}")
	@LoadSettore
	@Produces({MediaType.APPLICATION_JSON})
	public Response putOrdineVerificheFattibilitaChiudiById(
			@PathParam("id") UUID id,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@PUT
	@Path("chiudi/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response putOrdineChiudiById(
			@PathParam("id") UUID id,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("ricerca")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response getRicercaOrdini(
			@Min(0) @QueryParam("offset") Integer offset,
			@Min(1) @Max(100) @QueryParam("limit") Integer limit,
			@QueryParam("sort") @DefaultValue("") String sort,
			@QueryParam("direction") @DefaultValue("asc") String direction,
			RicercaOrdini ricercaOrdini,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("evasione-testata/{idEvasione}")
	@LoadSettore
	@Produces({MediaType.APPLICATION_JSON})
	public Response getTestateOrdineByEvasioneId(
			@PathParam("idEvasione") UUID idEvasione,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("evasione-anteprima-ordine/{idEvasione}")
	@LoadSettore
	@Produces({MediaType.APPLICATION_JSON})
	public Response getAnteprimeOrdineByEvasioneId(
			@PathParam("idEvasione") UUID idEvasione,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("evasione-destinatario/{idDestinatario}")
	@LoadSettore
	@Produces({MediaType.APPLICATION_JSON})
	public Response getTestateOrdineByDestinatarioEvasioneId(
			@PathParam("idDestinatario") UUID idDestinatario,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("evasione-riga/{idRiga}")
	@LoadSettore
	@Produces({MediaType.APPLICATION_JSON})
	public Response getTestateOrdineByRigaEvasioneId(
			@PathParam("idRiga") UUID idRiga,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("ricerca/righe-da-evadere")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response getRicercaRigheDaEvadere(
			RicercaRigheDaEvadere ricercaRigheDaEvadere,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("ricerca-sezione")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response getRicercaSezione(
			@Min(0) @QueryParam("offset") Integer offset,
			@Min(1) @Max(100) @QueryParam("limit") Integer limit,
			@QueryParam("sort") @DefaultValue("") String sort,
			@QueryParam("direction") @DefaultValue("asc") String direction,
			Sezione sezione,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);


	@GET
	@Path("ordine-documento/{idDocumento}")
	@LoadSettore
	@Produces({MediaType.APPLICATION_JSON})
	public Response getDocumentoById(
			@PathParam("idDocumento") Integer idDocumento,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("ordine-documento/lista/{idTestata}")
	@LoadSettore
	@Produces({MediaType.APPLICATION_JSON})
	public Response getDocumentiByOrdineTestataId(
			@PathParam("idTestata") UUID idTestataOrdine,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("controlli/invio-in-firma")
	@LoadSettore
	@Produces({MediaType.APPLICATION_JSON})
	public Response postControlliInvioInFirma(
			TestataOrdine testataOrdine,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

}
