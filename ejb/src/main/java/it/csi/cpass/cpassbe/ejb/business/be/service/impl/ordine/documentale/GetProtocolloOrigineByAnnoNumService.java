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

import it.csi.cpass.cpassbe.ejb.business.be.dad.DocumentaleDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.documentale.GetProtocolloOrigineByAnnoNumRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.documentale.GetProtocolloOrigineByAnnoNumResponse;
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

/**
 * Retrieves an testataOrdine by its id
 */
public class GetProtocolloOrigineByAnnoNumService extends BaseService<GetProtocolloOrigineByAnnoNumRequest, GetProtocolloOrigineByAnnoNumResponse> {

	private final ExternalHelperLookup externalHelperLookup;
	private final SettoreDad settoreDad;
	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad    the testataOrdine DAD
	 */
	public GetProtocolloOrigineByAnnoNumService(ConfigurationHelper configurationHelper, ExternalHelperLookup externalHelperLookup, DocumentaleDad documentaleDad,SettoreDad settoreDad) {
		super(configurationHelper);
		this.externalHelperLookup = externalHelperLookup;
		this.settoreDad = settoreDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getAnno(), "anno");
		checkNotNull(request.getNumero(), "numero");
	}

	@Override
	protected void execute() {
		//Optional<ProtocolloOrdine> prot = documentaleDad.getProtocolloOrdineByAnnoNumero(request.getAnno(),request.getNumero(),null);
		final Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		final Ente ente = settoreCorrente.getEnte();

		final Optional<AooActa> aoo = settoreDad.getAooByIdSettore(CpassThreadLocalContainer.SETTORE_UTENTE.get().getId(),ente.getId());//getAooBySettore(CpassThreadLocalContainer.SETTORE_UTENTE.get().getId(),ente.getId());
		final Long idAOOe = aoo.isPresent() ? (long) aoo.get().getAooActaOrigId() : null;
		final String aooOrigCode = aoo.isPresent() ? aoo.get().getAooCodice() : null;

		//chiamo acta
		final ExternalServiceResolveWrapper<DocumentaleHelper> handler = externalHelperLookup.lookup(DocumentaleHelper.class ,  ente.getId());

		ProtocolloOrdine protocolloOrdine = new ProtocolloOrdine();
		try {
			protocolloOrdine = invokeExternalService(handler, () -> handler.getInstance().getProtocolloOrigin(handler.getParams(), request.getAnno() , request.getNumero(),idAOOe,settoreCorrente.getCodice()));


			if((protocolloOrdine.getDescErrore()!=null && !protocolloOrdine.getDescErrore().isBlank())) {
				final List<ApiError> apiErrors = new ArrayList<>();
				apiErrors.add(CoreError.SYSTEM_ERROR.getError("error", protocolloOrdine.getDescErrore()));
				response.addApiErrors(apiErrors);
			}else {
				protocolloOrdine.setAooOrigCode(aooOrigCode);
			}
		}catch(final Exception e){
			log.error("getProvvedimenti", "Problemi nell'invocazione del servizio esposto da ACTA verificare che il servizio esterno sia attivo ",e );
		}
		response.setProtocolloOrdine(protocolloOrdine);
		//}
	}


	/*
	private Optional<CpassDAooActa> getAooBySettore(UUID idsettore,UUID enteId) {
		Optional<CpassDAooActa> aooOpt = settoreDad.getAooByIdSettore(idsettore,enteId);
		return aooOpt;
	}
	 */
}
