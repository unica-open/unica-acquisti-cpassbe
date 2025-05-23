/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2025 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.service.response.utente;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.RuoloPermesso;

public class GetGestioneRuoloPermessoListResponse extends BaseGetResponse<List<RuoloPermesso>> {
	private List<RuoloPermesso> ruoloPermesso;

	/**
	 * @return the ruoloPermesso
	 */
	public List<RuoloPermesso> getRuoloPermesso() {
		return ruoloPermesso;
	}


	/**
	 * @param ruoloPermesso the ruoloPermesso to set
	 */
	public void setRuoloPermesso(List<RuoloPermesso> ruoloPermesso) {
		this.ruoloPermesso = ruoloPermesso;
	}


	@Override
	protected List<RuoloPermesso> getEntity() {
		return ruoloPermesso;
	}

}
