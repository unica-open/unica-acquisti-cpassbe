/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
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

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RdaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RmsDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.rda.DeleteRigaRdaByIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.rda.DeleteTestataRdaByIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.rda.GetRicercaRdaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.rda.GetTestataRdaByAnnoENumService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.rda.GetTestataRdaByIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.rda.PostRicercaRmsDaEvadereService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.rda.PostTestataRdaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.rda.PutCambioStatoRdaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.rda.PutRigaRdaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.rda.PutTestataRdaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.rda.DeleteRigaRdaByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.rda.DeleteTestataRdaByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.rda.GetRicercaRdaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.rda.GetTestataRdaByAnnoENumRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.rda.GetTestataRdaByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.rda.PostRicercaRmsDaEvadereRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.rda.PostTestataRdaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.rda.PutCambioStatoRdaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.rda.PutRigaRdaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.rda.PutTestataRdaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.rda.DeleteRigaRdaByIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.rda.DeleteTestataRdaByIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.rda.GetRicercaRdaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.rda.GetTestataRdaByAnnoENumResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.rda.GetTestataRdaByIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.rda.PostRicercaRmsDaEvadereResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.rda.PostTestataRdaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.rda.PutCambioStatoRdaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.rda.PutRigaRdaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.rda.PutTestataRdaResponse;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.ord.Sezione;
import it.csi.cpass.cpassbe.lib.dto.ord.rda.RigaRda;
import it.csi.cpass.cpassbe.lib.dto.ord.rda.TestataRda;
import it.csi.cpass.cpassbe.lib.dto.rms.RigaRms;
import it.csi.cpass.cpassbe.lib.dto.rms.TestataRms;

/**
 * Facade for the /rda path
 */
@Stateless
@Lock(LockType.READ)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class RdaFacade extends BaseFacade{

	// Injection point
	@Inject private RdaDad rdaDad;
	@Inject private RmsDad rmsDad;
	@Inject private UtenteDad utenteDad;
	@Inject private DecodificaDad decodificaDad;
	@Inject private CommonDad commonDad;

	@Lock(LockType.WRITE)
	public PostRicercaRmsDaEvadereResponse getRicercaRmsDaEvadere(Integer page, Integer limit, String sort, String direction,
			Integer annoOrdineDa, Integer numeroOrdineDa,
			Integer annoOrdineA, Integer numeroOrdineA,
			Date dataInserimentoDa, Date dataInserimentoA,
			Date dataAutorizzazioneDa, Date dataAutorizzazioneA,
			TestataRms testataRms, RigaRms rigaRms,
			Sezione sezione,
			String statoRigaRms
			) {
		return executeService(new PostRicercaRmsDaEvadereRequest(page, limit, sort, direction, annoOrdineDa, numeroOrdineDa, annoOrdineA, numeroOrdineA, dataInserimentoDa,dataInserimentoA, dataAutorizzazioneDa, dataAutorizzazioneA, testataRms, rigaRms, sezione, statoRigaRms),new PostRicercaRmsDaEvadereService(configurationHelper, rdaDad, decodificaDad));
	}


	public GetTestataRdaByIdResponse getTestataRdaById(UUID id) {
		return executeService(new GetTestataRdaByIdRequest(id),
				new GetTestataRdaByIdService(configurationHelper, rdaDad, rmsDad));
	}

	public GetTestataRdaByAnnoENumResponse getTestataRdaByAnnoENum(Integer anno, Integer numero, UUID ente) {
		return executeService(new GetTestataRdaByAnnoENumRequest(anno, numero, ente),
				new GetTestataRdaByAnnoENumService(configurationHelper, rdaDad, utenteDad));
	}


	public GetRicercaRdaResponse getRicercaRda(Integer page, Integer limit, String sort, String direction, Integer annoRdaDa, Integer numeroRdaDa,
			Integer annoRdaA, Integer numeroRdaA, Date dataInserimentoDa, Date dataInserimentoA, TestataRda testataRda, RigaRda rigaRda, Settore settoreDestinatario) {
		return executeService(
				new GetRicercaRdaRequest(page, limit, sort, direction, annoRdaDa, numeroRdaDa, annoRdaA, numeroRdaA, dataInserimentoDa, dataInserimentoA, testataRda, rigaRda, settoreDestinatario),
				new GetRicercaRdaService(configurationHelper, rdaDad, rmsDad));
	}

	/**
	 * Posts an Rda
	 * @param testataRda
	 * @return the testataRda
	 */
	@Lock(LockType.WRITE)
	public PostTestataRdaResponse postTestataRda(TestataRda testataRda) {
		return executeService(new PostTestataRdaRequest(testataRda), new PostTestataRdaService(configurationHelper, rdaDad, decodificaDad,commonDad));
	}
	/**
	 * Puts an Rda
	 * @param testataRda
	 * @return the testataRda
	 */
	@Lock(LockType.WRITE)
	public PutTestataRdaResponse putTestataRdaById(UUID id, TestataRda testataRda) {
		return executeService(new PutTestataRdaRequest(setId(id, testataRda)), new PutTestataRdaService(configurationHelper, rdaDad));
	}

	@Lock(LockType.WRITE)
	public PutRigaRdaResponse putRigaRda(RigaRda rigaRda, Boolean checkOdsConfermato) {
		return executeService(new PutRigaRdaRequest(rigaRda, checkOdsConfermato),new PutRigaRdaService(configurationHelper, rdaDad, decodificaDad));
	}

	@Lock(LockType.WRITE)
	public DeleteTestataRdaByIdResponse deleteTestataRdaById(UUID testataRdaId) {
		return executeService(new DeleteTestataRdaByIdRequest(testataRdaId), new DeleteTestataRdaByIdService(configurationHelper, rdaDad, rmsDad, decodificaDad));
	}

	@Lock(LockType.WRITE)
	public DeleteRigaRdaByIdResponse deleteRigaRdaById(UUID rigaRdaId) {
		return executeService(new DeleteRigaRdaByIdRequest(rigaRdaId), new DeleteRigaRdaByIdService(configurationHelper, rdaDad, rmsDad, decodificaDad));
	}

	@Lock(LockType.WRITE)
	public PutCambioStatoRdaResponse putCambioStatoRdaRda(UUID rdaId, String statoCodice) {
		return executeService(new PutCambioStatoRdaRequest(rdaId,statoCodice),new PutCambioStatoRdaService(configurationHelper, rdaDad, decodificaDad));
	}

}
