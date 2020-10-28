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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.common;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.PostRicercaElaborazioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.PostRicercaElaborazioneResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;

public class PostRicercaElaborazioneService extends BaseCommonService<PostRicercaElaborazioneRequest, PostRicercaElaborazioneResponse> {

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the configuration helper
	 */
	public PostRicercaElaborazioneService(ConfigurationHelper configurationHelper, CommonDad commonDad) {
		super(configurationHelper, commonDad);
	}

	@Override
	protected void checkServiceParams() {
	}

	@Override
	protected void execute() {
		List<Elaborazione> elaborazione = commonDad.postRicercaElaborazione(request.getElaborazione(), request.getElaborazioneTipoCodice());
		response.setElaborazioni(elaborazione);
	}

}
