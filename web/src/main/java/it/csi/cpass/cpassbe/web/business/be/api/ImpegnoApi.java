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
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.Subimpegno;
import it.csi.cpass.cpassbe.web.util.annotation.LoadSettore;

/**
 * API interface for /impegno path
 */
@Path("impegno")
@Produces({MediaType.APPLICATION_JSON})
public interface ImpegnoApi {

	@POST
	@Path("ricerca/impegno-chiave-logica")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response postRicercaImpegnoByChiaveLogica(Impegno impegno, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest);

	@POST
	@Path("ricerca/sub-impegno-chiave-logica")
	@Produces({MediaType.APPLICATION_JSON})
	@LoadSettore
	public Response postRicercaSubimpegnoByChiaveLogica(Subimpegno subimpegno, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest);

}
