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
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.cpass.cpassbe.lib.dto.ord.consultazioni.RicercaXConsultazioni;
import it.csi.cpass.cpassbe.web.util.annotation.LoadSettore;

/**
 * API interface for /decodifica path
 */
@Path("ord/consultazioni")
@Produces({MediaType.APPLICATION_JSON})
public interface ConsultazioniApi {

	@POST
	@Path("ricerca/impegno")
	@LoadSettore
	@Produces({MediaType.APPLICATION_JSON})
	public Response postRicercaConsultazioniXImpegno(
			//@Min(0) @QueryParam("offset") Integer offset,
			//@Min(1) @Max(100) @QueryParam("limit") Integer limit,
			//@QueryParam("sort") @DefaultValue("") String sort,
			//@QueryParam("direction") @DefaultValue("asc") String direction,
			RicercaXConsultazioni ricercaXConsultazioni,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("ricerca/ordine")
	@LoadSettore
	@Produces({MediaType.APPLICATION_JSON})
	public Response postRicercaConsultazioniXOrdine(
			//@Min(0) @QueryParam("offset") Integer offset,
			//@Min(1) @Max(100) @QueryParam("limit") Integer limit,
			RicercaXConsultazioni ricercaXConsultazioni,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("ricerca/riepilogo")
	@LoadSettore
	@Produces({MediaType.APPLICATION_JSON})
	public Response postRicercaConsultazioniXRiepilogo(
			RicercaXConsultazioni ricercaXConsultazioni,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

}
