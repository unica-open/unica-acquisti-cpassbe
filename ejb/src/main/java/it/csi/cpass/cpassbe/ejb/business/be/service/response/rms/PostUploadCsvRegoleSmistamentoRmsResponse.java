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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.rms;

import java.util.ArrayList;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.rms.RegoleSmistamentoRms;

/**
 * Response for post upload CSV
 */
public class PostUploadCsvRegoleSmistamentoRmsResponse extends BaseGetResponse<List<RegoleSmistamentoRms>> {

	private List<RegoleSmistamentoRms> regoleSmistamentoRms = new ArrayList<>();


	/**
	 * @return the regoleSmistamentoRms
	 */
	public List<RegoleSmistamentoRms> getRegoleSmistamentoRms() {
		return regoleSmistamentoRms;
	}


	/**
	 * @param regoleSmistamentoRms the regoleSmistamentoRms to set
	 */
	public void setRegoleSmistamentoRms(List<RegoleSmistamentoRms> regoleSmistamentoRms) {
		this.regoleSmistamentoRms = initList(regoleSmistamentoRms);
	}


	@Override
	protected List<RegoleSmistamentoRms> getEntity() {
		return regoleSmistamentoRms;
	}


}
