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
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.cpass.cpassbe.ejb.business.be.facade.EvasioneFacade;
import it.csi.cpass.cpassbe.ejb.business.be.facade.ExposedFacade;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.ControllaEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DestinatarioEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DocumentoTrasporto;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.RigaEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.SalvaEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.SalvaImpegniEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.TestataEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.custom.RicercaDdt;
import it.csi.cpass.cpassbe.lib.exposed.dto.Evasione;
import it.csi.cpass.cpassbe.web.business.be.api.EvasioneApi;
import it.csi.cpass.cpassbe.web.dto.RicercaEvasioni;

public class EvasioneApiServiceImpl extends BaseRestServiceImpl implements EvasioneApi {

	@EJB
	private EvasioneFacade evasioneFacade;
	@EJB
	private ExposedFacade exposedFacade; // da cancellare

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
	public Response postTestataEvasioneFromDocumentoTrasporto(UUID idSettore, DocumentoTrasporto documentoTrasporto, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> evasioneFacade.postTestataEvasioneFromDocumentoTrasporto(documentoTrasporto, idSettore));
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
	public Response postRicercaDdtEvasione(@Min(0) @QueryParam("offset") Integer offset,
										   @Min(1) @Max(100) @QueryParam("limit") Integer limit,
										   @QueryParam("sort") @DefaultValue("") String sort,
										   @QueryParam("direction") @DefaultValue("asc") String direction,
										   RicercaDdt ricercaDDT,
										   SecurityContext securityContext,
										   HttpHeaders httpHeaders,
										   HttpServletRequest httpRequest) {
		return invoke(() -> evasioneFacade.ricercaDocumentoTrasporto(ricercaDDT, offset, limit, sort, direction));
	}

	@Override
	public Response getElaborazioneDocumentoTrasportoScartatoById(
		Integer idDocumentoTrasporto, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		return invoke(() -> evasioneFacade.getElaborazioneDocumentoTrasportoScartatoById(idDocumentoTrasporto));
	}

	@Override
	public Response getRiepilogoFatturaByIdEvasione(UUID idEvasione, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> evasioneFacade.getRiepilogoFatturaByIdEvasione(idEvasione));
	}

	@Override
	public Response getEsposizioneImpegniByRigaOrdine(UUID idRigaEvasione, Boolean bDistribuzioneTotaleRigaSugliImpegni, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> evasioneFacade.getEsposizioneImpegniByRigaOrdine(idRigaEvasione, bDistribuzioneTotaleRigaSugliImpegni));
	}

	@Override
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
				ricercaEvasioni.getProvvedimentoTipo(), ricercaEvasioni.getTestataEvasione(), ricercaEvasioni.getDestinatarioEvasione(), ricercaEvasioni.getImpegno(), ricercaEvasioni.getSubimpegno(), ricercaEvasioni.getOggettiSpesa()
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
	public Response putEvasioneControllaById(UUID id, ControllaEvasione controllaEvasione, Boolean perAutorizzazione, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> evasioneFacade.putEvasioneControllaById(id, controllaEvasione, perAutorizzazione));
	}

	@Override
	public Response putEvasioneAutorizzaById(UUID id, TestataEvasione testataEvasione, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> evasioneFacade.putEvasioneAutorizzaById(id, testataEvasione));
	}

	@Override
	public Response putEvasioneConfermaById(UUID id, TestataEvasione testataEvasione, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> evasioneFacade.putEvasioneConfermaById(id, testataEvasione));
	}

	@Override
	public Response putEvasioneInviaContabilitaById(UUID id, Boolean bypassControls,Boolean saltaVerificaCongruenzaTotali, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> evasioneFacade.putEvasioneInviaContabilitaById(id, bypassControls, saltaVerificaCongruenzaTotali));
	}

	@Override
	public Response getEvasioniCollegatePerFattura(UUID id, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> evasioneFacade.getEvasioniCollegatePerFattura(id));
	}

	@Override
	public Response getDocumentoTrasportoByEvasione(UUID idEvasione, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> evasioneFacade.getDocumentoTrasportoByEvasione(idEvasione));
	}

	@Override
	public Response putRigaEvasioneById(UUID idRiga, Integer qtaDaEvadere, String totaliCoerenti, RigaEvasione rigaEvasione, SecurityContext securityContext,HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> evasioneFacade.putRigaEvasioneById(idRiga, rigaEvasione, qtaDaEvadere, totaliCoerenti));

	}

	@Override
	public Response verificaEvasione(Evasione evasioni, SecurityContext securityContext,
			HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return invoke(() -> exposedFacade.verificaEvasione(evasioni));
	}

	@Override
	public Response getControlloSettoreAttivoSuDestinatario(UUID idDestinatarioEvasione, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> evasioneFacade.getControlloSettoreAttivoSuDestinatario(idDestinatarioEvasione));
	}
}
