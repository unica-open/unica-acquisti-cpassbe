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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.cpass.cpassbe.ejb.business.be.facade.SettoreFacade;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.web.business.be.api.SettoreApi;
import it.csi.cpass.cpassbe.web.util.annotation.Logged;

/**
 * Implementation for CommonApiServiceImpl
 */
@Logged
public class SettoreApiServiceImpl extends BaseRestServiceImpl implements SettoreApi {

	@EJB
	private SettoreFacade settoreFacade;

	@Override
	public Response getSettoreById(UUID settoreId, SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() ->settoreFacade.getSettoreById(settoreId));
	}

	@Override
	public Response deleteSettoreById(UUID id, SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return null;
	}

	@Override
	public Response putSettore(UUID settoreId, Settore settore, SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() ->settoreFacade.putSettore(settoreId,settore));
	}

	@Override
	public Response postSettore(Settore settore, SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() ->settoreFacade.postSettore(settore));
	}

	@Override
	public Response postRicercaSettore(@Min(0) Integer offset, @Min(1) @Max(100) Integer limit, String sort,String direction, Settore settore, SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return null;
	}

	@Override
	public Response getSezioniBySettoreById(UUID settoreId, SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() ->settoreFacade.getSezioniBySettoreById(settoreId));
	}

	@Override
	public Response getCdcValidi(SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() ->settoreFacade.getCdcValidi());
	}

	@Override
	public Response getCdcBySettore(UUID settoreId, SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
