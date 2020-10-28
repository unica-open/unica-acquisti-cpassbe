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

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.GetRicercaImpegniByRigaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.GetRicercaImpegniByRigaResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Impegno;

public class GetRicercaImpegniByRigaService extends BaseService<GetRicercaImpegniByRigaRequest, GetRicercaImpegniByRigaResponse> {

	private ImpegnoDad impegnoDad;

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad    the testataOrdine DAD
	 */
	public GetRicercaImpegniByRigaService(ConfigurationHelper configurationHelper, ImpegnoDad impegnoDad) {
		super(configurationHelper);
		this.impegnoDad = impegnoDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getIdRiga(), "destinatarioId");
	}

	@Override
	protected void execute() {
		List<Impegno> listImpegno = impegnoDad.getImpegniByRiga(request.getIdRiga());
		response.setListImpegno(listImpegno);
	}

}
