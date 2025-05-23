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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.rda;

import it.csi.cpass.cpassbe.ejb.business.be.dad.RdaDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.rda.PutTestataRdaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.rda.PutTestataRdaResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ord.rda.TestataRda;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Saves an testataRda
 */
public class PutTestataRdaService extends BaseRdaService<PutTestataRdaRequest, PutTestataRdaResponse> {

	private TestataRda testataRda;
	/**
	 * Constructor
	 *
	 * @param configurationHelper
	 * @param testataRdaDad
	 */
	public PutTestataRdaService(ConfigurationHelper configurationHelper, RdaDad rdaDad) {
		super(configurationHelper, rdaDad);
	}

	@Override
	protected void checkServiceParams() {
		testataRda = request.getTestataRda();
		checkNotNull(testataRda, "testataRda", Boolean.TRUE);
	}

	@Override
	protected void execute() {
		final TestataRda testataAttuale = isEntityPresent(() -> rdaDad.getTestataRdaById(testataRda.getId()), "testata rda");
		setAuditData(testataRda, testataAttuale);
		testataRda.setUtenteModifica(CpassThreadLocalContainer.UTENTE_CONNESSO.get().getId().toString());
		testataRda = rdaDad.updateTestataRda(testataRda);
		response.setTestataRda(testataRda);
	}

}
