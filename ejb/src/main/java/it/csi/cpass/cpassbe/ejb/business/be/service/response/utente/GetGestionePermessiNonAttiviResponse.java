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

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BasePagedResponse;
import it.csi.cpass.cpassbe.lib.dto.ModuloRuoloPermesso;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;

public class GetGestionePermessiNonAttiviResponse extends BasePagedResponse<ModuloRuoloPermesso> {
	PagedList<ModuloRuoloPermesso> ruoloPermesso;

	@Override
	protected PagedList<ModuloRuoloPermesso> getEntity() {
		return ruoloPermesso;

	}

	/**
	 * @return the ruoloPermesso
	 */
	public PagedList<ModuloRuoloPermesso> getRuoloPermesso() {
		return ruoloPermesso;
	}

	/**
	 * @param ruoloPermesso the ruoloPermesso to set
	 */
	public void setRuoloPermesso(PagedList<ModuloRuoloPermesso> ruoloPermesso) {
		this.ruoloPermesso = ruoloPermesso;
	}

}
