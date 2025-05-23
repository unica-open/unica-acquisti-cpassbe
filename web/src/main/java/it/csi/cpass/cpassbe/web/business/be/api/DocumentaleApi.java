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

import it.csi.cpass.cpassbe.lib.dto.ord.ProtocolloOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.web.util.annotation.LoadSettore;

@Path("documentale/ordine")
@Produces({MediaType.APPLICATION_JSON})
public interface DocumentaleApi {

	@GET
	@Path("ricerca-prot-origin/{anno-orig}/{numero-orig}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response getProtocolloOrigineByAnnoNum(
			@PathParam("anno-orig") Integer anno,
			@PathParam("numero-orig") String numero,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("ricerca-struttura-aggregativa-indice-classificazione")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response getStrutturaAggregativaXIndiceclassificazioneEstesa(
			ProtocolloOrdine protocolloOrdine,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("ricerca-struttura-aggregativa-struttura-aggregativa")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response getStrutturaAggregativaXStrutturaAggregativa(
			ProtocolloOrdine protocolloOrdine,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("archivia-ordine/{testata-ordine-id}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response getArchiviaOrdine(
			@PathParam("testata-ordine-id") UUID testataOrdineId,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("verifica-archiviazione-ordine/{testata-ordine-id}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response getVerificaArchiviazioneOrdine(
			@PathParam("testata-ordine-id") UUID testataOrdineId,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);


	@PUT
	@Path("protocolla-ordine/{testata-ordine-id}")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response getProtocollaOrdine(
			@PathParam("testata-ordine-id") UUID testataOrdineId,
			TestataOrdine testataOrdine,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

}
