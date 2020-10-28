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

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.ProgrammaDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.programma.GetProgrammiBySettoreAndStatoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.programma.GetProgrammiBySettoreAndStatoResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.pba.Programma;



/**
 * Retrieves a Programma
 */
public class GetProgrammiBySettoreAndStatoService extends BaseProgrammaService<GetProgrammiBySettoreAndStatoRequest, GetProgrammiBySettoreAndStatoResponse> {

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param programmaDad 
	 */
	public GetProgrammiBySettoreAndStatoService(ConfigurationHelper configurationHelper, ProgrammaDad programmaDad) {
		super(configurationHelper, programmaDad);
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getSettoreId(), "settoreId");
	}

	@Override
	protected void execute() {
		List<Programma> programmi = programmaDad.getProgrammiBySettoreAndStato(request.getSettoreId(), request.getStatoCode() );
		response.setProgrammi(programmi);
	}

}
