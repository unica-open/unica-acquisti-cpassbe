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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.mepa;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.ord.mepa.ScaricoMepaTestata;

public class GetTestataMepaByIdResponse extends BaseGetResponse<ScaricoMepaTestata> {

	ScaricoMepaTestata scaricoMepaTestata;

	public ScaricoMepaTestata getScaricoMepaTestata() {
		return scaricoMepaTestata;
	}

	public void setScaricoMepaTestata(ScaricoMepaTestata scaricoMepaTestata) {
		this.scaricoMepaTestata = scaricoMepaTestata;
	}

	@Override
	protected ScaricoMepaTestata getEntity() {
		return scaricoMepaTestata;
	}

}
