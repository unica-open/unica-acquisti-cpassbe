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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.programma;

import java.util.ArrayList;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.pba.Programma;

/**
 * Response for reading Programma by its id.
 */
public class GetProgrammiBySettoreAnnoVersioneResponse extends BaseGetResponse<List<Programma>> { //extends BaseGetResponse<Programma> {

	private List<Programma> programmi = new ArrayList<>();

	/**
	 * @return the interventiImporti
	 */
	public List<Programma> getProgrammi() {
		return programmi;
	}

	/**
	 * @param programmi the programmi to set
	 */
	public void setProgrammi(List<Programma> programmi) {
		this.programmi = programmi != null ? programmi : new ArrayList<>();
	}

	@Override
	protected List<Programma> getEntity() {
		return programmi;
	}

}
