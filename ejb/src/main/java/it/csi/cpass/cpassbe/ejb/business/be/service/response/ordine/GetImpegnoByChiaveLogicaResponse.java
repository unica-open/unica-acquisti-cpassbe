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
import it.csi.cpass.cpassbe.lib.dto.Impegno;

public class GetImpegnoByChiaveLogicaResponse extends BaseGetResponse<Impegno> {

	private Impegno impegno;




	/**
	 * @return the impegno
	 */
	public Impegno getImpegno() {
		return impegno;
	}




	/**
	 * @param impegno the impegno to set
	 */
	public void setImpegno(Impegno impegno) {
		this.impegno = impegno;
	}




	@Override
	protected Impegno getEntity() {
		// TODO Auto-generated method stub
		return impegno;
	}



}
