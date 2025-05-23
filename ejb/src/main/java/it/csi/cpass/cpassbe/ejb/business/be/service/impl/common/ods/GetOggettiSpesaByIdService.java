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
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.ods.GetOggettiSpesaByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.ods.GetOggettiSpesaByIdResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Ods;

public class GetOggettiSpesaByIdService extends BaseCommonService<GetOggettiSpesaByIdRequest, GetOggettiSpesaByIdResponse> {

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param interventoDad the intervento DAD
	 */
	public GetOggettiSpesaByIdService(ConfigurationHelper configurationHelper, CommonDad commonDad) {
		super(configurationHelper, commonDad);
	}

	@Override
	protected void execute() {
		final Ods oggettiSpesa = commonDad.getOgettiSpesaById(request.getId());
		response.setOggettiSpesa(oggettiSpesa);
	}

}
