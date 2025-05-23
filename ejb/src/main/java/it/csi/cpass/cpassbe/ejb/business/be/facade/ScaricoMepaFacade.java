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
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ScaricoMepaRigaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ScaricoMepaScontiDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ScaricoMepaTestataDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ScaricoMepaXmlDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.mepa.DeleteOrdineMepaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.mepa.GetOrdiniMepaDaCaricareService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.mepa.GetTestataMepaByIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.mepa.GetVerificaFornitoreService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.mepa.PostOrdineMepaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.mepa.PostUploadScaricoMepaXmlService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.mepa.DeleteOrdineMepaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.mepa.GetOrdiniMepaDaCaricareRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.mepa.GetTestataMepaByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.mepa.GetVerificaFornitoreRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.mepa.PostOrdineMepaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.mepa.PostUploadScaricoMepaXmlRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.mepa.DeleteOrdineMepaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.mepa.GetOrdiniMepaDaCaricareResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.mepa.GetTestataMepaByIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.mepa.GetVerificaFornitoreResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.mepa.PostOrdineMepaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.mepa.PostUploadScaricoMepaXmlResponse;
import it.csi.cpass.cpassbe.lib.dto.FileHolder;
import it.csi.cpass.cpassbe.lib.dto.custom.PostOrdineMepaBodyWrapper;

@Stateless
@Lock(LockType.READ)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ScaricoMepaFacade extends BaseFacade{

	@EJB private ScaricoMepaFacade self;

	@Inject
	private ScaricoMepaTestataDad scaricoMepaTestataDad;
	@Inject
	private CommonDad commonDad;
	@Inject
	private DecodificaDad decodificaDad;
	@Inject
	private ScaricoMepaRigaDad scaricoMepaRigaDad;
	@Inject
	private ScaricoMepaScontiDad scaricoMepaScontiDad;
	@Inject
	private ScaricoMepaXmlDad scaricoMepaXmlDad;
	@Inject
	private DestinatarioOrdineDad destinatarioOrdineDad;
	@Inject
	private TestataOrdineDad testataOrdineDad;
	@Inject
	private RigaOrdineDad rigaOrdineDad;
	@Inject
	private SettoreDad settoreDad;

	public GetOrdiniMepaDaCaricareResponse getOrdiniMepaDaCaricare(Integer page, Integer size, String sort, String direction) {
		return executeService(new GetOrdiniMepaDaCaricareRequest(page, size, sort, direction),
				new GetOrdiniMepaDaCaricareService(configurationHelper, scaricoMepaTestataDad, commonDad, decodificaDad));
	}

	public GetTestataMepaByIdResponse getTestataMepaById(Integer id) {
		return executeService(new GetTestataMepaByIdRequest(id),
				new GetTestataMepaByIdService(configurationHelper, scaricoMepaTestataDad, decodificaDad));
	}

	/**
	 * Posts the upload of a xml mepa
	 * @param fileHolder
	 * @return the response
	 */
	@TransactionTimeout(unit = TimeUnit.MINUTES, value = 5)
	@Lock(LockType.WRITE)
	public PostUploadScaricoMepaXmlResponse postUploadScaricoMepaXml(FileHolder fileHolder){
		return executeService(new PostUploadScaricoMepaXmlRequest(fileHolder),
				new PostUploadScaricoMepaXmlService(configurationHelper, scaricoMepaRigaDad,scaricoMepaTestataDad,
						scaricoMepaScontiDad, scaricoMepaXmlDad, decodificaDad));
	}

	/**
	 * Ricerca il fornitore tramite servizio esterno utilizzando il codice fornito, prima come partita IVA e,
	 * se non trova nulla, come codice fiscale,
	 *
	 * @param sellersupplierpartyEndpointId
	 * @return lista di fornitori
	 */
	@Lock(LockType.WRITE)
	public GetVerificaFornitoreResponse getVerificaFornitore(String sellersupplierpartyEndpointId) {
		return executeService(new GetVerificaFornitoreRequest(sellersupplierpartyEndpointId),
				new GetVerificaFornitoreService(configurationHelper, externalHelperLookup));
	}

	/**
	 * Salva la testata ordine creata a partire da un oridine MEPA
	 *
	 * @param postOrdineMepaBodyWrapper
	 * @return la testata ordine inserita
	 */
	@Lock(LockType.WRITE)
	public PostOrdineMepaResponse postOrdineMepa(PostOrdineMepaBodyWrapper postOrdineMepaBodyWrapper) {
		return executeService(
				new PostOrdineMepaRequest(
						postOrdineMepaBodyWrapper.getFormTestataOrdineMepa(),
						postOrdineMepaBodyWrapper.getScaricoMepaTestata(),
						postOrdineMepaBodyWrapper.getFornitore()),
				new PostOrdineMepaService(configurationHelper, scaricoMepaTestataDad, testataOrdineDad, rigaOrdineDad,
						decodificaDad, destinatarioOrdineDad, commonDad,settoreDad));
	}

	@Lock(LockType.WRITE)
	public DeleteOrdineMepaResponse deleteOrdineMepa(Integer idTestataMepa) {
		return executeService(new DeleteOrdineMepaRequest(idTestataMepa),
				new DeleteOrdineMepaService(configurationHelper, scaricoMepaTestataDad, decodificaDad));
	}
}
