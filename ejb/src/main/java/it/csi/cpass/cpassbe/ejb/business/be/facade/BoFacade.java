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

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.jboss.ejb3.annotation.TransactionTimeout;

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneMessaggioDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneTipoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.EnteDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.FornitoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.MagazzinoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.NotificheDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RdaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RmsDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.bo.GetGestionePermessiNonAttiviService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.bo.PostAvvisoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.bo.PostUploadCsvAggiornamentoOdsService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.bo.PutAnnullaOrdiniConfermatiService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.bo.PutCancellaOrdiniBozzaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.bo.PutDisattivaFunzioniGestioneService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.bo.PutGestionePermessiAttiviService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.bo.PutGestionePermessiNonAttiviService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.bo.PutRiattivazioneFunzioniGestioneService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.rms.PostUploadCsvRegoleSmistamentoRmsService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.rms.PutSmistamentoManualeRmsService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente.GetGestioneRuoloPermessoListService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente.GetGestioneRuoloPermessoNonAttListService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente.GetPermessiDaGestireService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.bo.PostAvvisoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.bo.PostUploadCsvAggiornamentoOdsRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.bo.PutAnnullaOrdiniConfermatiRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.bo.PutCancellaOrdiniBozzaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.bo.PutDisattivaFunzioniGestioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.bo.PutGestionePermessiAttiviRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.bo.PutGestionePermessiNonAttiviRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.bo.PutRiattivazioneFunzioniGestioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.rms.PostUploadCsvRegoleSmistamentoRmsRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.rms.PutSmistamentoManualeRmsRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetGestionePermessiNonAttiviRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetGestioneRuoloPermessoListRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetGestioneRuoloPermessoNonAttListRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetPermessiDaGestireRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.bo.PostAvvisoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.bo.PostUploadCsvAggiornamentoOdsResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.bo.PutAnnullaOrdiniConfermatiResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.bo.PutCancellaOrdiniBozzaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.bo.PutDisattivaFunzioniGestioneResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.bo.PutGestionePermessiAttiviResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.bo.PutGestionePermessiNonAttiviResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.bo.PutRiattivazioneFunzioniGestioneResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.rms.PostUploadCsvRegoleSmistamentoRmsResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.rms.PutSmistamentoManualeRmsResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetGestionePermessiNonAttiviResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetGestioneRuoloPermessoListResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetGestioneRuoloPermessoNonAttListResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetPermessiDaGestireResponse;
import it.csi.cpass.cpassbe.lib.dto.FileCsvAggiornaOdsHolder;
import it.csi.cpass.cpassbe.lib.dto.FileCsvRegoleHolder;
import it.csi.cpass.cpassbe.lib.dto.ModuloRuoloPermesso;
import it.csi.cpass.cpassbe.lib.dto.Notifica;
import it.csi.cpass.cpassbe.lib.dto.RuoloPermesso;
import it.csi.cpass.cpassbe.lib.dto.custom.SmistamentoManualeRms;

/**
 * Fa&ccedil;ade for the /rms path
 */
@Stateless
@Lock(LockType.READ)
@TransactionAttribute(TransactionAttributeType.REQUIRED )
public class BoFacade extends BaseFacade {
	@EJB private BoFacade self;

	// Injection point
	@Inject private RmsDad rmsDad;
	@Inject private DecodificaDad decodificaDad;
	@Inject private CommonDad commonDad;
	@Inject private UtenteDad utenteDad;
	@Inject private SettoreDad settoreDad;
	@Inject private EnteDad enteDad;
	@Inject private ElaborazioneDad elaborazioneDad;
	@Inject private ElaborazioneTipoDad elaborazioneTipoDad;
	@Inject private ElaborazioneMessaggioDad elaborazioneMessaggioDad;
	@Inject private MagazzinoDad magazzinoDad;
	@Inject private TestataOrdineDad testataOrdineDad;
	@Inject private FornitoreDad fornitoreDad;
	@Inject private ImpegnoDad impegnoDad;
	@Inject private DestinatarioOrdineDad destinatarioOrdineDad;
	@Inject private RigaOrdineDad rigaOrdineDad;
	@Inject private RdaDad rdaDad;
	@Inject private ImpegnoOrdineDad impegnoOrdineDad;
	@Inject private NotificheDad notificheDad;
	
	


	/**
	 * Posts the upload of a CSV
	 * @param fileHolder
	 * @return the response
	 */
	@TransactionTimeout(unit = TimeUnit.MINUTES, value = 5)
	@Lock(LockType.WRITE)
	public PostUploadCsvRegoleSmistamentoRmsResponse postUploadCsvRegoleSmistamentoRms(FileCsvRegoleHolder fileCsvRegoleHolder ) {
		return executeService(new PostUploadCsvRegoleSmistamentoRmsRequest(fileCsvRegoleHolder),
				new PostUploadCsvRegoleSmistamentoRmsService(configurationHelper,
																rmsDad,
																decodificaDad,
																enteDad,
																elaborazioneDad,
																elaborazioneTipoDad,
																elaborazioneMessaggioDad,
																settoreDad,
																magazzinoDad,
																testataOrdineDad
										
																));
	}

	/**
	 * Posts the upload of a CSV
	 * @param fileHolder
	 * @return the response
	 */
	@TransactionTimeout(unit = TimeUnit.MINUTES, value = 5)
	@Lock(LockType.WRITE)
	public PostUploadCsvAggiornamentoOdsResponse postUploadCsvAggiornamentoOds(FileCsvAggiornaOdsHolder fileCsvAggiornaOdsHolder ) {
		return executeService(new PostUploadCsvAggiornamentoOdsRequest(fileCsvAggiornaOdsHolder),
				new PostUploadCsvAggiornamentoOdsService(   configurationHelper,
						decodificaDad,
						elaborazioneDad,
						elaborazioneTipoDad,
						elaborazioneMessaggioDad,
						fornitoreDad,
						externalHelperLookup,
						commonDad
						));
	}

	@Lock(LockType.WRITE)
	public PutSmistamentoManualeRmsResponse putSmistamentoManualeRms(SmistamentoManualeRms smistamentoManualeRms) {
		return executeService(new PutSmistamentoManualeRmsRequest(smistamentoManualeRms.getSettore()
				,smistamentoManualeRms.getSezione()
				,smistamentoManualeRms.getMagazzino()
				,smistamentoManualeRms.getListaRigheRms()
				),
				new PutSmistamentoManualeRmsService(configurationHelper,rmsDad, magazzinoDad, decodificaDad, testataOrdineDad));
	}

	public PutDisattivaFunzioniGestioneResponse putDisattivaFunzioniGestione() {
		return executeService(new PutDisattivaFunzioniGestioneRequest(), new PutDisattivaFunzioniGestioneService(configurationHelper, elaborazioneDad,  elaborazioneMessaggioDad, elaborazioneTipoDad, decodificaDad));
	}

	public PutCancellaOrdiniBozzaResponse putCancellaOrdiniBozza() {
		return executeService(new PutCancellaOrdiniBozzaRequest(), new PutCancellaOrdiniBozzaService(configurationHelper, testataOrdineDad,   elaborazioneDad,  elaborazioneMessaggioDad, elaborazioneTipoDad, decodificaDad, impegnoDad, rigaOrdineDad, destinatarioOrdineDad, rdaDad));
	}

	public PutAnnullaOrdiniConfermatiResponse putAnnullaOrdiniConfermati() {
		return executeService(new PutAnnullaOrdiniConfermatiRequest(), new PutAnnullaOrdiniConfermatiService(configurationHelper, testataOrdineDad,   elaborazioneDad,  elaborazioneMessaggioDad, elaborazioneTipoDad, decodificaDad, rdaDad, rigaOrdineDad, destinatarioOrdineDad, impegnoOrdineDad));
	}

	public PutRiattivazioneFunzioniGestioneResponse putRiattivazioneFunzioniGestione() {
		return executeService(new PutRiattivazioneFunzioniGestioneRequest(), new PutRiattivazioneFunzioniGestioneService(configurationHelper, testataOrdineDad,   elaborazioneDad,  elaborazioneMessaggioDad, elaborazioneTipoDad, decodificaDad));
	}

	public PostAvvisoResponse postAvviso(Notifica notifica) {
		return executeService(new PostAvvisoRequest(notifica), new PostAvvisoService(configurationHelper, elaborazioneDad, elaborazioneMessaggioDad, elaborazioneTipoDad, notificheDad));
	}


	public GetGestioneRuoloPermessoListResponse getGestioneRuoloPermessoList() {
		return executeService(new GetGestioneRuoloPermessoListRequest(), new GetGestioneRuoloPermessoListService(configurationHelper, utenteDad));
	}
	public GetGestioneRuoloPermessoNonAttListResponse getGestioneRuoloPermessoNonAttiviList() {
		return executeService(new GetGestioneRuoloPermessoNonAttListRequest(), new GetGestioneRuoloPermessoNonAttListService(configurationHelper, utenteDad));
	}

	public GetPermessiDaGestireResponse getPermessiDaGestire(Integer page, Integer limit, String sort, String direction) {
		return executeService(new GetPermessiDaGestireRequest(page, limit, sort, direction), new GetPermessiDaGestireService(configurationHelper, utenteDad));
	}

	public GetGestionePermessiNonAttiviResponse getPermessiDaGestireNonAttivi(Integer page, Integer limit, String sort, String direction) {
		return executeService(new GetGestionePermessiNonAttiviRequest(page, limit, sort, direction), new GetGestionePermessiNonAttiviService(configurationHelper, utenteDad));
	}


	public PutGestionePermessiAttiviResponse putGestionePermessiAttivi(List<RuoloPermesso> ruoloPermessoAttivi) {
		return executeService(new PutGestionePermessiAttiviRequest(ruoloPermessoAttivi), new PutGestionePermessiAttiviService(configurationHelper, elaborazioneDad, elaborazioneMessaggioDad, elaborazioneTipoDad, utenteDad));
	}

	public PutGestionePermessiNonAttiviResponse putGestionePermessiNonAttivi(List<ModuloRuoloPermesso> moduloRuoloPermessoNonAttivi) {
		return executeService(new PutGestionePermessiNonAttiviRequest(moduloRuoloPermessoNonAttivi), new PutGestionePermessiNonAttiviService(configurationHelper, elaborazioneDad, elaborazioneMessaggioDad, elaborazioneTipoDad, utenteDad));
	}


}

