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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine;

import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.GetImpegnoByChiaveLogicaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.GetImpegnoByChiaveLogicaResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Impegno;

public class GetImpegnoByChiaveLogicaService extends BaseService<GetImpegnoByChiaveLogicaRequest, GetImpegnoByChiaveLogicaResponse> {

	private ImpegnoDad impegnoDad;

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad    the testataOrdine DAD
	 */
	public GetImpegnoByChiaveLogicaService(ConfigurationHelper configurationHelper, ImpegnoDad impegnoDad) {
		super(configurationHelper);
		this.impegnoDad = impegnoDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getImpegno().getAnnoEsercizio(), "annoEsercizio");
		checkNotNull(request.getImpegno().getAnno(), "anno");
		checkNotNull(request.getImpegno().getNumero(), "numero");
	}

	@Override
	protected void execute() {
		Impegno impegno = impegnoDad.getImpegnoByChiaveLogica(request.getImpegno().getAnnoEsercizio(),request.getImpegno().getAnno(),request.getImpegno().getNumero(),request.getImpegno().getEnte().getId());
		response.setImpegno(impegno);
	}

}
