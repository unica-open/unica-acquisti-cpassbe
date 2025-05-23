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
import it.csi.cpass.cpassbe.lib.dto.ModuloRuoloPermesso;

public class PutGestionePermessiNonAttiviRequest implements BaseRequest {
	private final List<ModuloRuoloPermesso> moduloRuoloPermessoNonAttivi;

	/**
	 * Constructor
	 *
	 * @param ruoloPermesso the moduloRuoloPermesso
	 */
	public PutGestionePermessiNonAttiviRequest(List<ModuloRuoloPermesso> moduloRuoloPermessoNonAttivi) {
		this.moduloRuoloPermessoNonAttivi = moduloRuoloPermessoNonAttivi;
	}

	/**
	 * @return the moduloRuoloPermesso
	 */
	public List<ModuloRuoloPermesso> getModuloRuoloPermessoNonAttivi() {
		return moduloRuoloPermessoNonAttivi;
	}

}
