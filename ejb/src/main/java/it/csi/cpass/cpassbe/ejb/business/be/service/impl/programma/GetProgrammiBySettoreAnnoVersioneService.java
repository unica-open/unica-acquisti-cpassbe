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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.programma;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.ProgrammaDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.programma.GetProgrammiBySettoreAnnoVersioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.programma.GetProgrammiBySettoreAnnoVersioneResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.pba.Programma;

/**
 * Retrieves an settoris
 */
public class GetProgrammiBySettoreAnnoVersioneService extends BaseProgrammaService<GetProgrammiBySettoreAnnoVersioneRequest, GetProgrammiBySettoreAnnoVersioneResponse> {

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param programmaDad
	 */
	public GetProgrammiBySettoreAnnoVersioneService(ConfigurationHelper configurationHelper, ProgrammaDad programmaDad) {
		super(configurationHelper, programmaDad);
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getSettoreId(), "id settore");
		checkNotNull(request.getAnno(), "anno");
	}

	@Override
	protected void execute() {
		final List<Programma> programma = programmaDad.getProgrammiBySettoreAnnoVersione(request.getSettoreId(), request.getAnno(), request.getVersione() , request.getSolovalidi());
		response.setProgrammi(programma);
	}
}
