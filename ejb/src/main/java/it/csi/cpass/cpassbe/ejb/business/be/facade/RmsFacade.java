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

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.NotificheDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RmsDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.rms.DeleteRigaRmsService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.rms.DeleteTestataRmsService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.rms.GetRicercaRmsDaSmistareService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.rms.GetRicercaRmsService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.rms.GetTestataRmsByAnnoENumService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.rms.GetTestataRmsByIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.rms.PostRigaRmsService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.rms.PostTestataRmsService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.rms.PutCambioStatoRigaRmsService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.rms.PutCambioStatoRmsService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.rms.PutRigaRmsService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.rms.PutTestataRmsService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.rms.DeleteRigaRmsRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.rms.DeleteTestataRmsRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.rms.GetRicercaRmsDaSmistareRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.rms.GetRicercaRmsRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.rms.GetTestataRmsByAnnoENumRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.rms.GetTestataRmsByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.rms.PostRigaRmsRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.rms.PostTestataRmsRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.rms.PutCambioStatoRigaRmsRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.rms.PutCambioStatoRmsRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.rms.PutRigaRmsRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.rms.PutTestataRmsRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.rms.DeleteRigaRmsResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.rms.DeleteTestataRmsResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.rms.GetRicercaRmsDaSmistareResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.rms.GetRicercaRmsResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.rms.GetTestataRmsByAnnoENumResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.rms.GetTestataRmsByIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.rms.PostRigaRmsResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.rms.PostTestataRmsResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.rms.PutCambioStatoRigaRmsResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.rms.PutCambioStatoRmsResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.rms.PutRigaRmsResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.rms.PutTestataRmsResponse;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.SettoreIndirizzo;
import it.csi.cpass.cpassbe.lib.dto.rms.RigaRms;
import it.csi.cpass.cpassbe.lib.dto.rms.TestataRms;

/**
 * Fa&ccedil;ade for the /rms path
 */
@Stateless
@Lock(LockType.READ)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class RmsFacade extends BaseFacade {
	@EJB private RmsFacade self;

	// Injection point
	@Inject private RmsDad rmsDad;
	@Inject private DecodificaDad decodificaDad;
	@Inject private UtenteDad utenteDad;
	@Inject private NotificheDad notificheDad;
	@Inject private SettoreDad settoreDad;
	public GetTestataRmsByIdResponse getTestataRmsById(UUID id) {
		return executeService(new GetTestataRmsByIdRequest(id), new GetTestataRmsByIdService(configurationHelper, rmsDad));
	}

	public GetTestataRmsByAnnoENumResponse getTestataRmsByAnnoENum(Integer anno, Integer numero) {
		return executeService(new GetTestataRmsByAnnoENumRequest(anno, numero),
				new GetTestataRmsByAnnoENumService(configurationHelper, rmsDad, utenteDad));
	}


	/**
	 * Posts an TestataRms
	 * @param testataRms
	 * @return the testataRms
	 */
	@Lock(LockType.WRITE)
	public PostTestataRmsResponse postTestataRms(TestataRms testataRms) {
		return executeService(new PostTestataRmsRequest(testataRms), new PostTestataRmsService(configurationHelper, rmsDad, decodificaDad));
	}

	/**
	 * Puts an TestataRms
	 * @param id the id
	 * @param testataRms
	 * @return the testataRms
	 */
	@Lock(LockType.WRITE)
	public PutTestataRmsResponse putTestataRmsById(UUID id, TestataRms testataRms) {
		return executeService(new PutTestataRmsRequest(setId(id, testataRms)), new PutTestataRmsService(configurationHelper, rmsDad));
	}

	/**
	 * Deletes the TestataRms by its id
	 * @param id the id
	 * @return nothing
	 */
	@Lock(LockType.WRITE)
	public DeleteTestataRmsResponse deleteTestataRmsById(UUID id) {
		return executeService(new DeleteTestataRmsRequest(id), new DeleteTestataRmsService(configurationHelper, rmsDad, decodificaDad));
	}

	public GetRicercaRmsResponse getRicercaRms(Integer page, Integer limit, String sort, String direction, Integer annoOrdineDa, Integer numeroOrdineDa,
			Integer annoOrdineA, Integer numeroOrdineA, Date dataInserimentoDa, Date dataInserimentoA, TestataRms testataRms, RigaRms rigaRms, Settore settoreEmittente, Settore settore, SettoreIndirizzo settoreIndirizzo) {
		return executeService(
				new GetRicercaRmsRequest(page, limit, sort, direction, annoOrdineDa, numeroOrdineDa, annoOrdineA, numeroOrdineA, dataInserimentoDa,
						dataInserimentoA, testataRms, rigaRms, settoreEmittente, settore, settoreIndirizzo),new GetRicercaRmsService(configurationHelper, rmsDad, utenteDad));
	}

	@Lock(LockType.WRITE)
	public PostRigaRmsResponse postRigaRms(RigaRms rigaRms) {
		return executeService(new PostRigaRmsRequest(rigaRms),
				new PostRigaRmsService(configurationHelper, externalHelperLookup, rmsDad, decodificaDad));
	}

	@Lock(LockType.WRITE)
	public PutRigaRmsResponse putRigaRms(RigaRms rigaRms) {
		return executeService(new PutRigaRmsRequest(rigaRms),new PutRigaRmsService(configurationHelper, rmsDad, decodificaDad));
	}

	@Lock(LockType.WRITE)
	public PutCambioStatoRmsResponse putCambioStatoRms(UUID id, String statoCode, TestataRms testataRms) {
		return executeService(new PutCambioStatoRmsRequest(id, statoCode, testataRms),new PutCambioStatoRmsService(configurationHelper, rmsDad ,decodificaDad,settoreDad, utenteDad,notificheDad));
	}

	@Lock(LockType.WRITE)
	public DeleteRigaRmsResponse deleteRigaRms(UUID id) {
		return executeService(new DeleteRigaRmsRequest(id),
				new DeleteRigaRmsService(configurationHelper, rmsDad));
	}

	@Lock(LockType.WRITE)
	public PutCambioStatoRigaRmsResponse putCambioStatoRigaRms(String statoCode, List<RigaRms> rigaRmsList) {
		return executeService(new PutCambioStatoRigaRmsRequest(statoCode, rigaRmsList),new PutCambioStatoRigaRmsService(configurationHelper, rmsDad ,decodificaDad,settoreDad));
	}

	public GetRicercaRmsDaSmistareResponse getRicercaRmsDaSmistare(Integer page, Integer limit, String sort, String direction, Integer annoOrdineDa, Integer numeroOrdineDa,
			Integer annoOrdineA, Integer numeroOrdineA, Date dataInserimentoDa, Date dataInserimentoA, TestataRms testataRms, RigaRms rigaRms) {
		return executeService(
				new GetRicercaRmsDaSmistareRequest(page, limit, sort, direction, annoOrdineDa, numeroOrdineDa, annoOrdineA, numeroOrdineA, dataInserimentoDa,
						dataInserimentoA, testataRms, rigaRms),
				new GetRicercaRmsDaSmistareService(configurationHelper, rmsDad, decodificaDad));
	}

	/**
	 * Posts the upload of a CSV
	 * @param fileHolder
	 * @return the response
	 */
	/*
	@TransactionAttribute(TransactionAttributeType.REQUIRED )
	@TransactionTimeout(unit = TimeUnit.MINUTES, value = 5)
	@Lock(LockType.WRITE)
	public PostUploadCsvRegoleSmistamentoRmsResponse postUploadCsvRegoleSmistamentoRms(FileCsvRegoleHolder fileCsvRegoleHolder ) {
		return executeService(new PostUploadCsvRegoleSmistamentoRmsRequest(fileCsvRegoleHolder), new PostUploadCsvRegoleSmistamentoRmsService(configurationHelper,
																																				rmsDad,
																																				decodificaDad,
																																				enteDad,
																																				elaborazioneDad,
																																				elaborazioneTipoDad,
																																				elaborazioneMessaggioDad,
																																				settoreDad,
																																				magazzinoDad));
	}
	 */

}

