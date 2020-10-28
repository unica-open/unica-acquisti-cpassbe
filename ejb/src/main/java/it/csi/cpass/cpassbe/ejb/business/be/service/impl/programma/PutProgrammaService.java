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
import it.csi.cpass.cpassbe.ejb.business.be.service.request.programma.PutProgrammaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.programma.PutProgrammaResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.pba.Programma;

/**
 * Saves an Programma
 */
public class PutProgrammaService extends BaseProgrammaService<PutProgrammaRequest, PutProgrammaResponse> {

	private Programma programma;

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param programmaDad the programma DAD
	 */
	public PutProgrammaService(ConfigurationHelper configurationHelper, ProgrammaDad programmaDad) {
		super(configurationHelper, programmaDad);
	}

	@Override
	protected void checkServiceParams() {
		programma = request.getProgramma();
		checkNotNull(programma, "programma", true);
		checkNotNull(programma.getAnno(),"prima annualita");
		checkNotNull(programma.getDescrizione(), "descrizione programma");
		checkModel(programma.getStato(), "stato");
		checkModel(programma.getUtenteReferente(), "utente referente");
		checkModel(programma.getEnte(), "ente");
	}

	@Override
	protected void execute() {
		programma = request.getProgramma();	
		Programma programmaAttuale = isEntityPresent(() -> programmaDad.getProgramma(programma.getId()), "programma");
		// controllo per la concorrenza
		checkOptlock(programmaAttuale.getOptlock(), programma.getOptlock());
		setAuditData(programma, programmaAttuale);
		programmaDad.updateProgramma(programma);
	}
}
