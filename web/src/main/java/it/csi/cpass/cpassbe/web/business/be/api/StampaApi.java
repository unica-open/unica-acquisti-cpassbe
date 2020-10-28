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
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.cpass.cpassbe.ejb.util.mime.MimeTypeContainer.MimeType;


/**
 * API interface for /stampa path
 */
@Path("stampa")
@Produces({MediaType.APPLICATION_JSON})
public interface StampaApi {
	/**
	 * Stampa
	 * @param nomeStampa the nome stampa
	 * @param formatFile the format file
	 * @param param the param
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@POST
	@Path("{nome-stampa}/{format-file}")
	@Produces({MimeType.EXCEL_WORKBOOK, MimeType.PDF})
	public Response stampa(
			@PathParam("nome-stampa") String nomeStampa,
			@PathParam("format-file") String formatFile,
			List<String> param,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);	
}
