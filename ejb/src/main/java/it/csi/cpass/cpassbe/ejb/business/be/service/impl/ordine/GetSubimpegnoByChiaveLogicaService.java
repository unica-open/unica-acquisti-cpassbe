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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine;

import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.GetSubimpegnoByChiaveLogicaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.GetSubimpegnoByChiaveLogicaResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Subimpegno;

public class GetSubimpegnoByChiaveLogicaService extends BaseService<GetSubimpegnoByChiaveLogicaRequest, GetSubimpegnoByChiaveLogicaResponse> {

	private final ImpegnoDad impegnoDad;

	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad    the testataOrdine DAD
	 */
	public GetSubimpegnoByChiaveLogicaService(ConfigurationHelper configurationHelper, ImpegnoDad impegnoDad) {
		super(configurationHelper);
		this.impegnoDad = impegnoDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getSubimpegno().getAnnoEsercizio(), "annoEsercizio");
		checkNotNull(request.getSubimpegno().getAnno(), "anno imp");
		checkNotNull(request.getSubimpegno().getNumero(), "numero imp ");
	}

	@Override
	protected void execute() {
		final Subimpegno subimpegno = impegnoDad.getSubimpegnoByChiaveLogica(
				request.getSubimpegno().getAnnoEsercizio(),
				request.getSubimpegno().getImpegno().getAnno(),
				request.getSubimpegno().getImpegno().getNumero(),
				request.getSubimpegno().getEnte().getId(),
				request.getSubimpegno().getAnno(),
				request.getSubimpegno().getNumero()

				);
		response.setSubimpegno(subimpegno);
	}

}
