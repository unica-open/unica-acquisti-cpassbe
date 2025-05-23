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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.Subimpegno;

public class GetSubimpegnoByChiaveLogicaResponse extends BaseGetResponse<Subimpegno> {

	private Subimpegno subimpegno;





	/**
	 * @return the subimpegno
	 */
	public Subimpegno getSubimpegno() {
		return subimpegno;
	}





	/**
	 * @param subimpegno the subimpegno to set
	 */
	public void setSubimpegno(Subimpegno subimpegno) {
		this.subimpegno = subimpegno;
	}





	@Override
	protected Subimpegno getEntity() {
		return subimpegno;
	}




}
