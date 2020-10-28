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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.RigaEvasione;

public class GetRicercaRigheByDestinatarioEvasioneResponse extends BaseGetResponse<List<RigaEvasione>> {
	
	private List<RigaEvasione> righeEvasione;

	/**
	 * @return the rigaEvasione
	 */
	public List<RigaEvasione> getRigheEvasione() {
		return righeEvasione;
	}

	/**
	 * @param rigaEvasione the rigaEvasione to set
	 */
	public void setRigheEvasione(List<RigaEvasione> righeEvasione) {
		this.righeEvasione = righeEvasione;
	}

	@Override
	protected List<RigaEvasione> getEntity() {
		return righeEvasione;
	}

}
