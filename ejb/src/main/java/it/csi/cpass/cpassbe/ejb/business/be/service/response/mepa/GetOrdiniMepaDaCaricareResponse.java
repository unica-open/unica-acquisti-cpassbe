/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.service.response.mepa;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BasePagedResponse;
import it.csi.cpass.cpassbe.lib.dto.ord.mepa.ScaricoMepaTestata;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;

public class GetOrdiniMepaDaCaricareResponse extends BasePagedResponse<ScaricoMepaTestata> {

	PagedList<ScaricoMepaTestata> scaricoMepaTestataPagedList;

	public PagedList<ScaricoMepaTestata> getScaricoMepaTestataPagedList() {
		return scaricoMepaTestataPagedList;
	}

	public void setScaricoMepaTestataPagedList(PagedList<ScaricoMepaTestata> scaricoMepaTestataPagedList) {
		this.scaricoMepaTestataPagedList = scaricoMepaTestataPagedList;
	}

	@Override
	protected PagedList<ScaricoMepaTestata> getEntity() {
		return scaricoMepaTestataPagedList;
	}
}
