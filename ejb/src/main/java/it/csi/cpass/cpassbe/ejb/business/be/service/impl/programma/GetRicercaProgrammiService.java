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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.programma;

import it.csi.cpass.cpassbe.ejb.business.be.dad.ProgrammaDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.programma.GetRicercaProgrammiRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.programma.GetRicercaProgrammiResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.pba.Programma;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;

/**
 * Retrieves an Programmas
 */
public class GetRicercaProgrammiService extends BaseProgrammaService<GetRicercaProgrammiRequest, GetRicercaProgrammiResponse> {

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param programmaDad the programma DAD
	 */
	public GetRicercaProgrammiService(ConfigurationHelper configurationHelper, ProgrammaDad programmaDad) {
		super(configurationHelper, programmaDad);
	}

	@Override
	protected void execute() {
		PagedList<Programma> programmi = programmaDad.getRicercaProgrammi(
				request.getPage(),
				request.getSize(),
				request.getSort(),
				request.getProgramma());
		response.setProgrammi(programmi);
	}

}
