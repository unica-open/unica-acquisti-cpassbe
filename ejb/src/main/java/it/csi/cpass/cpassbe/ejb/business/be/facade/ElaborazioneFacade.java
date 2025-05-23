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

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneMessaggioDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.elaborazione.GetElaborazioniByEntityService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.elaborazione.PostElaborazioneService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.elaborazione.PutElaborazioneService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.elaborazionemessaggio.GetMessaggiByUltimaElaborazioneService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.elaborazione.GetElaborazioniByEntityRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.elaborazione.PostElaborazioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.elaborazione.PutElaborazioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.elaborazionemessaggio.GetMessaggiByUltimaElaborazioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.elaborazione.GetElaborazioniByEntityResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.elaborazione.PostElaborazioneResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.elaborazione.PutElaborazioneResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.elaborazionemessaggio.GetMessaggiByUltimaElaborazioneResponse;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;

/**
 * Fa&ccedil;ade for the /ente path
 */
@Stateless
@Lock(LockType.READ)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ElaborazioneFacade extends BaseFacade {
	// Injection point
	@Inject private ElaborazioneDad elaborazioneDad;
	// Injection point
	@Inject private ElaborazioneMessaggioDad elaborazioneMessaggioDad;
	/**
	 * Posts an Elaborazione
	 * @param elaborazione
	 * @return the elaborazione
	 */
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
	@Lock(LockType.WRITE)
	public PutElaborazioneResponse putElaborazioneById(Integer id, Elaborazione elaborazione) {
		return executeService(new PutElaborazioneRequest(setId(id, elaborazione)), new PutElaborazioneService(configurationHelper, elaborazioneDad));
	}

	public GetElaborazioniByEntityResponse getElaborazioniByEntity(String id) {
		return executeService(new GetElaborazioniByEntityRequest(id), new GetElaborazioniByEntityService(configurationHelper, elaborazioneDad));
	}

	public GetMessaggiByUltimaElaborazioneResponse getMessaggiByUltimaElaborazione(String entitaId, String elaborazioneTipoCodice) {
		return executeService(new GetMessaggiByUltimaElaborazioneRequest(entitaId,elaborazioneTipoCodice), new GetMessaggiByUltimaElaborazioneService(configurationHelper, elaborazioneMessaggioDad));
	}

}
