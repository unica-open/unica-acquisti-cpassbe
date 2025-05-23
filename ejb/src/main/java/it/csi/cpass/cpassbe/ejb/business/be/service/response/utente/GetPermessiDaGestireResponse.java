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
import it.csi.cpass.cpassbe.lib.dto.RuoloPermesso;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;

public class GetPermessiDaGestireResponse extends BasePagedResponse<RuoloPermesso> {
	PagedList<RuoloPermesso> ruoloPermesso;

	@Override
	protected PagedList<RuoloPermesso> getEntity() {
		return ruoloPermesso;
	}

	/**
	 * @return the ruoloPermesso
	 */
	public PagedList<RuoloPermesso> getRuoloPermesso() {
		return ruoloPermesso;
	}

	/**
	 * @param ruoloPermesso the ruoloPermesso to set
	 */
	public void setRuoloPermesso(PagedList<RuoloPermesso> ruoloPermesso) {
		this.ruoloPermesso = ruoloPermesso;
	}






}
