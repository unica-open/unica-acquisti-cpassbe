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
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;

/**
 * Response for reading riga ordine
 */
public class GetRigheOrdineDaEvadereByOrdineAnnoNumeroResponse extends BaseGetResponse<List<RigaOrdine>> {

	private List<RigaOrdine> rigaOrdines;

	/**
	 * @param rigaOrdines the rigaOrdines to set
	 */
	public void setRigaOrdines(List<RigaOrdine> rigaOrdines) {
		this.rigaOrdines = rigaOrdines;
	}

	@Override
	protected List<RigaOrdine> getEntity() {
		return rigaOrdines;
	}

}
