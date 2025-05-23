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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.stampe;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import it.csi.cpass.cpassbe.ejb.business.be.dad.StampeDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.stampe.StampaAllegatoInterventoByProgrammaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.stampe.StampaAllegatoInterventoByProgrammaResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;

/**
 * Retrieves
 */
public class StampaAllegatoInterventoByProgrammaService extends BaseStampeService<StampaAllegatoInterventoByProgrammaRequest, StampaAllegatoInterventoByProgrammaResponse> {

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param stampeDad the stampe DAD
	 */
	public StampaAllegatoInterventoByProgrammaService(ConfigurationHelper configurationHelper, StampeDad stampeDad, SystemDad systemDad) {
		super(configurationHelper, stampeDad, systemDad);
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getIdProgramma(), "Programma_id");
	}

	@Override
	protected void execute() {
		//		Map<String, String> formData = new HashMap<>();
		//		formData.put("Programma_id", request.getIdProgramma().toString());
		final List<NameValuePair> formData = new ArrayList<>();
		formData.add(new BasicNameValuePair("Programma_id", request.getIdProgramma().toString()));
		//callBirt(formData, "Allegato_II.rptdesign", request.getFormatFile());
	}

}
