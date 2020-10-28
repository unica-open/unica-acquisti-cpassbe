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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.cpass.cpassbe.ejb.business.be.facade.EvasioneFacade;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.ControllaEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DestinatarioEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.SalvaEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.SalvaImpegniEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.TestataEvasione;
import it.csi.cpass.cpassbe.web.business.be.api.EvasioneApi;
import it.csi.cpass.cpassbe.web.dto.RicercaEvasioni;

public class EvasioneApiServiceImpl extends BaseRestServiceImpl implements EvasioneApi {

	@EJB
	private EvasioneFacade evasioneFacade;
	
	@Override
	public Response getRigheOrdineDaEvadereByOrdineAnnoNumero(Integer anno, Integer numero, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> evasioneFacade.getRigheOrdineDaEvadereByOrdineAnnoNumero(anno, numero));
	}

	@Override
	public Response postTestataEvasione(SalvaEvasione salvaEvasione, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> evasioneFacade.postTestataEvasione(salvaEvasione));
	}

	@Override
	public Response putTestataEvasioneById(UUID id, TestataEvasione testataEvasione, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> evasioneFacade.putTestataEvasioneById(id, testataEvasione));
	}
	
	@Override
	public Response putTestataEvasionePerRiepilogoFattura(UUID id, TestataEvasione testataEvasione, Boolean bypassControl, Boolean bypassFornitoreControl,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> evasioneFacade.putTestataEvasionePerRiepilogoFattura(id, testataEvasione, bypassControl, bypassFornitoreControl));
	}

	@Override
	public Response getTestataEvasioneById(UUID id, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> evasioneFacade.getTestataEvasioneById(id));
	}

	@Override
	public Response getRicercaRigheByDestinatarioEvasione(UUID idDestinatarioEvasione, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> evasioneFacade.getRigheByDestinatarioEvasione(idDestinatarioEvasione));
	}

	@Override
	public Response getRicercaImpegniByRigaEvasione(UUID idRigaEvasione, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> evasioneFacade.getImpegniByRigaEvasione(idRigaEvasione));
	}

	@Override
	public Response getRiepilogoFatturaByIdEvasione(UUID idEvasione, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> evasioneFacade.getRiepilogoFatturaByIdEvasione(idEvasione));
	}
	
	@Override
	public Response getEsposizioneImpegniByRigaOrdine(UUID idRigaEvasione, Boolean bDistribuzioneTotaleRigaSugliImpegni, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> evasioneFacade.getEsposizioneImpegniByRigaOrdine(idRigaEvasione, bDistribuzioneTotaleRigaSugliImpegni));
	}
	
	public Response getRicercaTestataEvasioneByAnnoENum(Integer anno, Integer numero, UUID idEnte, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> evasioneFacade.getTestataEvasioneByAnnoENum(anno, numero, idEnte));
	}
	
	@Override
	public Response putEvasioneDestinatario(DestinatarioEvasione destinatario, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> evasioneFacade.putEvasioneDestinatario(destinatario));
	}

	@Override
	public Response postImpegniEvasione(SalvaImpegniEvasione salvaImpegniEvasione, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> evasioneFacade.postImpegniEvasione(salvaImpegniEvasione));
	}
	
	@Override
	public Response putImpegniEvasione(SalvaImpegniEvasione salvaImpegniEvasione, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> evasioneFacade.putImpegniEvasione(salvaImpegniEvasione));
	}
	
   @Override
	public Response getRicercaEvasioni(Integer page, Integer limit, String sort, String direction, RicercaEvasioni ricercaEvasioni
			, SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return invoke(() -> evasioneFacade.getRicercaEvasioni(page, limit, sort, direction, 
				ricercaEvasioni.getAnnoEvasioneDa(), ricercaEvasioni.getNumeroEvasioneDa(), ricercaEvasioni.getAnnoEvasioneA(), ricercaEvasioni.getNumeroEvasioneA(), ricercaEvasioni.getDataInserimentoDa(), ricercaEvasioni.getDataInserimentoA(),
				ricercaEvasioni.getAnnoOrdineDa(), ricercaEvasioni.getNumeroOrdineDa(), ricercaEvasioni.getAnnoOrdineA(), ricercaEvasioni.getNumeroOrdineA(), ricercaEvasioni.getDataEmissioneDa(), ricercaEvasioni.getDataEmissioneA(), ricercaEvasioni.getAnnoProvvedimento(), ricercaEvasioni.getNumeroProvvedimento(),
				ricercaEvasioni.getTestataEvasione(), ricercaEvasioni.getDestinatarioEvasione(), ricercaEvasioni.getImpegno(), ricercaEvasioni.getSubimpegno(), ricercaEvasioni.getOggettiSpesa()
				));
	}

	@Override
	public Response deleteDestinatarioEvasione(UUID id, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> evasioneFacade.deleteDestinatarioEvasione(id));
	}

	@Override
	public Response deleteRigaEvasione(UUID id, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> evasioneFacade.deleteRigaEvasione(id));
	}

	@Override
	public Response deleteImpegniEvasioneByRiga(UUID rigaEvasioneId, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> evasioneFacade.deleteImpegniEvasioneByRiga(rigaEvasioneId));
	}
	
	@Override
	public Response deleteEvasione(UUID testataEvasioneId, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> evasioneFacade.deleteEvasione(testataEvasioneId));
	}
	
	@Override
	public Response putEvasioneVerifichePreliminariAnnullaById(UUID id, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> evasioneFacade.putEvasioneVerifichePreliminariAnnullaById(id));
	}
	
	@Override
	public Response putEvasioneAnnullaById(UUID id, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> evasioneFacade.putEvasioneAnnullaById(id));
	}
	
	@Override
	public Response putEvasioneControllaById(UUID id, ControllaEvasione controllaEvasione, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> evasioneFacade.putEvasioneControllaById(id, controllaEvasione));
	}
	
	@Override
	public Response putEvasioneAutorizzaById(UUID id, TestataEvasione testataEvasione, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> evasioneFacade.putEvasioneAutorizzaById(id, testataEvasione));
	}
	
	@Override
	public Response putEvasioneInviaContabilitaById(UUID id, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> evasioneFacade.putEvasioneInviaContabilitaById(id));
	}
	
}
