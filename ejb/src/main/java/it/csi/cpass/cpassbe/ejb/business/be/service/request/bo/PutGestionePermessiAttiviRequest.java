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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.bo;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.RuoloPermesso;

public class PutGestionePermessiAttiviRequest implements BaseRequest {
	private final List<RuoloPermesso> ruoloPermessoAttivi;


	/**
	 * Constructor
	 *
	 * @param ruoloPermesso the ruoloPermesso
	 */
	public PutGestionePermessiAttiviRequest(List<RuoloPermesso> ruoloPermessoAttivi) {
		this.ruoloPermessoAttivi = ruoloPermessoAttivi;
	}

	/**
	 * @return the ruoloPermesso
	 */
	public List<RuoloPermesso> getRuoloPermessoAttivi() {
		return ruoloPermessoAttivi;
	}

}
