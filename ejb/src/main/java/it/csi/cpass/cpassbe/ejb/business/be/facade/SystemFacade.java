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

import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.system.GetComunicazioneService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.system.PostCsiAuditService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.system.GetComunicazioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.system.PostCsiAuditRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.system.GetComunicazioneResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.system.PostCsiAuditResponse;

/**
 * Fa&ccedil;ade for the /system path
 */
@Stateless
public class SystemFacade extends BaseFacade {
	// Injection point
	@Inject private SystemDad systemDad;

	/**
	 * Gets the Comunicazione
	 * @return the comunicazione
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.READ)
	public GetComunicazioneResponse getComunicaziones() {
		return executeService(new GetComunicazioneRequest(), new GetComunicazioneService(configurationHelper, systemDad));
	}

	/**
	 * Gets the Comunicazione
	 * @return the comunicazione
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.READ)
	public PostCsiAuditResponse postCsiAudit(String cf,String azione) {
		return executeService(new PostCsiAuditRequest(cf,azione), new PostCsiAuditService(configurationHelper, systemDad));
	}

}
