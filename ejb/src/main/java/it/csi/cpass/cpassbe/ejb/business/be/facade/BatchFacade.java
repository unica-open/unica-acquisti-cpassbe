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

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.batch.GetVerificaInvioContabilitaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.batch.GetVerificaInvioContabilitaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.batch.GetVerificaInvioContabilitaResponse;


/**
 * Fa&ccedil;ade for the /decodifica path
 */
@Stateless
public class BatchFacade extends BaseFacade {
	// Injection point
	@Inject private InterventoDad 				interventoDad;
	// Injection point
	@Inject private CommonDad 				commonDad;


	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetVerificaInvioContabilitaResponse getVerificaInvioContabilita() {
		return executeService(new GetVerificaInvioContabilitaRequest(), new GetVerificaInvioContabilitaService(configurationHelper, interventoDad, commonDad));
	}
	
	
}
