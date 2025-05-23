/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - WAR submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
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

import it.csi.cpass.cpassbe.ejb.business.be.facade.MagazzinoFacade;
import it.csi.cpass.cpassbe.lib.dto.mag.Magazzino;
import it.csi.cpass.cpassbe.web.business.be.api.MagazzinoApi;
import it.csi.cpass.cpassbe.web.util.annotation.Logged;

/**
 * Implementation for RdaApi
 */
@Logged
public class MagazzinoApiServiceImpl extends BaseRestServiceImpl implements MagazzinoApi {

	@EJB private MagazzinoFacade magazzinoFacade;

	@Override
	public Response getMagazzinoById(Integer id, SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() -> magazzinoFacade.getMagazzinoById(id));
	}

	@Override
	public Response getRicercaMagazzino(@Min(0) Integer offset, @Min(1) @Max(100) Integer limit, String sort,String direction, Magazzino magazzino, SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() -> magazzinoFacade.getRicercaMagazzino(offset, limit, sort, direction, magazzino));
	}

	@Override
	public Response postMagazzino(Magazzino magazzino, SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return null;
	}

	@Override
	public Response putMagazzino(UUID id, Magazzino magazzino, SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return null;
	}

	@Override
	public Response deleteMagazzino(UUID id, SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return null;
	}

	@Override
	public Response getMagazzini(SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() -> magazzinoFacade.getMagazzini( ));
	}

}
