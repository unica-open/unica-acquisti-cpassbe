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
import it.csi.cpass.cpassbe.lib.dto.ord.RiepilogoImpegni;

public class GetRiepilogoImpegniByOrdineIdResponse extends BaseGetResponse<RiepilogoImpegni> {

	private RiepilogoImpegni riepilogoImpegni;



	/**
	 * @return the riepilogoImpegni
	 */
	public RiepilogoImpegni getRiepilogoImpegni() {
		return riepilogoImpegni;
	}



	/**
	 * @param riepilogoImpegni the riepilogoImpegni to set
	 */
	public void setRiepilogoImpegni(RiepilogoImpegni riepilogoImpegni) {
		this.riepilogoImpegni = riepilogoImpegni;
	}



	@Override
	protected RiepilogoImpegni getEntity() {
		return riepilogoImpegni;
	}

}
