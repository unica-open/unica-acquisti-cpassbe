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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.batch;

import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.batch.GetVerificaQuoteDocumentiRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.batch.GetVerificaQuoteDocumentiResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;

/**
 * Gets the Ufficios by settore
 */
public class GetVerificaQuoteDocumentiService extends BaseBatchService<GetVerificaQuoteDocumentiRequest, GetVerificaQuoteDocumentiResponse> {
	
	private final InterventoDad interventoDad;
	
	/**
	 * Constructor
	 * @param configurationHelper the helper for the configuration
	 * @param commonDad the DAD for the common
	 */
	public GetVerificaQuoteDocumentiService(ConfigurationHelper configurationHelper, InterventoDad interventoDad) {
		super(configurationHelper);
		this.interventoDad = interventoDad;		
	}

	@Override
	protected void execute() {
		// TODO DA FARE
		System.out.print("sevizio batch");
		//response.setUfficios(commonDad.getUfficiBySettore(request.getSettoreId()));
	}

}
