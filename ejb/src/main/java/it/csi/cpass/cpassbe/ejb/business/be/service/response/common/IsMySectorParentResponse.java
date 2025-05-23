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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.common;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;

/**
 * The Class GetUfficioResponse.
 */
public class IsMySectorParentResponse extends BaseGetResponse<Boolean> {

	/** The lingue. */
	private Boolean isParent;


	/**
	 * @return the isParent
	 */
	public Boolean getIsParent() {
		return isParent;
	}


	/**
	 * @param isParent the isParent to set
	 */
	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}


	@Override
	protected Boolean getEntity() {
		// TODO Auto-generated method stub
		return isParent;
	}



}
