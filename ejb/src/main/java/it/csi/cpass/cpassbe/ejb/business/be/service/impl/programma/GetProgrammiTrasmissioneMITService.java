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
import it.csi.cpass.cpassbe.ejb.business.be.service.request.programma.GetProgrammiTrasmissioneMITRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.programma.GetProgrammiTrasmissioneMITResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.pba.Programma;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;



/**
 * Retrieves a Programma
 */
public class GetProgrammiTrasmissioneMITService extends BaseProgrammaService<GetProgrammiTrasmissioneMITRequest, GetProgrammiTrasmissioneMITResponse> {

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param programmaDad the programma DAD
	 */
	public GetProgrammiTrasmissioneMITService(ConfigurationHelper configurationHelper, ProgrammaDad programmaDad) {
		super(configurationHelper, programmaDad);
	}

	@Override
	protected void execute() {
		final Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		final Ente ente = settoreCorrente.getEnte();
		final List<Programma> programmi = programmaDad.getProgrammiTrasmissioneMIT(ente.getId());
		response.setProgrammi(programmi);
	}

}
