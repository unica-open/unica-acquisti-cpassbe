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

import it.csi.cpass.cpassbe.ejb.business.be.dad.ProgrammaDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.programma.DeleteProgrammaByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.programma.DeleteProgrammaByIdResponse;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoProgrammaEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassPba;
import it.csi.cpass.cpassbe.lib.dto.pba.Programma;

/**
 * Deletes an programma by its id
 */
public class DeleteProgrammaByIdService extends BaseProgrammaService<DeleteProgrammaByIdRequest, DeleteProgrammaByIdResponse> {

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param programmaDad the programma DAD
	 */
	public DeleteProgrammaByIdService(ConfigurationHelper configurationHelper, ProgrammaDad programmaDad) {
		super(configurationHelper, programmaDad);
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getId(), "id");
	}

	@Override
	protected void execute() {

		final Programma programma = programmaDad.getProgramma(request.getId()).orElse(null);

		if(programma != null) {
			final boolean isConfermato = programma.getStato().getCodice().equals(StatoProgrammaEnum.CONFERMATO.getCostante());
			checkBusinessCondition(!isConfermato, MsgCpassPba.PBAPRGE0033.getError());
		}

		programmaDad.deleteProgramma(request.getId());
	}

}
