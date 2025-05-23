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

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DocumentaleDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.StampeDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.BaseDocumentaleService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.documentale.GetProtocollaOrdineRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.documentale.GetProtocollaOrdineResponse;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.error.CoreError;
import it.csi.cpass.cpassbe.lib.dto.ord.ProtocolloOrdine;
import it.csi.cpass.cpassbe.lib.external.DocumentaleHelper;
import it.csi.cpass.cpassbe.lib.external.itf.ExternalServiceResolveWrapper;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

public class GetProtocollaOrdineService extends BaseDocumentaleService<GetProtocollaOrdineRequest, GetProtocollaOrdineResponse> {

	private final ExternalHelperLookup externalHelperLookup;

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad    the testataOrdine DAD
	 * @param decodificaDad       the decodifica DAD
	 */
	public GetProtocollaOrdineService(ConfigurationHelper configurationHelper, ExternalHelperLookup externalHelperLookup,TestataOrdineDad testataOrdineDad, DocumentaleDad documentaleDad,CommonDad commonDad,StampeDad stampeDad,SettoreDad settoreDad,SystemDad systemDad ) {
		super(configurationHelper, testataOrdineDad, documentaleDad, settoreDad,commonDad,stampeDad,systemDad);
		this.externalHelperLookup 	= externalHelperLookup;
	}


	@Override
	protected void checkServiceParams() {
		checkModel(request.getTestataOrdine(), "testataOrdine");
		checkModel(request.getProtocolloOrdine(), "protocolloOrdine");
	}

	@Override
	protected void execute() {
		final Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		final Ente ente = settoreCorrente.getEnte();

		final ExternalServiceResolveWrapper<DocumentaleHelper> handler = externalHelperLookup.lookup(DocumentaleHelper.class ,  ente.getId());
		final Long idAOO = getAooBySettore(CpassThreadLocalContainer.SETTORE_UTENTE.get().getId(),ente.getId());
		final ProtocolloOrdine protocolloResponse = invokeExternalService(handler, () -> handler.getInstance().protocollaOrdine(handler.getParams(),  idAOO, request.getTestataOrdine(), request.getProtocolloOrdine(),settoreCorrente.getCodice()));
		if((protocolloResponse.getDescErrore()!=null && protocolloResponse.getDescErrore().equals(""))) {
			final List<ApiError> apiErrors = new ArrayList<>();
			apiErrors.add(CoreError.SYSTEM_ERROR.getError("error", protocolloResponse.getDescErrore()));
			response.addApiErrors(apiErrors);
		}
		//aggiorno il protocollo
		documentaleDad.saveProtocollo(protocolloResponse);
		response.setProtocolloOrdine(protocolloResponse);
	}

}
