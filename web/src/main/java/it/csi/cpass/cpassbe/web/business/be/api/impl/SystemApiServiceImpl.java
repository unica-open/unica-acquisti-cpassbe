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
package it.csi.cpass.cpassbe.web.business.be.api.impl;

import java.net.URI;
import java.util.UUID;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.cpass.cpassbe.ejb.business.be.facade.SystemFacade;
import it.csi.cpass.cpassbe.web.business.be.api.SystemApi;
import it.csi.cpass.cpassbe.web.util.annotation.Logged;

/**
 * Implementation for InterventoImportiApi
 */
@Logged
public class SystemApiServiceImpl extends BaseRestServiceImpl implements SystemApi {

	@EJB private SystemFacade systemFacade;

	@Override
	public Response ping(SecurityContext securityContext, HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest) {
		return Response.ok().build();
		//Response.temporaryRedirect(https://dev-unica-acquisti-coto.nivolapiemonte.it/cpass-devsliv1wrup/Shibboleth.sso/Logout)
	}

	@Override
	public Response getComunicazioni(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> systemFacade.getComunicaziones());
	}

	@Override
	public Response postCsiAudit(String cf,String azione, SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() -> systemFacade.postCsiAudit(cf,azione));
	}



}
