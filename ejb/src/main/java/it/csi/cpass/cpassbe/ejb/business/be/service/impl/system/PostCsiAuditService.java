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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.system;

import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.system.PostCsiAuditRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.system.PostCsiAuditResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;

/**
 * Lettura Comunicazione
 */

public class PostCsiAuditService extends BaseService<PostCsiAuditRequest, PostCsiAuditResponse> {

	private final SystemDad systemDad;

	/**
	 * Constructor
	 * @param configurationHelper the helper for the configuration
	 * @param systemDad the DAD for the system
	 */
	public PostCsiAuditService(ConfigurationHelper configurationHelper, SystemDad systemDad) {
		super(configurationHelper);
		this.systemDad = systemDad;
	}

	@Override
	protected void execute() {
		systemDad.insertCsiLogAudit(request.getCf(),request.getAzione());
		log.debug("CsiAudit", "cf "+request.getCf());
		log.debug("CsiAudit", "azione "+request.getAzione());
		response.setLogout(Boolean.TRUE);
	}


}
