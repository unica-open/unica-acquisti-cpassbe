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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.utente;

import java.util.ArrayList;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.Modulo;

/**
 * Response for reading modulo by the id of the parent Settore.
 */
public class GetModuloBySettoreResponse extends BaseGetResponse<List<Modulo>> {

	private List<Modulo> moduli = new ArrayList<>();

	/**
	 * @return the interventiImporti
	 */
	public List<Modulo> getModuli() {
		return moduli;
	}

	/**
	 * @param moduli the moduli to set
	 */
	public void setModuli(List<Modulo> moduli) {
		this.moduli = moduli != null ? moduli : new ArrayList<>();
	}

	@Override
	protected List<Modulo> getEntity() {
		return moduli;
	}

}
