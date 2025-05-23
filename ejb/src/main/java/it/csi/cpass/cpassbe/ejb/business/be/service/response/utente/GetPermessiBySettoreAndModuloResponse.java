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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.utente;

import java.util.ArrayList;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.Permesso;

/**
 * Response for reading Permesso by the id of the parent Permesso.
 */
public class GetPermessiBySettoreAndModuloResponse extends BaseGetResponse<List<Permesso>> {

	private List<Permesso> permessi = new ArrayList<>();

	/**
	 * @return the permessi
	 */
	public List<Permesso> getPermessi() {
		return permessi;
	}

	/**
	 * @param permessi the permessi to set
	 */
	public void setPermessi(List<Permesso> permessi) {
		this.permessi = permessi != null ? permessi : new ArrayList<>();
	}

	@Override
	protected List<Permesso> getEntity() {
		return permessi;
	}

}
