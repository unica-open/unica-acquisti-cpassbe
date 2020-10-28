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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.interventoimporti;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.pba.InterventoImporti;

/**
 * Response for reading InterventoImporti by its id.
 */
public class GetInterventoImportiByIdResponse extends BaseGetResponse<InterventoImporti> {

	private InterventoImporti interventoImporti;

	/**
	 * @return the interventoImporti
	 */
	public InterventoImporti getInterventoImporti() {
		return interventoImporti;
	}

	/**
	 * @param interventoImporti the interventoImporti to set
	 */
	public void setInterventoImporti(InterventoImporti interventoImporti) {
		this.interventoImporti = interventoImporti;
	}

	@Override
	protected InterventoImporti getEntity() {
		return interventoImporti;
	}

}
