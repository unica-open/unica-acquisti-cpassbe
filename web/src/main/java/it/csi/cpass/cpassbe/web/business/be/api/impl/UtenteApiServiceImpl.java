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

import it.csi.cpass.cpassbe.ejb.business.be.facade.UtenteFacade;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.web.business.be.api.UtenteApi;
import it.csi.cpass.cpassbe.web.util.annotation.Logged;

/**
 * Implementation for InterventoApi
 */
@Logged
public class UtenteApiServiceImpl extends BaseRestServiceImpl implements UtenteApi {

	@EJB private UtenteFacade utenteFacade;

	@Override
	public Response deleteUtenteById(UUID id, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> utenteFacade.deleteUtenteById(id));
	}
	@Override
	public Response postUtente(Utente utente, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> utenteFacade.postUtente(utente));
	}
	@Override
	public Response putUtenteById(UUID id, Utente utente, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> utenteFacade.putUtenteById(id, utente));
	}
	@Override
	public Response getUtenteById(UUID id, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> utenteFacade.getUtenteById(id));
	}
	@Override
	public Response getUtenti(Integer offset, Integer limit, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> utenteFacade.getUtenti(offset,limit));
	}
	@Override
	public Response getSettoriByUtente(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> utenteFacade.getSettoriByUtente());
	}
	@Override
	public Response getModuloBySettore(UUID settoreId, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> utenteFacade.getModuloBySettore(settoreId));
	}
	@Override
	public Response getPermessiBySettoreAndModulo(UUID settoreId, Integer idModulo, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> utenteFacade.getPermessiBySettoreAndModulo(settoreId, idModulo));
	}
	@Override
	public Response getUtenteSelf(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> utenteFacade.getUtenteSelf());
	}
	@Override
	public Response getUtenteBySettoreRuolo(UUID settoreId, String ruoloCodice, SecurityContext securityContext,HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> utenteFacade.getUtenteBySettoreRuolo(settoreId, ruoloCodice));
	}
	
	@Override
	public Response getRuoliBySettore(UUID settoreId, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> utenteFacade.getRuoliBySettore(settoreId));
	}

	@Override
	public Response getRupsBySettoreId(UUID settoreId, SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() -> utenteFacade.getRupsBySettoreId(settoreId));
	}
	@Override
	public Response getUtenteRupDelegante(UUID idUtenteDelegante, SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() -> utenteFacade.getUtenteRupDelegante(idUtenteDelegante));
	}
	@Override
	public Response getSettoriByRupId(UUID rupId, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> utenteFacade.getSettoriByRupId(rupId));
	}
	@Override
	public Response getSettoriRuoliPermessiByUtente(SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() -> utenteFacade.getSettoriRuoliPermessiByUtente());	}
}
