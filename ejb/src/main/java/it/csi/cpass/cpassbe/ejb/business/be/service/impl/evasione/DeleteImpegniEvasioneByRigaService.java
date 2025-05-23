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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.evasione;

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.DeleteImpegniEvasioneByRigaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.DeleteImpegniEvasioneByRigaResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;

public class DeleteImpegniEvasioneByRigaService extends BaseService<DeleteImpegniEvasioneByRigaRequest, DeleteImpegniEvasioneByRigaResponse> {

	private final ImpegnoEvasioneDad impegnoEvasioneDad;

	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 * @param testataEvasioneDad  the testataEvasione DAD
	 */
	public DeleteImpegniEvasioneByRigaService(ConfigurationHelper configurationHelper, ImpegnoEvasioneDad impegnoEvasioneDad) {
		super(configurationHelper);
		this.impegnoEvasioneDad = impegnoEvasioneDad;
	}

	@Override
	protected void checkServiceParams() {
		final UUID rigaEvasioneId = request.getRigaEvasioneId();
		checkNotNull(rigaEvasioneId, "rigaEvasioneId", Boolean.TRUE);
	}

	@Override
	protected void execute() {
		// elimino tutti gli impegni collegati
		impegnoEvasioneDad.deleteImpegniEvasioneByRiga(request.getRigaEvasioneId());
	}

}
