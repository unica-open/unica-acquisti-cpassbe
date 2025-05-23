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
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 * API interface for
 */
@Path("batch")
@Produces({MediaType.APPLICATION_JSON})
public interface BatchApi {

	@GET
	@Path("verifica-invio-contabilita/ente/{ente}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getVerificaInvioContabilita(
			@PathParam("ente") String ente,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("recupero-ddt/ente/{ente}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getRecuperoDdt(
			@PathParam("ente") String ente,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("recupero-notifica-nso/ente/{ente}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getRecuperoNotificaNSOService(
			@PathParam("ente") String ente,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("aggiornamenti-impegni/ente/{ente}/numelab/{numelab}/dataElab/{dataElab}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getAggiornamentiImpegni(
			@PathParam("ente") String ente,
			@PathParam("numelab") Integer numelab,
			@PathParam("dataElab") String dataElab,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("caricamento-aggiornamenti-impegni/ente/{ente}/numelab/{numelab}/dataElab/{dataElab}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getCaricamentoAggiornamentiImpegni(
			@PathParam("ente") String ente,
			@PathParam("numelab") Integer numelab,
			@PathParam("dataElab") String dataElab,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("aggiornamenti-subimpegni/ente/{ente}/numelab/{numelab}/dataElab/{dataElab}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getAggiornamentiSubImpegni(
			@PathParam("ente") String ente,
			@PathParam("numelab") Integer numelab,
			@PathParam("dataElab") String dataElab,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("caricamento-aggiornamenti-subimpegni/ente/{ente}/numelab/{numelab}/dataElab/{dataElab}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getCaricamentoAggiornamentiSubImpegni(
			@PathParam("ente") String ente,
			@PathParam("numelab") Integer numelab,
			@PathParam("dataElab") String dataElab,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);


	@GET
	@Path("controllo-batch-impegni/ente/{ente}/numelab/{numelab}/dataElab/{dataElab}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getControlloBatchImpegni(
			@PathParam("ente") String ente,
			@PathParam("numelab") Integer numelab,
			@PathParam("dataElab") String dataElab,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("smistamento/ente/{ente}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getSmistamentoRms(
			@PathParam("ente") String ente,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);


	@GET
	@Path("storico-file-ddt/ente/{ente}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getStoricizzaFileDdt(
			@PathParam("ente") String ente,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("storico-file-nso/ente/{ente}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getStoricizzaFileNso(
			@PathParam("ente") String ente,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("aggiornamento-struttura/ente/{ente}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getAggiornaStruttura(
			@PathParam("ente") String ente,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
}


