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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.elaborazionemessaggio;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneMessaggioDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.elaborazionemessaggio.GetMessaggiByUltimaElaborazioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.elaborazionemessaggio.GetMessaggiByUltimaElaborazioneResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ElaborazioneMessaggio;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Retrieves an elaborazioneMessaggio by its id
 */
public class GetMessaggiByUltimaElaborazioneService extends BaseElaborazioneMessaggioService<GetMessaggiByUltimaElaborazioneRequest, GetMessaggiByUltimaElaborazioneResponse> {

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param elaborazioneMessaggioDad the elaborazioneMessaggio DAD
	 */
	public GetMessaggiByUltimaElaborazioneService(ConfigurationHelper configurationHelper, ElaborazioneMessaggioDad elaborazioneMessaggioDad) {
		super(configurationHelper, elaborazioneMessaggioDad);
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getElaborazioneTipoCodice(), "ElaborazioneTipoCodice");
	}

	@Override
	protected void execute() {
		final Ente ente = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte();
		final String entitaId = request.getEntitaId();
		final List<ElaborazioneMessaggio> listaMessaggi = elaborazioneMessaggioDad.getMessaggioByUltimaElaborazione(entitaId,request.getElaborazioneTipoCodice(), ente.getId());
		response.setListaMessaggi(listaMessaggi);
	}

}
