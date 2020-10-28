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
import java.util.UUID;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.cpass.cpassbe.ejb.business.be.facade.InterventoFacade;
import it.csi.cpass.cpassbe.ejb.business.be.facade.StampeFacade;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.Cpv;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;
import it.csi.cpass.cpassbe.web.business.be.api.InterventoApi;
import it.csi.cpass.cpassbe.web.dto.InterventiDaCopia;
import it.csi.cpass.cpassbe.web.dto.WebInterventoFileHolder;
//import it.csi.cpass.cpassbe.web.dto.WebFileHolder;
import it.csi.cpass.cpassbe.web.util.annotation.Logged;

/**
 * Implementation for InterventoApi
 */
@Logged
public class InterventoApiServiceImpl extends BaseRestServiceImpl implements InterventoApi {

	@EJB private InterventoFacade interventoFacade;
	@EJB private StampeFacade stampeFacade;

	@Override
	public Response deleteInterventoById(UUID id, SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return invoke(() -> interventoFacade.deleteInterventoById(id));
	}
	@Override
	public Response getRicercaInterventi(Integer page, Integer limit, String sort, String direction, Intervento intervento, UUID settoreId, SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return invoke(() -> interventoFacade.getRicercaInterventi(page, limit, sort, direction, intervento, settoreId));
	}
	@Override
	public Response getInterventoById(UUID id, SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return invoke(() -> interventoFacade.getInterventoById(id));
	}
	@Override
	public Response getInterventoImportiByIntervento(UUID uuid, Integer offset, Integer limit, SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return invoke(() -> interventoFacade.getInterventoImportiByIntervento(uuid, offset, limit));
	}
	@Override
	public Response postIntervento(Intervento intervento, SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return invoke(() -> interventoFacade.postIntervento(intervento));
	}
	@Override
	public Response putInterventoById(UUID id, Intervento intervento, SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return invoke(() -> interventoFacade.putInterventoById(id, intervento));
	}
	@Override
	public Response getInterventoByCui(String cui,UUID idProgramma, UUID settoreId, SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() -> interventoFacade.getInterventoByCui(cui,idProgramma, settoreId));
	}
	@Override
	public Response putInterventoStatoAnnullatoById(UUID id, UUID settoreId, Intervento intervento, SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() -> interventoFacade.putInterventoStatoAnnullatoById(id, settoreId, intervento));
	}
	@Override
	public Response putInterventoStatoApprovatoById(UUID id, Intervento intervento, SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() -> interventoFacade.putInterventoStatoApprovatoById(id, intervento));
	}
	@Override
	public Response stampaAllegatoIntervento(UUID idProgramma, String formatFile, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> stampeFacade.stampaAllegatoIntervento(idProgramma, formatFile));
	}
	@Override
	public Response putInterventiStatoApprovato(List<Intervento> interventi, SecurityContext securityContext,HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> interventoFacade.putInterventiStatoApprovato(interventi));
	}
	@Override
	public Response putInterventiStatoAnnullato(UUID settoreId, List<Intervento> interventi, SecurityContext securityContext,HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> interventoFacade.putInterventiStatoAnnullato(settoreId, interventi));
	}	
	@Override
	public Response postUploadCsv(WebInterventoFileHolder intfileHolder,  SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> interventoFacade.postUploadCsv(intfileHolder.toFileHolder()));
	}
	@Override
	public Response postInterventiDaCopia(InterventiDaCopia interventiDaCopia ,UUID idProgramma, SecurityContext securityContext,HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> interventoFacade.postInterventiCopia(interventiDaCopia.getInterventi() , interventiDaCopia.getInterventoCopiaTipo(), interventiDaCopia.getInterventoImportoCopiaTipo(),idProgramma));
	}
	@Override
	public Response getRicercaInterventiXCopia(Integer page, Integer limit, String sort, String direction, UUID programmaIdOld,UUID programmaIdNew, SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return invoke(() -> interventoFacade.getRicercaInterventiXCopia(page, limit, sort, direction, programmaIdOld,programmaIdNew));
	}
	@Override
	public Response getCpvsByInterventoId(UUID id, SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() -> interventoFacade.getCpvsByInterventoId(id));
	}
	@Override
	public Response postCpvsByInterventoId(UUID idIntervento, List<Cpv> cpvs, SecurityContext securityContext,HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> interventoFacade.postCpvsByInterventoId(idIntervento, cpvs));	}
	@Override
	public Response getStoricoRupsByInterventoId(UUID id, SecurityContext securityContext,HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> interventoFacade.getStoricoRupssByInterventoId(id));
	}
	@Override
	public Response putInterventoVoltura(UUID idRup, List<Intervento> interventi,SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> interventoFacade.putInterventoVoltura(idRup,interventi));
	}
	@Override
	public Response putInterventiStatoVisto(List<Intervento> interventi, SecurityContext securityContext,HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> interventoFacade.putInterventiStatoVisto(interventi));
	}
	@Override
	public Response putInterventiStatoBozzaDaRifiuto(List<Intervento> interventi,SecurityContext securityContext,HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() -> interventoFacade.putInterventiStatoBozzaDaRifiuto(interventi));
	}	
	@Override
	public Response putInterventiStatoVistoEValidato(List<Intervento> interventi,SecurityContext securityContext,HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() -> interventoFacade.putInterventiStatoVistoEValidato(interventi));
	}
	@Override
	public Response getUltimoStatoInfoByInterventoId(UUID id, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> interventoFacade.getUltimoStatoInfoByIntervento(id));
	}
	@Override
	public Response putInterventoPrendiInCarico(List<Intervento> interventi,SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> interventoFacade.putInterventoPrendiInCarico(interventi));
	}
}
