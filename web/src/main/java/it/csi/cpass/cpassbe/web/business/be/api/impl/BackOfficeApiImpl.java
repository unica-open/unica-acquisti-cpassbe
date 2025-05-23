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

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.cpass.cpassbe.ejb.business.be.facade.BoFacade;
import it.csi.cpass.cpassbe.lib.dto.ModuloRuoloPermesso;
import it.csi.cpass.cpassbe.lib.dto.Notifica;
import it.csi.cpass.cpassbe.lib.dto.RuoloPermesso;
import it.csi.cpass.cpassbe.lib.dto.custom.SmistamentoManualeRms;
import it.csi.cpass.cpassbe.web.business.be.api.BackOfficeApi;
import it.csi.cpass.cpassbe.web.dto.WebAggiornamentoOdsFileHolder;
import it.csi.cpass.cpassbe.web.dto.WebSmistamentoRmsFileHolder;
import it.csi.cpass.cpassbe.web.util.annotation.Logged;

/**
 * Implementation for RmsApi
 */
@Logged
public class BackOfficeApiImpl extends BaseRestServiceImpl implements BackOfficeApi {

	@EJB private BoFacade boFacade;

	@Override
	public Response postUploadCsvRegoleSmistamentoRms(WebSmistamentoRmsFileHolder fileHolder, SecurityContext securityContext,HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> boFacade.postUploadCsvRegoleSmistamentoRms(fileHolder.toFileCsvRegoleHolder()));
	}

	@Override
	public Response postUploadCsvAggiornamentoOds(WebAggiornamentoOdsFileHolder fileHolder,SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> boFacade.postUploadCsvAggiornamentoOds(fileHolder.toFileCsvAggiornaOdsHolder()));
	}

	@Override
	public Response putSmistamentoManualeRms(SmistamentoManualeRms smistamentoManualeRms,SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> boFacade.putSmistamentoManualeRms(smistamentoManualeRms));
	}

	@Override
	public Response putDisattivaFunzioniGestione(SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() -> boFacade.putDisattivaFunzioniGestione());
	}

	@Override
	public Response putCancellaOrdiniBozza(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> boFacade.putCancellaOrdiniBozza());
	}

	@Override
	public Response putAnnullaOrdiniConfermati(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> boFacade.putAnnullaOrdiniConfermati());
	}

	@Override
	public Response putRiattivazioneFunzioniGestione(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> boFacade.putRiattivazioneFunzioniGestione());
	}

	@Override
	public Response postAvviso(Notifica notifica, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> boFacade.postAvviso(notifica));
	}

	@Override
	public Response getPermessiDaGestire(Integer page, Integer limit, String sort, String direction, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> boFacade.getPermessiDaGestire(page, limit, sort, direction));
	}

	@Override
	public Response putGestionePermessiAttivi(List<RuoloPermesso> ruoloPermessoAttivi,SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> boFacade.putGestionePermessiAttivi(ruoloPermessoAttivi));
	}

	@Override
	public Response putGestionePermessiNonAttivi(List<ModuloRuoloPermesso> moduloRuoloPermessoNonAttivi,SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> boFacade.putGestionePermessiNonAttivi(moduloRuoloPermessoNonAttivi));
	}

	@Override
	public Response getPermessiDaGestireNonAttivi(Integer page, Integer limit, String sort, String direction, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> boFacade.getPermessiDaGestireNonAttivi(page, limit, sort, direction));
	}

	@Override
	public Response getGestioneRuoloPermessoList(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> boFacade.getGestioneRuoloPermessoList());
	}

	@Override
	public Response getGestioneRuoloPermessoNonAttiviList(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> boFacade.getGestioneRuoloPermessoNonAttiviList());
	}








}
