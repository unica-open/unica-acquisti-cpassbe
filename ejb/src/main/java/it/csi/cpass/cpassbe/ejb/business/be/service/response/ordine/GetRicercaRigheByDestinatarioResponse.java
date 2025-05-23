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

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;

public class GetRicercaRigheByDestinatarioResponse extends BaseGetResponse<List<RigaOrdine>> {

	private List<RigaOrdine> righeOrdine;

	/**
	 * @return the rigaOrdine
	 */
	public List<RigaOrdine> getRigheOrdine() {
		return righeOrdine;
	}

	/**
	 * @param rigaOrdine the rigaOrdine to set
	 */
	public void setRigheOrdine(List<RigaOrdine> righeOrdine) {
		this.righeOrdine = righeOrdine;
	}

	@Override
	protected List<RigaOrdine> getEntity() {
		return righeOrdine;
	}

}
