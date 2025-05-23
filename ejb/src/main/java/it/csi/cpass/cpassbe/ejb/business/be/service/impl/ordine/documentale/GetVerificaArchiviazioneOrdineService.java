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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.documentale;

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DocumentaleDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.documentale.GetVerificaArchiviazioneOrdineRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.documentale.GetVerificaArchiviazioneOrdineResponse;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;

public class GetVerificaArchiviazioneOrdineService extends BaseService<GetVerificaArchiviazioneOrdineRequest, GetVerificaArchiviazioneOrdineResponse> {

	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad    the testataOrdine DAD
	 * @param decodificaDad       the decodifica DAD
	 */
	public GetVerificaArchiviazioneOrdineService(ConfigurationHelper configurationHelper
			,ExternalHelperLookup externalHelperLookup
			,TestataOrdineDad testataOrdineDad
			,DocumentaleDad documentaleDad
			,CommonDad commonDad
			,UtenteDad utenteDad
			,SettoreDad settoreDad
			) {
		super(configurationHelper);
	}


	@Override
	protected void checkServiceParams() {
		final UUID testataOrdineId = request.getTestataOrdineId();
		checkNotNull(testataOrdineId, "testataOrdineId");
	}

	@Override
	protected void execute() {
		//chiamo acta
		// chiamata non usata
		/*
		Ente ente = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte();
		externalHelperLookup.lookup(DocumentaleHelper.class ,  ente.getId());
		UUID uuid = request.getTestataOrdineId();
//		TestataOrdine ordine = testataOrdineDad.getTestataOrdine(uuid);
		utenteDad.getUtente(ordine.getUtenteCompilatore().getId()).get();
		getAooBySettore(CpassThreadLocalContainer.SETTORE_UTENTE.get().getId(),ente.getId());
		try {
			//invokeExternalService(handler, () -> handler.getInstance().verificaArchiviazioneOrdine(handler.getParams(),idAOO));
		}catch(Exception e){
			log.error("getProvvedimenti", "Problemi nell'invocazione del servizio esposto da ACTA verificare che il servizio esterno sia attivo ",e );
		}
		 */
	}
	/*
	private Long getAooBySettore(UUID idsettore,UUID enteId) {
		Optional<AooActa> aooOpt = settoreDad.getAooByIdSettore(idsettore,enteId);
		if(aooOpt.isPresent()) {
			return Long.valueOf(aooOpt.get().getAooActaOrigId());
		}
		return null;
	}
	 */
}
