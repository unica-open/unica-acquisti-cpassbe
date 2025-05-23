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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.rms;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BasePagedResponse;
import it.csi.cpass.cpassbe.lib.dto.rms.RigaRms;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedListImpl;

/**
 * Response for reading Rmss.
 */
public class GetRicercaRmsDaSmistareResponse extends BasePagedResponse<RigaRms> {

	private PagedList<RigaRms> rigaRmss = new PagedListImpl<>();

	/**
	 * @return the testataRms
	 */
	public PagedList<RigaRms> getRigaRmss() {
		return rigaRmss;
	}

	/**
	 * @param rigaRmss the testataRms to set
	 */
	public void setRigaRmss(PagedList<RigaRms> rigaRmss) {
		this.rigaRmss = rigaRmss != null ? rigaRmss : new PagedListImpl<>();
	}

	@Override
	protected PagedList<RigaRms> getEntity() {
		return rigaRmss;
	}

}
