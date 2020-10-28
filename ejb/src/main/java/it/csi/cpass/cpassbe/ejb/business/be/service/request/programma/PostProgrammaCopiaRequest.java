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
 * Request for posting the Programma
 */
public class PostProgrammaCopiaRequest implements BaseRequest {

	private final Programma programma;
	private final Boolean soloControlli;

	/**
	 * Constructor
	 * 
	 * @param programma the programma
	 */
	public PostProgrammaCopiaRequest(Programma programma, Boolean soloControlli) {
		this.programma = programma;
		this.soloControlli = soloControlli;
	}

	/**
	 * @return the programma
	 */
	public Programma getProgramma() {
		return programma;
	}

	/**
	 * @return the soloControlli
	 */
	public Boolean getSoloControlli() {
		return soloControlli;
	}

}
