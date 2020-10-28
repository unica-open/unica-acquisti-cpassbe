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

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.DeleteImpegniByRigaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.DeleteImpegniByRigaResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;

public class DeleteImpegniByRigaService extends BaseService<DeleteImpegniByRigaRequest, DeleteImpegniByRigaResponse> {
	
	private final ImpegnoDad impegnoDad;

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad    the testataOrdine DAD
	 * @param decodificaDad       the decodifica DAD
	 */
	public DeleteImpegniByRigaService(ConfigurationHelper configurationHelper, ImpegnoDad impegnoDad) {
		super(configurationHelper);
		this.impegnoDad = impegnoDad;
	}

	@Override
	protected void checkServiceParams() {
		UUID idRiga = request.getIdRiga();
     	checkNotNull(idRiga, "idRiga", true);
	}

	@Override
	protected void execute() {
		// elimino tutti gli impegni collegati
		impegnoDad.deleteImpegniByRiga(request.getIdRiga());
	}

}
