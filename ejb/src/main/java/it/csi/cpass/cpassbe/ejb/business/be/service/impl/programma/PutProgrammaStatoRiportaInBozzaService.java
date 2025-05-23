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

import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ProgrammaDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.programma.PutProgrammaStatoRiportaInBozzaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.programma.PutProgrammaStatoRiportaInBozzaResponse;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoProgrammaEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassPba;
import it.csi.cpass.cpassbe.lib.dto.pba.Programma;

/**
 * Saves an Programma
 */
public class PutProgrammaStatoRiportaInBozzaService
extends BaseProgrammaService<PutProgrammaStatoRiportaInBozzaRequest, PutProgrammaStatoRiportaInBozzaResponse> {

	private Programma programma;
	private final InterventoDad interventoDad;

	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 * @param programmaDad        the programma DAD
	 */
	public PutProgrammaStatoRiportaInBozzaService(ConfigurationHelper configurationHelper, ProgrammaDad programmaDad, InterventoDad interventoDad ) {
		super(configurationHelper, programmaDad);
		this.interventoDad = interventoDad;
	}

	@Override
	protected void checkServiceParams() {
		programma = request.getProgramma();
		checkModel(programma, "programma");
		checkNotNull(programma.getOptlock(), "opt lock");
	}

	@Override
	protected void execute() {
		final Programma programmaAttuale = isEntityPresent(() -> programmaDad.getProgramma(programma.getId()), "programma");
		// verificare se esiste un altro programma con lo stesso biennio del programma che l’attore vuole riportare in bozza
		final List<Programma> programmas = programmaDad.getProgrammiByAnnoVersioneEnteStato(programmaAttuale.getAnno(),programmaAttuale.getAnnoFineProgramma(), null, programmaAttuale.getEnte().getId(),StatoProgrammaEnum.BOZZA.getCostante(), false, null);
		if (programmas != null && programmas.size() > 0) {
			checkBusinessCondition(false, MsgCpassPba.PBAPRGE0068.getError());
		}
		programmaDad.updateStatoProgramma(request.getProgramma().getId(), StatoProgrammaEnum.BOZZA.getCostante(),ConstantsCPassStato.TipoStatoEnum.PROGRAMMA.getCostante());
		interventoDad.deleteAcquistiCapPrivatiDaTrasmettereByProgrammaId(request.getProgramma().getId());
		interventoDad.deleteAcquistiDaTrasmettereByProgrammaId(request.getProgramma().getId());
	}

}
