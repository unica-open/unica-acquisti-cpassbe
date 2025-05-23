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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.common.ods;

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.common.BaseCommonService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.ods.DelOggettiSpesaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.ods.DelOggettiSpesaResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

public class DelOggettiSpesaService extends BaseCommonService<DelOggettiSpesaRequest, DelOggettiSpesaResponse> {

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param interventoDad the commonDad DAD
	 */
	public DelOggettiSpesaService(ConfigurationHelper configurationHelper, CommonDad commonDad) {
		super(configurationHelper, commonDad);
	}

	@Override
	protected void execute() {
		final Utente utenteConnesso   = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		if(request.getControllo().equals("SI")) {
			final ApiError msg = commonDad.controlloDelOggettoSpesa(request.getId());
			checkBusinessCondition( !(request.getControllo().equalsIgnoreCase("SI")), msg);
		}else {
			commonDad.delOggettoSpesa(request.getId(),utenteConnesso.getCodiceFiscale());
		}
	}

}
