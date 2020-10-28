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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine;

import java.util.ArrayList;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PostTestataOrdineRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PostTestataOrdineResponse;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.IntegrationConstants;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.FiltroFornitore;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.external.FornitoreHelper;
import it.csi.cpass.cpassbe.lib.external.itf.ExternalServiceResolveWrapper;

/**
 * Saves an TestataOrdine
 */
public class PostTestataOrdineService extends BaseTestataOrdineService<PostTestataOrdineRequest, PostTestataOrdineResponse> {

	private final ExternalHelperLookup externalHelperLookup;
	private final DecodificaDad decodificaDad;
	private TestataOrdine testataOrdine;

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad    the testataOrdine DAD
	 * @param decodificaDad       the decodifica DAD
	 */
	public PostTestataOrdineService(ConfigurationHelper configurationHelper, ExternalHelperLookup externalHelperLookup, TestataOrdineDad testataOrdineDad, DecodificaDad decodificaDad) {
		super(configurationHelper, testataOrdineDad);
		this.externalHelperLookup = externalHelperLookup;
		this.decodificaDad = decodificaDad;
	}

	@Override
	protected void checkServiceParams() {
		testataOrdine = request.getTestataOrdine();
		checkNotNull(testataOrdine, "testataOrdine", true);
	}

	@Override
	protected void execute() {
		// testataOrdine.setUfficio(isEntityPresent(() -> decodificaDad.getUfficio(testataOrdine.getUfficio().getId()), "ufficio"));

		// Impostazione dello stato a bozza
		testataOrdine.setStato(isEntityPresent(
				() -> decodificaDad.getStato(ConstantsCPassStato.StatoEnum.BOZZA.getCostante(), ConstantsCPassStato.TipoEnum.ORDINE.getCostante()), "stato"));

		List<ApiError> apiErrors = new ArrayList<ApiError>();
		
		// Fornitore corrisponda ad un fornitore valido
		FiltroFornitore filtroFornitore = new FiltroFornitore();
		filtroFornitore.setFornitore(testataOrdine.getFornitore());
		filtroFornitore.setStatoFornitore(IntegrationConstants.SOGGETTO_STATO_VALIDO);
		
		ExternalServiceResolveWrapper<FornitoreHelper> handlerFornitore = externalHelperLookup.lookup(FornitoreHelper.class);
		List<Fornitore> fornitori = invokeExternalService(handlerFornitore,
				() -> handlerFornitore.getInstance().getFornitori(handlerFornitore.getParams(), filtroFornitore));
		if (fornitori == null || fornitori.size() == 0) {
			apiErrors.add(MsgCpassOrd.ORDORDE0004.getError());
		}

		// • il provvedimento esista TODO
		if (false) {
			apiErrors.add(MsgCpassOrd.ORDORDE0005.getError());
		}
		response.addApiErrors(apiErrors);

		if (apiErrors == null || apiErrors.size() == 0) {
			testataOrdine = testataOrdineDad.saveTestataOrdine(testataOrdine);
		}
		response.setTestataOrdine(testataOrdine);
	}

}
