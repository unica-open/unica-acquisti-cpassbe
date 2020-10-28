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

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.batch.GetVerificaInvioContabilitaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.batch.GetVerificaInvioContabilitaResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;

/**
 * Gets the Ufficios by settore
 */
public class GetVerificaInvioContabilitaService extends BaseBatchService<GetVerificaInvioContabilitaRequest, GetVerificaInvioContabilitaResponse> {
	
	private final InterventoDad interventoDad;
	private final CommonDad commonDad;
	
	/**
	 * Constructor
	 * @param configurationHelper the helper for the configuration
	 * @param commonDad the DAD for the common
	 */
	public GetVerificaInvioContabilitaService(ConfigurationHelper configurationHelper, InterventoDad interventoDad, CommonDad commonDad) {
		super(configurationHelper);
		this.interventoDad = interventoDad;		
		this.commonDad = commonDad;		
	}

	@Override
	protected void execute() {
		Elaborazione elaborazione = new Elaborazione();
		//elaborazione.set
		//commonDad.postRicercaElaborazione( elaborazione, ConstantsCPassElaborazione.StatoEnum.DA_ELABORARE.getStatoDB());
		
	}

}
