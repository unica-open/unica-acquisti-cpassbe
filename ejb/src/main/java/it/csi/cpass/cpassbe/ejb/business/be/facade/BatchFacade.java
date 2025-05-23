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

import java.util.concurrent.TimeUnit;

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
import it.csi.cpass.cpassbe.ejb.business.be.dad.DocumentoTrasportoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DocumentoTrasportoRigaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneMessaggioDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneTipoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.EnteDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.FornitoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.NotificheDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RmsDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.batch.GetAggiornaStrutturaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.batch.GetAggiornamentiImpegniService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.batch.GetAggiornamentiSubImpegniService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.batch.GetCaricamentoAggiornamentiImpegniService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.batch.GetCaricamentoAggiornamentiSubImpegniService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.batch.GetControlloBatchImpegniService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.batch.GetRecuperoDdtService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.batch.GetRecuperoNotificaNSOService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.batch.GetSmistamentoRmsService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.batch.GetStoricizzaFileDdtService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.batch.GetStoricizzaFileNsoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.batch.GetVerificaInvioContabilitaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.batch.GetAggiornaStrutturaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.batch.GetAggiornamentiImpegniRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.batch.GetAggiornamentiSubImpegniRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.batch.GetCaricamentoAggiornamentiImpegniRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.batch.GetCaricamentoAggiornamentiSubImpegniRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.batch.GetControlloBatchImpegniRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.batch.GetRecuperoDdtRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.batch.GetRecuperoNotificaNSORequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.batch.GetSmistamentoRmsRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.batch.GetStoricizzaFileDdtRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.batch.GetStoricizzaFileNsoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.batch.GetVerificaInvioContabilitaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.batch.GetAggiornaStrutturaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.batch.GetAggiornamentiImpegniResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.batch.GetAggiornamentiSubImpegniResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.batch.GetCaricamentoAggiornamentiImpegniResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.batch.GetCaricamentoAggiornamentiSubImpegniResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.batch.GetControlloBatchImpegniResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.batch.GetRecuperoDdtResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.batch.GetRecuperoNotificaNSOResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.batch.GetSmistamentoRmsResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.batch.GetStoricizzaFileDdtResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.batch.GetStoricizzaFileNsoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.batch.GetVerificaInvioContabilitaResponse;


/**
 * Fa&ccedil;ade for the /decodifica path
 */
@Stateless
@Lock(LockType.READ)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class BatchFacade extends BaseFacade {

	// Injection point
	@Inject private ElaborazioneDad elaborazioneDad;
	// Injection point
	@Inject private ElaborazioneTipoDad elaborazioneTipoDad;
	// Injection point
	@Inject private ElaborazioneMessaggioDad elaborazioneMessaggioDad;
	// Injection point
	@Inject private TestataEvasioneDad testataEvasioneDad;
	// Injection point
	@Inject private TestataOrdineDad testataOrdineDad;
	// Injection point
	@Inject private DestinatarioOrdineDad destinatarioOrdineDad;
	// Injection point
	@Inject private DecodificaDad decodificaDad;
	// Injection point
	@Inject private SystemDad systemDad;
	// Injection point
	@Inject private EnteDad enteDad;
	// Injection point
	@Inject private ImpegnoDad impegnoDad;
	// Injection point
	@Inject private CommonDad commonDad;
	// Injection point
	@Inject private DocumentoTrasportoDad documentoTrasportoDad;
	// Injection point
	@Inject private DocumentoTrasportoRigaDad documentoTrasportoRigaDad;
	// Injection point
	@Inject private RigaOrdineDad rigaOrdineDad;
	// Injection point
	@Inject private  FornitoreDad fornitoreDad;
	// Injection point
	@Inject private  UtenteDad utenteDad;
	// Injection point
	@Inject private  RmsDad  rmsDad;
	// Injection point
	@Inject private SettoreDad settoreDad;
	// Injection point
	@Inject private NotificheDad notificheDad;
	
	



	@TransactionTimeout(unit = TimeUnit.MINUTES, value = 10)
	public GetVerificaInvioContabilitaResponse getVerificaInvioContabilita(String ente) {
		return executeService(new GetVerificaInvioContabilitaRequest(ente), new GetVerificaInvioContabilitaService(configurationHelper, elaborazioneDad,elaborazioneTipoDad,elaborazioneMessaggioDad,enteDad,commonDad,systemDad,externalHelperLookup,  testataEvasioneDad, decodificaDad));
	}

	@TransactionTimeout(unit = TimeUnit.MINUTES, value = 60)
	public GetCaricamentoAggiornamentiImpegniResponse getCaricamentoAggiornamentiImpegni(String ente,Integer numelab,String dataElab) {
		final GetCaricamentoAggiornamentiImpegniResponse ris = executeService(new GetCaricamentoAggiornamentiImpegniRequest (ente, numelab, dataElab), new GetCaricamentoAggiornamentiImpegniService(configurationHelper, elaborazioneDad,elaborazioneTipoDad,elaborazioneMessaggioDad,enteDad,commonDad,systemDad, impegnoDad));
		return ris;
	}

	@TransactionTimeout(unit = TimeUnit.MINUTES, value = 60)
	public GetCaricamentoAggiornamentiSubImpegniResponse getCaricamentoAggiornamentiSubImpegni(String ente,Integer numelab,String dataElab) {
		return executeService(new GetCaricamentoAggiornamentiSubImpegniRequest (ente, numelab, dataElab), new GetCaricamentoAggiornamentiSubImpegniService(configurationHelper, elaborazioneDad,elaborazioneTipoDad,elaborazioneMessaggioDad,enteDad,commonDad,systemDad, impegnoDad));
	}

	@TransactionTimeout(unit = TimeUnit.MINUTES, value = 60)
	public GetAggiornamentiImpegniResponse getAggiornamentiImpegni(String ente,Integer numelab,String dataElab) {
		return executeService(new GetAggiornamentiImpegniRequest (ente, numelab, dataElab), new GetAggiornamentiImpegniService(configurationHelper, elaborazioneDad,elaborazioneTipoDad,elaborazioneMessaggioDad,enteDad,commonDad,systemDad, externalHelperLookup, impegnoDad, fornitoreDad));
	}

	@TransactionTimeout(unit = TimeUnit.MINUTES, value = 60)
	public GetAggiornamentiSubImpegniResponse getAggiornamentiSubImpegni(String ente,Integer numelab,String dataElab) {
		return executeService(new GetAggiornamentiSubImpegniRequest (ente, numelab,dataElab), new GetAggiornamentiSubImpegniService(configurationHelper, elaborazioneDad,elaborazioneTipoDad,elaborazioneMessaggioDad,enteDad,commonDad,systemDad, impegnoDad, fornitoreDad,externalHelperLookup));
	}

	@TransactionTimeout(unit = TimeUnit.MINUTES, value = 60)
	public GetControlloBatchImpegniResponse getControlloBatchImpegni(String enteCode,Integer numelab,String dataElab) {
		return executeService(new GetControlloBatchImpegniRequest (enteCode,numelab, dataElab), new GetControlloBatchImpegniService(configurationHelper,
																													elaborazioneDad,
																													elaborazioneTipoDad,
																													elaborazioneMessaggioDad,
																													enteDad,
																													commonDad,
																													systemDad));
	}


	@TransactionTimeout(unit = TimeUnit.MINUTES, value = 10)
	public GetRecuperoDdtResponse getRecuperoDdt(String enteCode) {
		return executeService(new GetRecuperoDdtRequest(enteCode), new GetRecuperoDdtService(configurationHelper, elaborazioneDad,elaborazioneTipoDad,elaborazioneMessaggioDad,enteDad,commonDad,systemDad,externalHelperLookup,testataOrdineDad, destinatarioOrdineDad, rigaOrdineDad, decodificaDad,  documentoTrasportoDad,documentoTrasportoRigaDad));
	}

	@TransactionTimeout(unit = TimeUnit.MINUTES, value = 5)
	public GetRecuperoNotificaNSOResponse getRecuperoNotificaNSO(String enteCode) {
		return executeService(new GetRecuperoNotificaNSORequest(enteCode), new GetRecuperoNotificaNSOService( configurationHelper, elaborazioneDad,elaborazioneTipoDad,elaborazioneMessaggioDad,enteDad,commonDad,systemDad,externalHelperLookup,testataOrdineDad,destinatarioOrdineDad,decodificaDad, utenteDad,notificheDad));
	}

	@TransactionTimeout(unit = TimeUnit.MINUTES, value = 5)
	public GetSmistamentoRmsResponse getSmistamentoRms(String enteCode) {
		return executeService(new GetSmistamentoRmsRequest(enteCode), new GetSmistamentoRmsService(configurationHelper, elaborazioneDad,elaborazioneTipoDad,elaborazioneMessaggioDad,enteDad,commonDad,systemDad,rmsDad,decodificaDad,utenteDad,notificheDad));
	}

	@TransactionTimeout(unit = TimeUnit.MINUTES, value = 5)
	public GetStoricizzaFileNsoResponse getStoricizzaFileNso(String enteCode) {
		return executeService(new GetStoricizzaFileNsoRequest(enteCode), new GetStoricizzaFileNsoService(configurationHelper, elaborazioneDad,elaborazioneTipoDad,elaborazioneMessaggioDad,enteDad,commonDad,destinatarioOrdineDad,systemDad));
	}

	@TransactionTimeout(unit = TimeUnit.MINUTES, value = 5)
	public GetStoricizzaFileDdtResponse getStoricizzaFileDdt(String enteCode) {
		return executeService(new GetStoricizzaFileDdtRequest(enteCode), new GetStoricizzaFileDdtService( configurationHelper, elaborazioneDad,elaborazioneTipoDad,elaborazioneMessaggioDad,enteDad,commonDad,systemDad,documentoTrasportoDad,documentoTrasportoRigaDad));
	}

	@TransactionTimeout(unit = TimeUnit.MINUTES, value = 5)
	public GetAggiornaStrutturaResponse getAggiornaStruttura(String enteCode) {
		return executeService(new GetAggiornaStrutturaRequest(enteCode), new GetAggiornaStrutturaService( configurationHelper, elaborazioneDad,elaborazioneTipoDad,elaborazioneMessaggioDad,enteDad,commonDad,systemDad,settoreDad,decodificaDad));
	}
}

