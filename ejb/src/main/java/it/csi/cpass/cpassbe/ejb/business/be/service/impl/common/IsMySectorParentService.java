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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.common;

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.IsMySectorParentRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.IsMySectorParentResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;

/**
 * Retrieves an Moduli
 */
public class IsMySectorParentService extends BaseCommonService<IsMySectorParentRequest, IsMySectorParentResponse> {

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param utenteDad the utente DAD
	 */
	public IsMySectorParentService(ConfigurationHelper configurationHelper, CommonDad commonDad) {
		super(configurationHelper, commonDad);
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getSettoreId(), "settoreId");
		checkNotNull(request.getSettorePadreId(), "settorePadreId");
	}

	@Override
	protected void execute() {
		final boolean ris = commonDad.isMySectorParent(request.getSettoreId(), request.getSettorePadreId());
		response.setIsParent(ris);
	}

}
