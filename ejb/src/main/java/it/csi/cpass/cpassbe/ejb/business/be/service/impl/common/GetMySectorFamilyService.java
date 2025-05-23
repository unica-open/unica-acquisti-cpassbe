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

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.GetMySectorFamilyRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.GetMySectorFamilyResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Settore;

/**
 * Retrieves an Moduli
 */
public class GetMySectorFamilyService extends BaseCommonService<GetMySectorFamilyRequest, GetMySectorFamilyResponse> {

	private final SettoreDad  settoreDad;
	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param utenteDad the utente DAD
	 */
	public GetMySectorFamilyService(ConfigurationHelper configurationHelper, CommonDad commonDad,SettoreDad settoreDad) {
		super(configurationHelper, commonDad);
		this.settoreDad = settoreDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getSettorePadreId(), "settorePadreId");
	}

	@Override
	protected void execute() {
		final List<Settore> lista = settoreDad.getMySectorFamily(request.getSettorePadreId());
		response.setSettores(lista);
	}

}
