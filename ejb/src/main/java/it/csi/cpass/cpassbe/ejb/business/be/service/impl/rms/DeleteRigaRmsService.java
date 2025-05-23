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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.rms;

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.RmsDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.rms.DeleteRigaRmsRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.rms.DeleteRigaRmsResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;

public class DeleteRigaRmsService extends BaseService<DeleteRigaRmsRequest, DeleteRigaRmsResponse> {

	private final RmsDad rigaRmsDad;

	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 * @param testataRmsDad    the testataRms DAD
	 * @param decodificaDad       the decodifica DAD
	 */
	public DeleteRigaRmsService(ConfigurationHelper configurationHelper, RmsDad rigaRmsDad) {
		super(configurationHelper);
		this.rigaRmsDad = rigaRmsDad;
	}

	@Override
	protected void checkServiceParams() {
		final UUID idRiga = request.getIdRiga();
		checkNotNull(idRiga, "idRiga", true);
	}

	@Override
	protected void execute() {
		rigaRmsDad.findOneRiga(request.getIdRiga());
		// elimino la riga
		rigaRmsDad.deleteRStatiRigaRmsByRmsId(request.getIdRiga());
		rigaRmsDad.deleteRiga(request.getIdRiga());
	}

}
