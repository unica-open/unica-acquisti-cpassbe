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

import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.GetImpegnoByChiaveLogicaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.GetSubimpegnoByChiaveLogicaService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.GetImpegnoByChiaveLogicaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.GetSubimpegnoByChiaveLogicaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.GetImpegnoByChiaveLogicaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.GetSubimpegnoByChiaveLogicaResponse;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.Subimpegno;

/**
 * Fa&ccedil;ade for the /impegno path
 */
@Stateless
@Lock(LockType.READ)
public class ImpegnoFacade extends BaseFacade {
	@Inject private ImpegnoDad impegnoDad;
	/**
	 * Gets the Impegnos By ChiaveLogica
	 * @return the Impegnos
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetImpegnoByChiaveLogicaResponse getImpegnoByChiaveLogica(Impegno impegno) {
		return executeService(new GetImpegnoByChiaveLogicaRequest(impegno), new GetImpegnoByChiaveLogicaService(configurationHelper, impegnoDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GetSubimpegnoByChiaveLogicaResponse getSubimpegnoByChiaveLogica(Subimpegno subimpegno) {
		return executeService(new GetSubimpegnoByChiaveLogicaRequest(subimpegno), new GetSubimpegnoByChiaveLogicaService(configurationHelper, impegnoDad));
	}

}
