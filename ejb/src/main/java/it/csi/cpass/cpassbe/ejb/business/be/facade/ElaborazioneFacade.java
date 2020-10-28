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

import java.util.UUID;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.elaborazione.GetElaborazioniByEntityService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.elaborazione.PostElaborazioneService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.elaborazione.PutElaborazioneService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.elaborazione.GetElaborazioniByEntityRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.elaborazione.PostElaborazioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.elaborazione.PutElaborazioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.elaborazione.GetElaborazioniByEntityResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.elaborazione.PostElaborazioneResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.elaborazione.PutElaborazioneResponse;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;

/**
 * Fa&ccedil;ade for the /ente path
 */
@Stateless
@Lock(LockType.READ)
public class ElaborazioneFacade extends BaseFacade {
	// Injection point
	@Inject private ElaborazioneDad elaborazioneDad;



	/**
	 * Retrieves the Ente by its id
	 * @param id the id
	 * @return the ente
	 */
	/*
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetEnteByIdResponse getEnteById(UUID id) {
		return executeService(new GetEnteByIdRequest(id), new GetEnteByIdService(configurationHelper, enteDad));
	}*/
	

	/**
	 * Posts an Elaborazione
	 * @param elaborazione
	 * @return the elaborazione
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PostElaborazioneResponse postElaborazione(Elaborazione elaborazione) {
		return executeService(new PostElaborazioneRequest(elaborazione), new PostElaborazioneService(configurationHelper, elaborazioneDad));
	}
	/**
	 * Puts an Elaborazione
	 * @param id the id
	 * @param elaborazione
	 * @return the elaborazione
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PutElaborazioneResponse putElaborazioneById(Integer id, Elaborazione elaborazione) {
		return executeService(new PutElaborazioneRequest(setId(id, elaborazione)), new PutElaborazioneService(configurationHelper, elaborazioneDad));
	}
	
	public GetElaborazioniByEntityResponse getElaborazioniByEntity(String id) {
		return executeService(new GetElaborazioniByEntityRequest(id), new GetElaborazioniByEntityService(configurationHelper, elaborazioneDad));
	}
	
}
