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
import it.csi.cpass.cpassbe.ejb.business.be.service.request.programma.PutProgrammaStatoRiportaInBozzaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.programma.PutProgrammaStatoRiportaInBozzaResponse;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.CpassStatiEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassPba;
import it.csi.cpass.cpassbe.lib.dto.pba.Programma;

/**
 * Saves an Programma
 */
public class PutProgrammaStatoRiportaInBozzaService
		extends BaseProgrammaService<PutProgrammaStatoRiportaInBozzaRequest, PutProgrammaStatoRiportaInBozzaResponse> {

	private Programma programma;

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the configuration helper
	 * @param programmaDad        the programma DAD
	 */
	public PutProgrammaStatoRiportaInBozzaService(ConfigurationHelper configurationHelper, ProgrammaDad programmaDad) {
		super(configurationHelper, programmaDad);
	}

	@Override
	protected void checkServiceParams() {
		programma = request.getProgramma();
		checkModel(programma, "programma");
		checkNotNull(programma.getOptlock(), "opt lock");
	}

	@Override
	protected void execute() {
		Programma programmaAttuale = isEntityPresent(() -> programmaDad.getProgramma(programma.getId()), "programma");
		checkOptlock(programma.getOptlock(), programmaAttuale.getOptlock());

		// verificare se esiste un altro programma con lo stesso biennio del programma che l’attore vuole riportare in bozza
		List<Programma> programmas = programmaDad.getProgrammiByAnnoVersioneEnteStato(programmaAttuale.getAnno(), null, programmaAttuale.getEnte().getId(),
				CpassStatiEnum.PRO_BOZZA.getCostante(), false, null);
		if (programmas != null && programmas.size() > 0) {
			checkBusinessCondition(false, MsgCpassPba.PBAPRGE0068.getError());
		}

		programmaDad.updateStatoProgramma(request.getProgramma().getId(), ConstantsCPassStato.StatoEnum.BOZZA.getCostante(),
				ConstantsCPassStato.TipoEnum.PROGRAMMA.getCostante());
	}

}
