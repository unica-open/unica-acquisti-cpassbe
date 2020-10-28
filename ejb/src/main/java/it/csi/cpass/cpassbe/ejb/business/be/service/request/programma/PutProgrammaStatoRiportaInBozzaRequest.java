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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.programma;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.pba.Programma;

/**
 * Request for putting the stato Programma
 */
public class PutProgrammaStatoRiportaInBozzaRequest implements BaseRequest {
	private final Programma programma;

	/**
	 * Constructor
	 * 
	 * @param programma the programma
	 */
	public PutProgrammaStatoRiportaInBozzaRequest(Programma programma) {
		this.programma = programma;
	}

	/**
	 * @return the programma
	 */
	public Programma getProgramma() {
		return programma;
	}

}
