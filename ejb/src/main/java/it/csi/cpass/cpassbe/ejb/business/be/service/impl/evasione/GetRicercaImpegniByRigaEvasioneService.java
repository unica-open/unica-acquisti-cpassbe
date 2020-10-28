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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.evasione;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.GetRicercaImpegniByRigaEvasioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.GetRicercaImpegniByRigaEvasioneResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.ImpegnoEvasione;

public class GetRicercaImpegniByRigaEvasioneService extends BaseService<GetRicercaImpegniByRigaEvasioneRequest, GetRicercaImpegniByRigaEvasioneResponse> {

	private ImpegnoEvasioneDad impegnoEvasioneDad;

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad    the testataOrdine DAD
	 */
	public GetRicercaImpegniByRigaEvasioneService(ConfigurationHelper configurationHelper, ImpegnoEvasioneDad impegnoEvasioneDad) {
		super(configurationHelper);
		this.impegnoEvasioneDad = impegnoEvasioneDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getIdRigaEvasione(), "idRigaEvasione");
	}

	@Override
	protected void execute() {
		List<ImpegnoEvasione> listImpegno = impegnoEvasioneDad.getImpegniByRigaEvasione(request.getIdRigaEvasione());
		response.setListImpegno(listImpegno);
	}

}
