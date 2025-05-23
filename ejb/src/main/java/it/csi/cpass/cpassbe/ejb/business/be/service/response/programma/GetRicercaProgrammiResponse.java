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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.programma;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BasePagedResponse;
import it.csi.cpass.cpassbe.lib.dto.pba.Programma;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedListImpl;

/**
 * Response for reading Programma by its id.
 */
public class GetRicercaProgrammiResponse extends BasePagedResponse<Programma> {

	private PagedList<Programma> programmi = new PagedListImpl<>();

	/**
	 * @return the programmi
	 */
	public PagedList<Programma> getProgrammi() {
		return programmi;
	}

	/**
	 * @param programmi the programmi to set
	 */
	public void setProgrammi(PagedList<Programma> programmi) {
		this.programmi = programmi != null ? programmi : new PagedListImpl<>();
	}

	@Override
	protected PagedList<Programma> getEntity() {
		return programmi;
	}

}
