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

import it.csi.cpass.cpassbe.ejb.business.be.facade.CommonFacade;
import it.csi.cpass.cpassbe.ejb.business.be.facade.InterventoFacade;
import it.csi.cpass.cpassbe.ejb.business.be.facade.StampeFacade;
import it.csi.cpass.cpassbe.lib.dto.Cpv;
import it.csi.cpass.cpassbe.lib.dto.pba.CopiaInterventoWrapper;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;
import it.csi.cpass.cpassbe.web.business.be.api.InterventoApi;
import it.csi.cpass.cpassbe.web.dto.InterventiDaCopia;
import it.csi.cpass.cpassbe.web.dto.RicercaInterventiPerCopia;
import it.csi.cpass.cpassbe.web.dto.WebInterventoFileHolder;
import it.csi.cpass.cpassbe.web.util.annotation.Logged;

/**
 * Implementation for InterventoApi
 */
@Logged
public class InterventoApiServiceImpl extends BaseRestServiceImpl implements InterventoApi {

	@EJB private InterventoFacade interventoFacade;
	@EJB private StampeFacade stampeFacade;
	@EJB private CommonFacade commonFacade;

	@Override
	public Response deleteInterventoById(UUID id, SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return invoke(() -> interventoFacade.deleteInterventoById(id));
	}
	@Override
	public Response deleteInterventoPhysicallyById(UUID id, SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return invoke(() -> interventoFacade.deleteInterventoPhysicallyById(id));
	}

	@Override
	public Response getRicercaInterventi(Integer page, Integer limit, String sort, String direction, Intervento intervento, SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return invoke(() -> interventoFacade.getRicercaInterventi(page, limit, sort, direction, intervento));
	}
	@Override
	public Response getInterventoById(UUID idIntervento, SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return invoke(() -> interventoFacade.getInterventoById(idIntervento));
	}
	@Override
	public Response getInterventoInProgrammaFuturoById(UUID idIntervento, SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return invoke(() -> interventoFacade.getInterventoInProgrammaFuturoById(idIntervento));
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
	public Response getInterventiByCui(String cui, SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() -> interventoFacade.getInterventiByCui(cui));
	}
	@Override
	public Response getInterventoByCui(String cui,UUID idProgramma, SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() -> interventoFacade.getInterventoByCui(cui,idProgramma));
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
		return invoke(() -> stampeFacade.stampaAllegatoInterventoDaButtare(idProgramma, formatFile));
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
		Response ris = invoke(() -> interventoFacade.postUploadCsv(intfileHolder.toFileHolder()));
		return ris;
	}
	@Override
	public Response postInterventiDaCopia(InterventiDaCopia interventiDaCopia ,UUID idProgramma, SecurityContext securityContext,HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> interventoFacade.postInterventiCopia(interventiDaCopia.getInterventi() , interventiDaCopia.getInterventoCopiaTipo(), interventiDaCopia.getInterventoImportoCopiaTipo(),idProgramma));
	}
	@Override
	public Response postInterventiDaCopiaV2(List<CopiaInterventoWrapper> listCopiaInterventoWrapper ,UUID idProgramma, String stato, SecurityContext securityContext,HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> interventoFacade.postInterventiCopiaV2(listCopiaInterventoWrapper , idProgramma, stato));
	}
	@Override
	public Response getRicercaInterventiXCopia(Integer page, Integer limit, String sort, String direction, UUID programmaIdOld,UUID programmaIdNew, SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return invoke(() -> interventoFacade.getRicercaInterventiXCopia(page, limit, sort, direction, programmaIdOld,programmaIdNew, null));
	}
	@Override
	public Response postRicercaInterventiXCopia(Integer page, Integer limit, String sort, String direction, RicercaInterventiPerCopia ricercaInterventiPerCopia, SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return invoke(() -> interventoFacade.getRicercaInterventiXCopia(page, limit, sort, direction, ricercaInterventiPerCopia.getProgrammaIdOld(), ricercaInterventiPerCopia.getProgrammaIdNew(), ricercaInterventiPerCopia.getUtenteRupId()));
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
	public Response putInterventoVoltura(UUID idSettore, UUID idRup, List<Intervento> interventi,SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> interventoFacade.putInterventoVoltura(idSettore,idRup,interventi));
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
	public Response getStatiInterventoByInterventoId(UUID id, SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() -> interventoFacade.getStatiInterventoByInterventoId(id));
	}

	@Override
	public Response putInterventoPrendiInCarico(List<Intervento> interventi,SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> interventoFacade.putInterventoPrendiInCarico(interventi));
	}
	@Override
	public Response putInterventiStatoVistoRagioneria(List<Intervento> interventi, SecurityContext securityContext,HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> interventoFacade.putInterventiStatoVistoRagioneria(interventi));
	}
	@Override
	public Response putInterventiStatoRifiutoVistoRagioneria(List<Intervento> interventi, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> interventoFacade.putInterventiStatoRifiutoVistoRagioneria(interventi));
	}
	@Override
	public Response getInterventiCapofila(UUID programmaId,Intervento intervento,SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> interventoFacade.getInterventiCapofila(programmaId, intervento));
	}
	@Override
	public Response getInterventibyCapofilaId(UUID capofilaId, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> interventoFacade.getInterventibyCapofilaId(capofilaId));
	}
	@Override
	public Response postControlloCapofilaXCopiaInterventi(List<Intervento> interventi, SecurityContext securityContext,HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> interventoFacade.postControlloCapofilaXCopiaInterventi(interventi));
	}
	@Override
	public Response postControlloCancellazioneIntervento(List<Intervento> interventi, SecurityContext securityContext,HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> interventoFacade.postControlloCancellazioneIntervento(interventi));
	}
	@Override
	public Response getCigByInterventoId(UUID idIntervento, SecurityContext securityContext, HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() -> interventoFacade.getCigByInterventoId(idIntervento));
	}

	@Override
	public Response putInterventiRiportaInBozza(List<Intervento> interventi,SecurityContext securityContext,HttpHeaders httpHeaders,HttpServletRequest httpRequest) {
		return invoke(() -> interventoFacade.putInterventiRiportaInBozza(interventi));
	}
	@Override
	public Response putAvviaInterventoById(UUID idIntervento,Integer motivoEsclusioneCigId, List<String> cigs, SecurityContext securityContext,HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> interventoFacade.putAvviaInterventoById(idIntervento,motivoEsclusioneCigId , cigs));
	}
	@Override
	public Response putInterventoNonAvviatoById(UUID idIntervento, SecurityContext securityContext,HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> interventoFacade.putInterventoNonAvviatoById(idIntervento));
	}

}
