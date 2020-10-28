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

import java.util.UUID;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.cpass.cpassbe.ejb.business.be.facade.EnteFacade;
import it.csi.cpass.cpassbe.web.business.be.api.EnteApi;
import it.csi.cpass.cpassbe.web.util.annotation.Logged;

/**
 * Implementation for InterventoApi
 */
@Logged
public class EnteApiServiceImpl extends BaseRestServiceImpl implements EnteApi {

	@EJB private EnteFacade enteFacade;
/*
	@Override
	public Response deleteEnteById(UUID id, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() ->enteFacade.deleteEnteById(id));
	}
	@Override
	public Response postEnte(Ente ente, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() ->enteFacade.postEnte(utente));
	}
	@Override
	public Response putEnteById(UUID id, Enteente, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() ->enteFacade.putEnteById(id,ente));
	}
	*/
	@Override
	public Response getEnteById(UUID id, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() ->enteFacade.getEnteById(id));
	}

	/*
	@Override
	public Response getEnti(Integer offset, Integer limit, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() ->enteFacade.getEnti(offset,limit));
	}
	*/
}
