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
import it.csi.cpass.cpassbe.ejb.business.be.service.request.programma.PutProgrammaStatoAnnullatoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.programma.PutProgrammaStatoAnnullatoResponse;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoInterventiEnum;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoProgrammaEnum;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.TipoStatoEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassPba;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;
import it.csi.cpass.cpassbe.lib.dto.pba.Programma;

/**
 * Saves an stato Programma
 */
public class PutProgrammaStatoAnnullatoService extends BaseProgrammaService<PutProgrammaStatoAnnullatoRequest, PutProgrammaStatoAnnullatoResponse> {

	private Programma programma;
	private final InterventoDad interventoDad;
	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param programmaDad the programma DAD
	 * @param interventoDad
	 */
	public PutProgrammaStatoAnnullatoService(ConfigurationHelper configurationHelper, ProgrammaDad programmaDad,InterventoDad interventoDad) {
		super(configurationHelper, programmaDad);
		this.interventoDad = interventoDad;
	}

	@Override
	protected void checkServiceParams() {
		programma = request.getProgramma();
		checkModel(programma, "programma");
		checkNotNull( programma.getOptlock(),"opt look");
	}

	@Override
	protected void execute() {
		final List<Intervento> listIntNonAnnullati = interventoDad.getInterventoByProgrammaStato(programma.getId(),StatoProgrammaEnum.CANCELLATO.getCostante(),false);
		checkBusinessCondition(listIntNonAnnullati.size()==0, MsgCpassPba.PBAPRGE0036.getError());
		isEntityPresent(() -> programmaDad.getProgramma(programma.getId()), "programma");
		// se in stato annullato non posso annullare il programma
		//checkBusinessCondition(!programmaAttuale.getStato().getCodice().equals("ANNULLATO"), MsgCpass.PBAACQE0013.getError());
		programmaDad.updateStatoProgramma(request.getProgramma().getId(),StatoInterventiEnum.CANCELLATO.getCostante(), TipoStatoEnum.INTERVENTO.getCostante());
	}
}
