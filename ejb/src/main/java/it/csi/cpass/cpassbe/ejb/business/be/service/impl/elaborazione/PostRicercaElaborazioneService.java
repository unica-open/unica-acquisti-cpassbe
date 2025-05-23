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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.elaborazione;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.PostRicercaElaborazioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.PostRicercaElaborazioneResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;
import it.csi.cpass.cpassbe.lib.dto.ElaborazioneTipo;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

public class PostRicercaElaborazioneService extends BaseElaborazioneService<PostRicercaElaborazioneRequest, PostRicercaElaborazioneResponse> {

	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 */
	public PostRicercaElaborazioneService(ConfigurationHelper configurationHelper, ElaborazioneDad elaborazioneDad) {
		super(configurationHelper, elaborazioneDad);
	}

	@Override
	protected void checkServiceParams() {
	}

	@Override
	protected void execute() {
		final Elaborazione elab = request.getElaborazione();
		final ElaborazioneTipo elaborazioneTipo = new ElaborazioneTipo();
		elaborazioneTipo.setCodice(request.getElaborazioneTipoCodice());
		elab.setElaborazioneTipo(elaborazioneTipo );
		final Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();

		elab.setEnte(settoreCorrente.getEnte());
		final List<Elaborazione> elaborazione = elaborazioneDad.postRicercaElaborazione(request.getElaborazione());
		response.setElaborazioni(elaborazione);
	}

}
