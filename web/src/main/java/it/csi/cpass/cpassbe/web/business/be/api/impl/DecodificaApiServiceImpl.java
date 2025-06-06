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

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.cpass.cpassbe.ejb.business.be.facade.DecodificaFacade;
import it.csi.cpass.cpassbe.lib.dto.Ods;
import it.csi.cpass.cpassbe.web.business.be.api.DecodificaApi;
import it.csi.cpass.cpassbe.web.util.annotation.Logged;

/**
 * Implementation for DecodificaApi
 */
@Logged
public class DecodificaApiServiceImpl extends BaseRestServiceImpl implements DecodificaApi {

	@EJB private DecodificaFacade decodificaFacade;

	@Override
	public Response getCpv(SecurityContext securityContext, HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest) {
		return invoke(decodificaFacade::getCpv);
	}
	@Override
	public Response getCpvTree(SecurityContext securityContext, HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest) {
		return invoke(decodificaFacade::getCpvTree);
	}
	@Override
	public Response getCpvOdsTree(SecurityContext securityContext, HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest) {
		return invoke(decodificaFacade::getCpvOdsTree);
	}
	@Override
	public Response getModalitaAffidamento(SecurityContext securityContext, HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest) {
		return invoke(decodificaFacade::getModalitaAffidamento);
	}
	@Override
	public Response getNuts(SecurityContext securityContext, HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest) {
		return invoke(decodificaFacade::getNuts);
	}
	@Override
	public Response getPriorita(SecurityContext securityContext, HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest) {
		return invoke(decodificaFacade::getPriorita);
	}
	@Override
	public Response getRisorse(SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return invoke(decodificaFacade::getRisorse);
	}
	@Override
	public Response getSettoreInterventi(SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return invoke(decodificaFacade::getSettoreInterventi);
	}
	@Override
	public Response getAusas(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(decodificaFacade::getAusas);
	}
	@Override
	public Response getAcquistiVariati(SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(decodificaFacade::getAcquistiVariati);
	}
	@Override
	public Response getRicompresoTipos(SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(decodificaFacade::getRicompresoTipos);
	}


	@Override
	public Response getTipoOrdine(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(decodificaFacade::getTipoOrdine);
	}

	@Override
	public Response getListaTipoOrdineExcludeCode(String noTypeCode, SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() -> decodificaFacade.getListaTipoOrdineExcludeCode(noTypeCode));
	}

	@Override
	public Response getTipoProceduraOrd(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(decodificaFacade::getTipoProceduraOrd);
	}

	@Override
	public Response getTipoProceduraPba(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(decodificaFacade::getTipoProceduraPba);
	}

	@Override
	public Response getAliquoteIva(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(decodificaFacade::getAliquoteIva);
	}

	@Override
	public Response getUnitaMisura(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(decodificaFacade::getUnitaMisura);
	}

	@Override
	public Response getRicercaCpvOggettiSpesa(Integer page, Integer limit, String sort, String direction, Ods ods, SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return invoke(() -> decodificaFacade.getRicercaCpvOggettiSpesa(page, limit, sort, direction, ods));
	}

	@Override
	public Response getStatoByTipo(String tipo, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> decodificaFacade.getStatoByTipo(tipo));
	}
	@Override
	public Response getStatoNsoByTipo(String tipo, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> decodificaFacade.getStatoNsoByTipo(tipo));
	}
	@Override
	public Response getAllCausaleSospensioneEvasioneValide(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> decodificaFacade.getAllCausaleSospensioneEvasioneValide());
	}
	@Override
	public Response getTipoEvasione(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(decodificaFacade::getTipoEvasione);
	}
	@Override
	public Response getTipoAcquistos(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(decodificaFacade::getTipoAcquisto);
	}
	@Override
	public Response getProvvedimentoTipo(SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(decodificaFacade::getProvvedimentoTipo);
	}
	@Override
	public Response getMotiviEsclusioneCig(SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(decodificaFacade::getMotiviEsclusioneCig);
	}

	@Override
	public Response getTipoSettore(SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return invoke(decodificaFacade::getTipoSettore);
	}


}
