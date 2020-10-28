/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.facade;

import java.util.Date;
import java.util.UUID;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneTipoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.FornitoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SubimpegnoEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.evasione.DeleteDestinatarioEvasioneService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.evasione.DeleteEvasioneService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.evasione.DeleteImpegniEvasioneByRigaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.evasione.DeleteRigaEvasioneService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.evasione.GetEsposizioneImpegniByRigaOrdineService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.evasione.GetRicercaEvasioniService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.evasione.GetRicercaImpegniByRigaEvasioneService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.evasione.GetRicercaRigheByDestinatarioEvasioneService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.evasione.GetRiepilogoFatturaByIdEvasioneService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.evasione.GetRigheOrdineDaEvadereByOrdineAnnoNumeroService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.evasione.GetTestataEvasioneByAnnoENumService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.evasione.GetTestataEvasioneByIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.evasione.PostImpegniEvasioneService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.evasione.PostTestataEvasioneService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.evasione.PutDestinatarioEvasioneService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.evasione.PutEvasioneAnnullaByIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.evasione.PutEvasioneAutorizzaByIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.evasione.PutEvasioneControllaByIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.evasione.PutEvasioneInviaContabilitaByIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.evasione.PutEvasioneVerifichePreliminariAnnullaByIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.evasione.PutImpegniEvasioneService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.evasione.PutTestataEvasionePerRiepilogoFatturaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.evasione.PutTestataEvasioneService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.DeleteDestinatarioEvasioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.DeleteEvasioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.DeleteImpegniEvasioneByRigaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.DeleteRigaEvasioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.GetEsposizioneImpegniByRigaOrdineRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.GetRicercaEvasioniRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.GetRicercaImpegniByRigaEvasioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.GetRicercaRigheByDestinatarioEvasioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.GetRiepilogoFatturaByIdEvasioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.GetRigheOrdineDaEvadereByOrdineAnnoNumeroRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.GetTestataEvasioneByAnnoENumRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.GetTestataEvasioneByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.PostImpegniEvasioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.PostTestataEvasioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.PutDestinatarioEvasioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.PutEvasioneAnnullaByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.PutEvasioneAutorizzaByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.PutEvasioneControllaByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.PutEvasioneInviaContabilitaByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.PutEvasioneVerifichePreliminariAnnullaByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.PutImpegniEvasioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.PutTestataEvasionePerRiepilogoFatturaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.PutTestataEvasioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.DeleteDestinatarioEvasioneResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.DeleteEvasioneResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.DeleteImpegniEvasioneByRigaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.DeleteRigaEvasioneResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.GetEsposizioneImpegniByRigaOrdineResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.GetRicercaEvasioniResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.GetRicercaImpegniByRigaEvasioneResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.GetRicercaRigheByDestinatarioEvasioneResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.GetRiepilogoFatturaByIdEvasioneResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.GetRigheOrdineDaEvadereByOrdineAnnoNumeroResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.GetTestataEvasioneByAnnoENumResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.GetTestataEvasioneByIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.PostImpegniEvasioneResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.PostTestataEvasioneResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.PutDestinatarioEvasioneResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.PutEvasioneAnnullaByIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.PutEvasioneAutorizzaByIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.PutEvasioneControllaByIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.PutEvasioneInviaContabilitaByIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.PutEvasioneVerifichePreliminariAnnullaByIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.PutImpegniEvasioneResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.PutTestataEvasionePerRiepilogoFatturaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.PutTestataEvasioneResponse;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.Subimpegno;
import it.csi.cpass.cpassbe.lib.dto.ord.OggettiSpesa;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.ControllaEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DestinatarioEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.SalvaEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.SalvaImpegniEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.TestataEvasione;

/**
 * Fa&ccedil;ade for the /evasione path
 */
@Stateless
public class EvasioneFacade extends BaseFacade {

	// Injection point
	@Inject
	private TestataEvasioneDad testataEvasioneDad;
	@Inject
	private DestinatarioEvasioneDad destinatarioEvasioneDad;
	@Inject
	private RigaEvasioneDad rigaEvasioneDad;
	@Inject
	private ImpegnoEvasioneDad impegnoEvasioneDad;
	@Inject
	private SubimpegnoEvasioneDad subimpegnoEvasioneDad;

	@Inject
	private TestataOrdineDad testataOrdineDad;
	@Inject
	private DestinatarioOrdineDad destinatarioOrdineDad;
	@Inject
	private RigaOrdineDad rigaOrdineDad;

	@Inject
	private ImpegnoDad impegnoDad;
	@Inject
	private FornitoreDad fornitoreDad;

	@Inject
	private DecodificaDad decodificaDad;
	@Inject
	private SystemDad systemDad;
	@Inject
	private UtenteDad utenteDad;
	@Inject
	private SettoreDad settoreDad;
	@Inject
	private ElaborazioneDad elaborazioneDad;
	@Inject
	private ElaborazioneTipoDad elaborazioneTipoDad;

	/**
	 * 
	 * @param anno
	 * @param numero
	 * @return
	 */
	public GetRigheOrdineDaEvadereByOrdineAnnoNumeroResponse getRigheOrdineDaEvadereByOrdineAnnoNumero(Integer anno, Integer numero) {
		return executeService(new GetRigheOrdineDaEvadereByOrdineAnnoNumeroRequest(anno, numero), new GetRigheOrdineDaEvadereByOrdineAnnoNumeroService(
				configurationHelper, testataOrdineDad, destinatarioOrdineDad, rigaOrdineDad, impegnoDad, decodificaDad, rigaEvasioneDad, utenteDad));
	}

	/**
	 * Posts an TestataEvasione
	 * 
	 * @param testataEvasione
	 * @return the testataEvasione
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PostTestataEvasioneResponse postTestataEvasione(SalvaEvasione salvaEvasione) {
		return executeService(new PostTestataEvasioneRequest(salvaEvasione),
				new PostTestataEvasioneService(configurationHelper, testataEvasioneDad, destinatarioEvasioneDad, rigaEvasioneDad, impegnoEvasioneDad,
						subimpegnoEvasioneDad, destinatarioOrdineDad, rigaOrdineDad, impegnoDad, decodificaDad, settoreDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PutTestataEvasioneResponse putTestataEvasioneById(UUID id, TestataEvasione testataEvasione) {
		return executeService(new PutTestataEvasioneRequest(setId(id, testataEvasione)),
				new PutTestataEvasioneService(configurationHelper, testataEvasioneDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PutTestataEvasionePerRiepilogoFatturaResponse putTestataEvasionePerRiepilogoFattura(UUID id, TestataEvasione testataEvasione, Boolean bypassControl,
			Boolean bypassFornitoreControl) {
		return executeService(new PutTestataEvasionePerRiepilogoFatturaRequest(setId(id, testataEvasione), bypassControl, bypassFornitoreControl),
				new PutTestataEvasionePerRiepilogoFatturaService(configurationHelper, externalHelperLookup, testataEvasioneDad, systemDad, utenteDad));
	}

	public GetTestataEvasioneByIdResponse getTestataEvasioneById(UUID id) {
		return executeService(new GetTestataEvasioneByIdRequest(id), new GetTestataEvasioneByIdService(configurationHelper, testataEvasioneDad));
	}

	public GetRicercaRigheByDestinatarioEvasioneResponse getRigheByDestinatarioEvasione(UUID id) {
		return executeService(new GetRicercaRigheByDestinatarioEvasioneRequest(id),
				new GetRicercaRigheByDestinatarioEvasioneService(configurationHelper, rigaEvasioneDad));
	}

	public GetRicercaImpegniByRigaEvasioneResponse getImpegniByRigaEvasione(UUID id) {
		return executeService(new GetRicercaImpegniByRigaEvasioneRequest(id),
				new GetRicercaImpegniByRigaEvasioneService(configurationHelper, impegnoEvasioneDad));
	}

	public GetRiepilogoFatturaByIdEvasioneResponse getRiepilogoFatturaByIdEvasione(UUID id) {
		return executeService(new GetRiepilogoFatturaByIdEvasioneRequest(id),
				new GetRiepilogoFatturaByIdEvasioneService(configurationHelper, testataEvasioneDad));
	}

	public GetTestataEvasioneByAnnoENumResponse getTestataEvasioneByAnnoENum(Integer anno, Integer numero, UUID ente) {
		return executeService(new GetTestataEvasioneByAnnoENumRequest(anno, numero, ente),
				new GetTestataEvasioneByAnnoENumService(configurationHelper, testataEvasioneDad, utenteDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PutDestinatarioEvasioneResponse putEvasioneDestinatario(DestinatarioEvasione destinatario) {
		return executeService(new PutDestinatarioEvasioneRequest(destinatario),
				new PutDestinatarioEvasioneService(configurationHelper, destinatarioEvasioneDad, settoreDad));
	}

	public GetEsposizioneImpegniByRigaOrdineResponse getEsposizioneImpegniByRigaOrdine(UUID idRigaEvasione, Boolean bDistribuzioneTotaleRigaSugliImpegni) {
		return executeService(new GetEsposizioneImpegniByRigaOrdineRequest(idRigaEvasione, bDistribuzioneTotaleRigaSugliImpegni),
				new GetEsposizioneImpegniByRigaOrdineService(configurationHelper, rigaEvasioneDad, impegnoDad, impegnoEvasioneDad, subimpegnoEvasioneDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PostImpegniEvasioneResponse postImpegniEvasione(SalvaImpegniEvasione salvaImpegniEvasione) {
		return executeService(new PostImpegniEvasioneRequest(salvaImpegniEvasione),
				new PostImpegniEvasioneService(configurationHelper, externalHelperLookup, impegnoEvasioneDad, subimpegnoEvasioneDad, impegnoDad, fornitoreDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PutImpegniEvasioneResponse putImpegniEvasione(SalvaImpegniEvasione salvaImpegniEvasione) {
		return executeService(new PutImpegniEvasioneRequest(salvaImpegniEvasione),
				new PutImpegniEvasioneService(configurationHelper, externalHelperLookup, impegnoEvasioneDad, subimpegnoEvasioneDad, impegnoDad, fornitoreDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetRicercaEvasioniResponse getRicercaEvasioni(Integer page, Integer limit, String sort, String direction, Integer annoEvasioneDa,
			Integer numeroEvasioneDa, Integer annoEvasioneA, Integer numeroEvasioneA, Date dataInserimentoDa, Date dataInserimentoA, Integer annoOrdineDa,
			Integer numeroOrdineDa, Integer annoOrdineA, Integer numeroOrdineA, Date dataEmissioneDa, Date dataEmissioneA, Integer annoProvvedimento,
			Integer numeroProvvedimento, TestataEvasione testataEvasione, DestinatarioEvasione destinatarioEvasione, Impegno impegno, Subimpegno subimpegno,
			OggettiSpesa oggettiSpesa) {
		return executeService(
				new GetRicercaEvasioniRequest(page, limit, sort, direction, annoEvasioneDa, numeroEvasioneDa, annoEvasioneA, numeroEvasioneA, dataInserimentoDa,
						dataInserimentoA, annoOrdineDa, numeroOrdineDa, annoOrdineA, numeroOrdineA, dataEmissioneDa, dataEmissioneA, annoProvvedimento,
						numeroProvvedimento, testataEvasione, destinatarioEvasione, impegno, subimpegno, oggettiSpesa),
				new GetRicercaEvasioniService(configurationHelper, testataEvasioneDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public DeleteDestinatarioEvasioneResponse deleteDestinatarioEvasione(UUID id) {
		return executeService(new DeleteDestinatarioEvasioneRequest(id),
				new DeleteDestinatarioEvasioneService(configurationHelper, testataEvasioneDad, destinatarioEvasioneDad, rigaEvasioneDad, impegnoEvasioneDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public DeleteRigaEvasioneResponse deleteRigaEvasione(UUID id) {
		return executeService(new DeleteRigaEvasioneRequest(id),
				new DeleteRigaEvasioneService(configurationHelper, rigaEvasioneDad, impegnoEvasioneDad, testataEvasioneDad, destinatarioEvasioneDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public DeleteImpegniEvasioneByRigaResponse deleteImpegniEvasioneByRiga(UUID rigaEvasioneId) {
		return executeService(new DeleteImpegniEvasioneByRigaRequest(rigaEvasioneId),
				new DeleteImpegniEvasioneByRigaService(configurationHelper, impegnoEvasioneDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public DeleteEvasioneResponse deleteEvasione(UUID testataEvasioneId) {
		return executeService(new DeleteEvasioneRequest(testataEvasioneId), new DeleteEvasioneService(configurationHelper, decodificaDad, testataEvasioneDad,
				destinatarioEvasioneDad, rigaEvasioneDad, impegnoEvasioneDad, destinatarioOrdineDad, rigaOrdineDad));
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PutEvasioneVerifichePreliminariAnnullaByIdResponse putEvasioneVerifichePreliminariAnnullaById(UUID id) {
		return executeService(new PutEvasioneVerifichePreliminariAnnullaByIdRequest(id),
				new PutEvasioneVerifichePreliminariAnnullaByIdService(configurationHelper, externalHelperLookup, decodificaDad, systemDad, testataEvasioneDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PutEvasioneAnnullaByIdResponse putEvasioneAnnullaById(UUID id) {
		return executeService(new PutEvasioneAnnullaByIdRequest(id),
				new PutEvasioneAnnullaByIdService(configurationHelper, externalHelperLookup, decodificaDad, testataEvasioneDad, destinatarioEvasioneDad,
						rigaEvasioneDad, impegnoEvasioneDad, destinatarioOrdineDad, rigaOrdineDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PutEvasioneControllaByIdResponse putEvasioneControllaById(UUID id, ControllaEvasione controllaEvasione) {
		return executeService(new PutEvasioneControllaByIdRequest(id, controllaEvasione), new PutEvasioneControllaByIdService(configurationHelper,
				externalHelperLookup, testataEvasioneDad, rigaEvasioneDad, impegnoEvasioneDad, subimpegnoEvasioneDad, systemDad, settoreDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PutEvasioneAutorizzaByIdResponse putEvasioneAutorizzaById(UUID id, TestataEvasione testataEvasione) {
		return executeService(new PutEvasioneAutorizzaByIdRequest(id, testataEvasione),
				new PutEvasioneAutorizzaByIdService(configurationHelper, testataEvasioneDad, decodificaDad));
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PutEvasioneInviaContabilitaByIdResponse putEvasioneInviaContabilitaById(UUID id) {
		return executeService(new PutEvasioneInviaContabilitaByIdRequest(id),
				new PutEvasioneInviaContabilitaByIdService(configurationHelper, externalHelperLookup, testataEvasioneDad, destinatarioEvasioneDad,
						rigaEvasioneDad, impegnoEvasioneDad, subimpegnoEvasioneDad, decodificaDad, elaborazioneDad, systemDad, elaborazioneTipoDad));
	}

}
