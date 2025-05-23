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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.magazzino;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.mag.Magazzino;

public class GetMagazzinoByIdResponse extends BaseGetResponse<Magazzino> {

	private Magazzino magazzino;

	public Magazzino getMagazzino() {
		return magazzino;
	}



	/**
	 * @param magazzino the magazzino to set
	 */
	public void setMagazzino(Magazzino magazzino) {
		this.magazzino = magazzino;
	}



	/**
	 * @return the entity
	 */
	@Override
	protected Magazzino getEntity() {
		return magazzino;
	}
}
