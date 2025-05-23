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

public class GetOggettiSpesaByIdResponse extends BaseGetResponse<Ods> {

	private Ods oggettiSpesa = new Ods();

	/**
	 * @return the oggettiSpesa
	 */
	public Ods getOggettiSpesa() {
		return oggettiSpesa;
	}

	/**
	 * @param oggettiSpesa the oggettiSpesa to set
	 */
	public void setOggettiSpesa(Ods oggettiSpesa) {
		this.oggettiSpesa = oggettiSpesa;
	}

	@Override
	protected Ods getEntity() {
		// TODO Auto-generated method stub
		return oggettiSpesa;
	}



}
