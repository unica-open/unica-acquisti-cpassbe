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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.common.ods;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.Ods;

public class PutOggettiSpesaResponse extends BaseGetResponse<Ods> {

	private Ods ods;



	/**
	 * @return the ods
	 */
	public Ods getOds() {
		return ods;
	}



	/**
	 * @param ods the ods to set
	 */
	public void setOds(Ods ods) {
		this.ods = ods;
	}



	@Override
	protected Ods getEntity() {
		return ods;
	}
}
