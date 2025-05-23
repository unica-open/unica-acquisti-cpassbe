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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DocumentaleDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.documentale.GetStrutturaAggregativaXIndiceclassificazioneEstesaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.documentale.GetStrutturaAggregativaXIndiceclassificazioneEstesaResponse;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.error.CoreError;
import it.csi.cpass.cpassbe.lib.dto.ord.AooActa;
import it.csi.cpass.cpassbe.lib.dto.ord.ProtocolloOrdine;
import it.csi.cpass.cpassbe.lib.external.DocumentaleHelper;
import it.csi.cpass.cpassbe.lib.external.itf.ExternalServiceResolveWrapper;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;


public class GetStrutturaAggregativaXIndiceclassificazioneEstesaService extends BaseService<GetStrutturaAggregativaXIndiceclassificazioneEstesaRequest, GetStrutturaAggregativaXIndiceclassificazioneEstesaResponse> {

	private final ExternalHelperLookup externalHelperLookup;
	private final SettoreDad settoreDad;
	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad    the testataOrdine DAD
	 */
	public GetStrutturaAggregativaXIndiceclassificazioneEstesaService(ConfigurationHelper configurationHelper, ExternalHelperLookup externalHelperLookup, DocumentaleDad documentaleDad,SettoreDad settoreDad) {
		super(configurationHelper);
		this.externalHelperLookup = externalHelperLookup;
		this.settoreDad = settoreDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getIndiceclassificazione(), "indice classificazione");
	}

	@Override
	protected void execute() {
		final Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		final Ente ente = settoreCorrente.getEnte();
		final Long idAOO = getAooBySettore(CpassThreadLocalContainer.SETTORE_UTENTE.get().getId(),ente.getId());
		//chiamo acta
		final ExternalServiceResolveWrapper<DocumentaleHelper> handler = externalHelperLookup.lookup(DocumentaleHelper.class ,  ente.getId());

		ProtocolloOrdine protocolloOrdine = new ProtocolloOrdine();
		final List<ApiError> apiErrors = new ArrayList<>();
		try {
			protocolloOrdine = invokeExternalService(handler, () -> handler.getInstance().getStrutturaAggregativaXIndiceclassificazioneEstesa(handler.getParams(),idAOO,request.getIndiceclassificazione(),settoreCorrente.getCodice() ));
			if((protocolloOrdine.getDescErrore()!=null && !protocolloOrdine.getDescErrore().isBlank())) {
				final ApiError err = new ApiError();
				err.setCode(protocolloOrdine.getCodErrore());
				err.setErrorMessage(protocolloOrdine.getDescErrore());
				apiErrors.add(err);
				//apiErrors.add(CoreError.SYSTEM_ERROR.getError("error", protocolloOrdine.getDescErrore()));
				response.addApiErrors(apiErrors);
			}
		}catch(final Exception e){
			log.error("getProvvedimenti", "Problemi nell'invocazione del servizio esposto da ACTA verificare che il servizio esterno sia attivo ",e );
			apiErrors.add(CoreError.SYSTEM_ERROR.getError("error", e.getMessage()));
			response.addApiErrors(apiErrors);
		}
		response.setProtocolloOrdine(protocolloOrdine);
	}

	private Long getAooBySettore(UUID idsettore,UUID enteId) {
		final Optional<AooActa> aooOpt = settoreDad.getAooByIdSettore(idsettore,enteId);
		if(aooOpt.isPresent()) {
			return (long) aooOpt.get().getAooActaOrigId();
		}
		return null;
	}
}
