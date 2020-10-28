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

import it.csi.cpass.cpassbe.ejb.business.be.facade.ElaborazioneFacade;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;
import it.csi.cpass.cpassbe.web.business.be.api.ElaborazioneApi;
import it.csi.cpass.cpassbe.web.util.annotation.Logged;

/**
 * Implementation for InterventoApi
 */
@Logged
public class ElaborazioneApiServiceImpl extends BaseRestServiceImpl implements ElaborazioneApi {

	@EJB private ElaborazioneFacade enteFacade;
/*
	@Override
	public Response deleteElaborazioneById(UUID id, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() ->enteFacade.deleteElaborazioneById(id));
	}
	*/
	@Override
	public Response postElaborazione(Elaborazione elaborazione, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() ->enteFacade.postElaborazione(elaborazione));
	}
	@Override
	public Response putElaborazioneById(Integer id, Elaborazione elaborazione, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() ->enteFacade.putElaborazioneById(id,elaborazione));
	}
	/*
	@Override
	public Response getElaborazioneById(UUID id, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() ->enteFacade.getElaborazioneById(id));
	}

	@Override
	public Response getEnti(Integer offset, Integer limit, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() ->enteFacade.getEnti(offset,limit));
	}
	*/
	@Override
	public Response getElaborazioniByEntity(String id, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() ->enteFacade.getElaborazioniByEntity(id));
	}
}
