/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.facade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.jboss.ejb3.annotation.TransactionTimeout;

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DocumentaleDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.FornitoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ProvvedimentoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RdaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RmsDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ScaricoMepaTestataDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.StampeDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SubimpegnoEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.DeleteDestinatarioOrdineService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.DeleteImpegniByRigaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.DeleteRigaOrdineService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.DeleteTestataOrdineService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.GetAnteprimeOrdineByEvasioneIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.GetDestinatariOrdinePerCopiaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.GetDocumentiByOrdineTestataIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.GetDocumentoByIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.GetRicercaImpegniByRigaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.GetRicercaOrdiniService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.GetRicercaRigheByDestinatarioService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.GetRicercaRigheDaEvadereService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.GetRicercaSezioneService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.GetRiepilogoImpegniByOrdineIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.GetTestataOrdineByAnnoENumService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.GetTestataOrdineByIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.GetTestateOrdineByDestinatarioEvasioneIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.GetTestateOrdineByEvasioneIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.GetTestateOrdineByRigaEvasioneIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.InviaOrdineNSOService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.InviaOrdineRevocaNSOService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.PostAnnullaTestataOrdineService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.PostControlliInvioInFirmaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.PostCopiaRigheService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.PostDestinatarioOrdineService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.PostImpegniService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.PostRicercaImpegnoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.PostRicercaProvvedimentoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.PostRigaOrdineService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.PostTestataOrdineDerivatoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.PostTestataOrdineService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.PutDestinatarioOrdineService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.PutOrdineAutorizzaByIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.PutOrdineChiudiByIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.PutOrdineConfermaByIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.PutOrdineControllaByIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.PutOrdineVerificheFattibilitaChiudiByIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.PutRigaOrdineService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.PutTestataOrdineService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.DeleteDestinatarioOrdineRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.DeleteImpegniByRigaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.DeleteRigaOrdineRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.DeleteTestataOrdineRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.GetAnteprimeOrdineByEvasioneIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.GetDestinatariOrdinePerCopiaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.GetDocumentiByOrdineTestataIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.GetDocumentoByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.GetRicercaImpegniByRigaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.GetRicercaOrdiniRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.GetRicercaRigheByDestinatarioRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.GetRicercaRigheDaEvadereRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.GetRicercaSezioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.GetRiepilogoImpegniByOrdineIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.GetTestataOrdineByAnnoENumRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.GetTestataOrdineByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.GetTestateOrdineByDestinatarioEvasioneIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.GetTestateOrdineByEvasioneIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.GetTestateOrdineByRigaEvasioneIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.InviaOrdineNSORequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PostAnnullaTestataOrdineRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PostControlliInvioInFirmaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PostCopiaRigheRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PostDestinatarioOrdineRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PostImpegniRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PostRicercaImpegnoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PostRicercaProvvedimentoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PostRigaOrdineRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PostTestataOrdineRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PutDestinatarioOrdineRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PutOrdineAutorizzaByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PutOrdineChiudiByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PutOrdineConfermaByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PutOrdineControllaByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PutOrdineVerificheFattibilitaChiudiByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PutRigaOrdineRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PutTestataOrdineRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.DeleteDestinatarioOrdineResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.DeleteImpegniByRigaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.DeleteRigaOrdineResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.DeleteTestataOrdineResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.GetAnteprimeOrdineByEvasioneIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.GetDestinatariOrdinePerCopiaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.GetDocumentiByOrdineTestataIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.GetDocumentoByIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.GetRicercaImpegniByRigaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.GetRicercaOrdiniResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.GetRicercaRigheByDestinatarioResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.GetRicercaRigheDaEvadereResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.GetRicercaSezioneResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.GetRiepilogoImpegniByOrdineIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.GetTestataOrdineByAnnoENumResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.GetTestataOrdineByIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.GetTestateOrdineByDestinatarioEvasioneIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.GetTestateOrdineByEvasioneIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.GetTestateOrdineByRigaEvasioneIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.InviaOrdineNSOResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PostAnnullaTestataOrdineResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PostControlliInvioInFirmaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PostCopiaRigheResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PostDestinatarioOrdineResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PostImpegniResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PostRicercaImpegnoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PostRicercaProvvedimentoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PostRigaOrdineResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PostTestataOrdineDerivatoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PostTestataOrdineResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PutDestinatarioOrdineResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PutOrdineAutorizzaByIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PutOrdineChiudiByIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PutOrdineConfermaByIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PutOrdineControllaByIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PutOrdineVerificheFattibilitaChiudiByIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PutRigaOrdineResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PutTestataOrdineResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.documentale.GetProtocollaOrdineResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.documentale.PostArchiviaOrdineResponse;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro.ChiaveEnum;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoOrdineEnum;
import it.csi.cpass.cpassbe.ejb.util.ConstantsDecodifiche.TipoOrdineEnum;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.Ods;
import it.csi.cpass.cpassbe.lib.dto.Parametro;
import it.csi.cpass.cpassbe.lib.dto.Provvedimento;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.SettoreIndirizzo;
import it.csi.cpass.cpassbe.lib.dto.Subimpegno;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.FiltroImpegni;
import it.csi.cpass.cpassbe.lib.dto.ord.ProtocolloOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.SalvaImpegni;
import it.csi.cpass.cpassbe.lib.dto.ord.Sezione;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Fa&ccedil;ade for the /testata-ordine path
 */
@Stateless
@Lock(LockType.READ)
public class OrdineFacade extends BaseFacade {

	@EJB private OrdineFacade self;

	// Injection point
	@Inject
	private TestataOrdineDad testataOrdineDad;
	@Inject
	private DestinatarioOrdineDad destinatarioOrdineDad;
	@Inject
	private RigaOrdineDad rigaOrdineDad;
	@Inject
	private ImpegnoOrdineDad impegnoOrdineDad;
	@Inject
	private ImpegnoDad impegnoDad;
	@Inject
	private DestinatarioEvasioneDad destinatarioEvasioneDad;
	@Inject
	private ImpegnoEvasioneDad impegnoEvasioneDad;
	@Inject
	private SubimpegnoEvasioneDad subimpegnoEvasioneDad;
	@Inject
	private DecodificaDad decodificaDad;
	@Inject
	private SettoreDad settoreDad;
	@Inject
	private SystemDad systemDad;
	@Inject
	private UtenteDad utenteDad;
	@Inject
	private CommonDad commonDad;
	@Inject
	private FornitoreDad fornitoreDad;
	@Inject
	private ProvvedimentoDad provvedimentoDad;
	@Inject
	private ScaricoMepaTestataDad scaricoMepaTestataDad;
	@Inject
	private RmsDad rmsDad;
	@Inject
	private RdaDad rdaDad;
	@Inject
	private DocumentaleDad documentaleDad;
	@Inject
	private StampeDad stampeDad;
	@Inject
	private DocumentaleFacade documentaleFacade;

	/**
	 * Posts an TestataOrdine
	 *
	 * @param testataOrdine
	 * @return the PostTestataOrdineResponse
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PostTestataOrdineResponse postTestataOrdine(TestataOrdine testataOrdine) {
		return executeService(new PostTestataOrdineRequest(testataOrdine),new PostTestataOrdineService(configurationHelper, externalHelperLookup, testataOrdineDad, decodificaDad,settoreDad));
	}

	/**
	 * Posts an TestataOrdine da Rda
	 *
	 * @param testataOrdine
	 * @return the testataOrdine
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PostTestataOrdineDerivatoResponse postTestataOrdineDerivato(TestataOrdine testataOrdine) {
		return executeService(new PostTestataOrdineRequest(testataOrdine),
				new PostTestataOrdineDerivatoService(configurationHelper, externalHelperLookup, testataOrdineDad, decodificaDad, rmsDad, destinatarioOrdineDad, rigaOrdineDad, rdaDad
						,utenteDad,systemDad,impegnoDad,commonDad,settoreDad));
	}

	/**
	 * Puts an TestataOrdine
	 *
	 * @param id            the id
	 * @param testataOrdine
	 * @return the testataOrdine
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PutTestataOrdineResponse putTestataOrdineById(UUID id, TestataOrdine testataOrdine) {
		return executeService(new PutTestataOrdineRequest(setId(id, testataOrdine)),
				new PutTestataOrdineService(configurationHelper, testataOrdineDad, decodificaDad,settoreDad));
	}

	/**
	 * Delete a TestataOrdine
	 *
	 * @param id the id
	 * @return nothing
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public DeleteTestataOrdineResponse deleteTestataOrdineById(UUID id) {
		return executeService(new DeleteTestataOrdineRequest(id),
				new DeleteTestataOrdineService(configurationHelper, testataOrdineDad, destinatarioOrdineDad, rigaOrdineDad,
						impegnoDad, decodificaDad, scaricoMepaTestataDad, rmsDad, rdaDad,settoreDad));
	}

	/**
	 * Annulla una TestataOrdine
	 *
	 * @param testataOrdine
	 * @return the testataOrdine
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PostAnnullaTestataOrdineResponse postAnnullaTestataOrdine(TestataOrdine testataOrdine, boolean bypassControlli) {
		return executeService(new PostAnnullaTestataOrdineRequest(testataOrdine, bypassControlli), new PostAnnullaTestataOrdineService(configurationHelper,
				testataOrdineDad, destinatarioOrdineDad, rigaOrdineDad, impegnoDad, systemDad, utenteDad, decodificaDad, rdaDad, rmsDad, scaricoMepaTestataDad,settoreDad));
	}

	public GetTestataOrdineByIdResponse getTestataOrdineById(UUID id) {
		return executeService(new GetTestataOrdineByIdRequest(id),
				new GetTestataOrdineByIdService(configurationHelper, testataOrdineDad, impegnoDad, systemDad, utenteDad,settoreDad,documentaleDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PostRicercaProvvedimentoResponse postRicercaProvvedimento(Provvedimento provvedimento) {
		return executeService(new PostRicercaProvvedimentoRequest(provvedimento),new PostRicercaProvvedimentoService(configurationHelper, externalHelperLookup, provvedimentoDad, settoreDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PostRicercaImpegnoResponse postRicercaImpegno(FiltroImpegni filtroImpegni, Integer offset, Integer limit, String sort, String direction) {
		return executeService(new PostRicercaImpegnoRequest(offset, limit, sort, direction, filtroImpegni),
				new PostRicercaImpegnoService(configurationHelper, externalHelperLookup, impegnoDad, settoreDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PostDestinatarioOrdineResponse postOrdineDestinatario(Destinatario destinatario) {
		return executeService(new PostDestinatarioOrdineRequest(destinatario),
				new PostDestinatarioOrdineService(configurationHelper, destinatarioOrdineDad, settoreDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PostRigaOrdineResponse postRigaOrdine(RigaOrdine rigaOrdine, Boolean bypassControlloIva) {
		return executeService(new PostRigaOrdineRequest(rigaOrdine, bypassControlloIva), new PostRigaOrdineService(configurationHelper, externalHelperLookup,
				rigaOrdineDad, destinatarioOrdineDad, decodificaDad, impegnoDad, systemDad, utenteDad, commonDad, testataOrdineDad, fornitoreDad,settoreDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PostCopiaRigheResponse postCopiaRighe(UUID idFrom, UUID idTo) {
		return executeService(new PostCopiaRigheRequest(idFrom, idTo),
				new PostCopiaRigheService(configurationHelper, externalHelperLookup, rigaOrdineDad, testataOrdineDad, destinatarioOrdineDad, decodificaDad, impegnoDad,settoreDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PutDestinatarioOrdineResponse putOrdineDestinatario(Destinatario destinatario) {
		return executeService(new PutDestinatarioOrdineRequest(destinatario),
				new PutDestinatarioOrdineService(configurationHelper, destinatarioOrdineDad, settoreDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PutRigaOrdineResponse putRigaOrdine(RigaOrdine rigaOrdine, Boolean bypassControlloIva) {
		return executeService(new PutRigaOrdineRequest(rigaOrdine, bypassControlloIva), new PutRigaOrdineService(configurationHelper, rigaOrdineDad,
				decodificaDad, systemDad, utenteDad, testataOrdineDad, destinatarioOrdineDad, commonDad, fornitoreDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public DeleteDestinatarioOrdineResponse deleteDestinatario(UUID id) {
		return executeService(new DeleteDestinatarioOrdineRequest(id),
				new DeleteDestinatarioOrdineService(configurationHelper, destinatarioOrdineDad, rigaOrdineDad, impegnoDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public DeleteRigaOrdineResponse deleteRigaOrdine(UUID id) {
		return executeService(new DeleteRigaOrdineRequest(id),
				new DeleteRigaOrdineService(configurationHelper, rigaOrdineDad, impegnoDad, testataOrdineDad, destinatarioOrdineDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public DeleteImpegniByRigaResponse deleteImpegniByRiga(UUID rigaOrdineId) {
		return executeService(new DeleteImpegniByRigaRequest(rigaOrdineId), new DeleteImpegniByRigaService(configurationHelper, impegnoDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.READ)
	public GetTestataOrdineByAnnoENumResponse getTestataOrdineByAnnoENum(Integer anno, Integer numero, UUID ente) {
		return executeService(new GetTestataOrdineByAnnoENumRequest(anno, numero, ente),
				new GetTestataOrdineByAnnoENumService(configurationHelper, testataOrdineDad, utenteDad,settoreDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.READ)
	public GetDestinatariOrdinePerCopiaResponse getDestinatariPerCopia(UUID idOrdine) {
		return executeService(new GetDestinatariOrdinePerCopiaRequest(idOrdine), new GetDestinatariOrdinePerCopiaService(configurationHelper, destinatarioOrdineDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.READ)
	public GetRicercaRigheByDestinatarioResponse getRigheByDestinatario(UUID id) {
		return executeService(new GetRicercaRigheByDestinatarioRequest(id), new GetRicercaRigheByDestinatarioService(configurationHelper, rigaOrdineDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.READ)
	public GetRicercaImpegniByRigaResponse getImpegniByRiga(UUID id) {
		return executeService(new GetRicercaImpegniByRigaRequest(id), new GetRicercaImpegniByRigaService(configurationHelper, impegnoDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	@TransactionTimeout(value=120, unit=TimeUnit.SECONDS)
	public PostImpegniResponse postImpegni(SalvaImpegni salvaImpegni) {
		return executeService(new PostImpegniRequest(salvaImpegni), new PostImpegniService(configurationHelper, externalHelperLookup, impegnoDad, settoreDad));
	}

	@Lock(LockType.READ)
	public GetRiepilogoImpegniByOrdineIdResponse getRiepilogoImpegniByOrdineId(UUID id) {
		return executeService(new GetRiepilogoImpegniByOrdineIdRequest(id),
				new GetRiepilogoImpegniByOrdineIdService(configurationHelper, testataOrdineDad, impegnoDad, systemDad,settoreDad));
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PutOrdineControllaByIdResponse putOrdineControllaById(UUID id, TestataOrdine testataOrdine) {
		return executeService(new PutOrdineControllaByIdRequest(id, testataOrdine), new PutOrdineControllaByIdService(configurationHelper, externalHelperLookup,
				testataOrdineDad, rigaOrdineDad, impegnoDad, systemDad, settoreDad));
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PutOrdineConfermaByIdResponse putOrdineConfermaById(UUID id, TestataOrdine testataOrdine) {
		return executeService(new PutOrdineConfermaByIdRequest(id, testataOrdine),
				new PutOrdineConfermaByIdService(configurationHelper, testataOrdineDad, rigaOrdineDad, decodificaDad,settoreDad));
	}

	//@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PutOrdineAutorizzaByIdResponse putOrdineAutorizzaById(UUID id, TestataOrdine testataOrdine) {
		final Ente ente = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte();

		//Parametro paramSysDocument = systemDad.getParametro(ChiaveEnum.SISTEMA_DOCUMENTALE.name(), ConstantsCPassParametro.RiferimentoEnum.NULL.getCostante(),ente.getId());
		final Parametro paramSysDocument = systemDad.getParametro(ChiaveEnum.SISTEMA_DOCUMENTALE.name(), ente.getId());
		final boolean noProtocollo = (paramSysDocument != null && paramSysDocument.getValore().equals("NO PROTOCOLLO"));
		String tipDocu = testataOrdine.getTipoOrdine().getTipologiaDocumentoCodice();
		if (
				(!TipoOrdineEnum.INTERNO.getCodice().equals(tipDocu)) 
				&& (!TipoOrdineEnum.MEPA.getCodice().equals(tipDocu)) 	
				&& !noProtocollo
				) {
			testataOrdine.getSettore().getFirma();
			// da ripristinare
			if(!StatoOrdineEnum.IN_FIRMA.getCostante().equals(testataOrdine.getStato().getCodice())) {
				//if (!"DWD".equalsIgnoreCase(firma)) {
				final PostArchiviaOrdineResponse postArchiviaOrdineResponse = documentaleFacade.getArchiviaOrdine(id, Boolean.FALSE);
				if(postArchiviaOrdineResponse.getApiErrors()!= null && postArchiviaOrdineResponse.getApiErrors().size()>0) {
					final PutOrdineAutorizzaByIdResponse ris = new PutOrdineAutorizzaByIdResponse();
					ris.setApiErrors(postArchiviaOrdineResponse.getApiErrors());
					return ris;
				}
				//protocolla
				if (postArchiviaOrdineResponse.getProtocolloOrdine()!=null && postArchiviaOrdineResponse.getProtocolloOrdine().getAnnoProtocollo() == null) {
					// se protocollato
					final GetProtocollaOrdineResponse getProtocollaOrdineResponse = documentaleFacade.getProtocollaOrdine(testataOrdine, postArchiviaOrdineResponse.getProtocolloOrdine());
					if(getProtocollaOrdineResponse.getApiErrors()!= null && getProtocollaOrdineResponse.getApiErrors().size()>0) {
						final PutOrdineAutorizzaByIdResponse ris = new PutOrdineAutorizzaByIdResponse();
						ris.setApiErrors(getProtocollaOrdineResponse.getApiErrors());
						return ris;
					}
					final List<ProtocolloOrdine> listProt = new ArrayList<>();
					listProt.add(getProtocollaOrdineResponse.getProtocolloOrdine());
					testataOrdine.setProtocolloOrdines(listProt);
				}
			}
		}
		return self.putAutorizzaById( id,  testataOrdine);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public PutOrdineAutorizzaByIdResponse putAutorizzaById(UUID id, TestataOrdine testataOrdine) {
		return executeService(new PutOrdineAutorizzaByIdRequest(id, testataOrdine),new PutOrdineAutorizzaByIdService(configurationHelper, testataOrdineDad, rigaOrdineDad, decodificaDad,settoreDad,documentaleDad,commonDad,stampeDad,utenteDad,fornitoreDad,systemDad,externalHelperLookup));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public InviaOrdineNSOResponse inviaOrdineInzialeNSO(UUID id) {
		return executeService(new InviaOrdineNSORequest(id),new InviaOrdineNSOService(configurationHelper, testataOrdineDad, destinatarioOrdineDad, rigaOrdineDad, decodificaDad,impegnoDad, settoreDad, externalHelperLookup));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public InviaOrdineNSOResponse inviaOrdineRevocaNSO(UUID id) {
		return executeService(new InviaOrdineNSORequest(id),new InviaOrdineRevocaNSOService(configurationHelper, testataOrdineDad, destinatarioOrdineDad, rigaOrdineDad, decodificaDad,impegnoDad, settoreDad, externalHelperLookup));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PutOrdineVerificheFattibilitaChiudiByIdResponse putOrdineVerificheFattibilitaChiudiById(UUID id) {
		return executeService(new PutOrdineVerificheFattibilitaChiudiByIdRequest(id),
				new PutOrdineVerificheFattibilitaChiudiByIdService(configurationHelper, testataOrdineDad, destinatarioOrdineDad, destinatarioEvasioneDad, systemDad, settoreDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PutOrdineChiudiByIdResponse putOrdineChiudiById(UUID id) {
		return executeService(new PutOrdineChiudiByIdRequest(id),
				new PutOrdineChiudiByIdService(configurationHelper, testataOrdineDad, rigaOrdineDad, impegnoOrdineDad, impegnoDad,
						impegnoEvasioneDad, subimpegnoEvasioneDad, systemDad, settoreDad, decodificaDad, destinatarioOrdineDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.READ)
	public GetRicercaOrdiniResponse getRicercaOrdini(Integer page, Integer limit, String sort, String direction, Integer annoOrdineDa, Integer numeroOrdineDa,
			Integer annoOrdineA, Integer numeroOrdineA, Date dataEmissioneDa, Date dataEmissioneA, TestataOrdine testataOrdine,
			Impegno impegno, Subimpegno subimpegno, RigaOrdine rigaOrdine, Settore settoreEmittente, Settore settore, SettoreIndirizzo settoreIndirizzo) {
		return executeService(
				new GetRicercaOrdiniRequest(page, limit, sort, direction, annoOrdineDa, numeroOrdineDa, annoOrdineA, numeroOrdineA, dataEmissioneDa,
						dataEmissioneA, testataOrdine, impegno, subimpegno, rigaOrdine, settoreEmittente, settore, settoreIndirizzo),
				new GetRicercaOrdiniService(configurationHelper, testataOrdineDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.READ)
	public GetTestateOrdineByEvasioneIdResponse getTestateOrdineByEvasioneId(UUID idEvasione) {
		return executeService(new GetTestateOrdineByEvasioneIdRequest(idEvasione),
				new GetTestateOrdineByEvasioneIdService(configurationHelper, testataOrdineDad,settoreDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.READ)
	public GetAnteprimeOrdineByEvasioneIdResponse getAnteprimeOrdineByEvasioneId(UUID idEvasione) {
		return executeService(new GetAnteprimeOrdineByEvasioneIdRequest(idEvasione),
				new GetAnteprimeOrdineByEvasioneIdService(configurationHelper, testataOrdineDad, rigaOrdineDad,settoreDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.READ)
	public GetTestateOrdineByDestinatarioEvasioneIdResponse getTestateOrdineByDestinatarioEvasioneId(UUID idEvasione) {
		return executeService(new GetTestateOrdineByDestinatarioEvasioneIdRequest(idEvasione),
				new GetTestateOrdineByDestinatarioEvasioneIdService(configurationHelper, testataOrdineDad,settoreDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.READ)
	public GetTestateOrdineByRigaEvasioneIdResponse getTestateOrdineByRigaEvasioneId(UUID idEvasione) {
		return executeService(new GetTestateOrdineByRigaEvasioneIdRequest(idEvasione),
				new GetTestateOrdineByRigaEvasioneIdService(configurationHelper, testataOrdineDad,settoreDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.READ)
	public GetRicercaRigheDaEvadereResponse getRigheDaEvadere(Integer annoOrdineDa, Integer numeroOrdineDa, Integer annoOrdineA, Integer numeroOrdineA,
			Date dataEmissioneDa, Date dataEmissioneA, TestataOrdine testataOrdine, Destinatario destinatario, Impegno impegno, Subimpegno subimpegno,
			RigaOrdine rigaOrdine, List<Ods> odsList) {
		return executeService(new GetRicercaRigheDaEvadereRequest(annoOrdineDa, numeroOrdineDa, annoOrdineA, numeroOrdineA, dataEmissioneDa, dataEmissioneA,
				testataOrdine, destinatario, impegno, subimpegno, rigaOrdine, odsList),
				new GetRicercaRigheDaEvadereService(configurationHelper, rigaOrdineDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.READ)
	public GetRicercaSezioneResponse getRicercaSezione(@Min(0) Integer offset, @Min(1) @Max(100) Integer limit, String sort,String direction, Sezione sezione) {
		return executeService(new GetRicercaSezioneRequest(offset, limit, sort, direction,sezione),new GetRicercaSezioneService(configurationHelper, testataOrdineDad,settoreDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetDocumentoByIdResponse getDocumentoById(Integer idDocumento) {
		return executeService(new GetDocumentoByIdRequest(idDocumento),new GetDocumentoByIdService(configurationHelper, testataOrdineDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetDocumentiByOrdineTestataIdResponse getDocumentiByOrdineTestataId(UUID idTestataOrdine) {
		return executeService(new GetDocumentiByOrdineTestataIdRequest(idTestataOrdine),new GetDocumentiByOrdineTestataIdService(configurationHelper, testataOrdineDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public PostControlliInvioInFirmaResponse postControlliInvioInFirma(TestataOrdine testataOrdine) {
		return executeService(new PostControlliInvioInFirmaRequest(testataOrdine),new PostControlliInvioInFirmaService(configurationHelper, externalHelperLookup,testataOrdineDad, rigaOrdineDad, impegnoDad, systemDad, settoreDad));
	}
}
