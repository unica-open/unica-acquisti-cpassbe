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

import java.util.UUID;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.settore.GetCdcValidiService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.settore.GetSettoreByIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.settore.GetSezioniBySettoreByIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.settore.PostSettoreService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.settore.PutSettoreService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.settore.GetCdcValidiRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.settore.GetSettoreByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.settore.GetSezioniBySettoreByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.settore.PostSettoreRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.settore.PutSettoreRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.settore.GetCdcValidiResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.settore.GetSettoreByIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.settore.GetSezioniBySettoreByIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.settore.PostSettoreResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.settore.PutSettoreResponse;
import it.csi.cpass.cpassbe.lib.dto.Settore;


/**
 * Fa&ccedil;ade for the /decodifica path
 */
@Stateless
@Lock(LockType.READ)
public class SettoreFacade extends BaseFacade {
	// Injection point
	@Inject private SettoreDad settoreDad;
	@Inject private UtenteDad utenteDad;

	public GetSettoreByIdResponse getSettoreById(UUID settoreId) {
		return executeService(new GetSettoreByIdRequest(settoreId), new GetSettoreByIdService(configurationHelper, settoreDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PostSettoreResponse postSettore(Settore settore) {
		return executeService(new PostSettoreRequest(settore), new PostSettoreService(configurationHelper, settoreDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public PutSettoreResponse putSettore(UUID settoreId,Settore settore) {
		return executeService(new PutSettoreRequest(settoreId,settore), new PutSettoreService(configurationHelper, settoreDad, utenteDad));
	}

	public GetSezioniBySettoreByIdResponse getSezioniBySettoreById(UUID settoreId) {
		return executeService(new GetSezioniBySettoreByIdRequest(settoreId), new GetSezioniBySettoreByIdService(configurationHelper, settoreDad));

	}

	public GetCdcValidiResponse getCdcValidi() {
		return executeService(new GetCdcValidiRequest(), new GetCdcValidiService(configurationHelper, settoreDad));
	}

}
