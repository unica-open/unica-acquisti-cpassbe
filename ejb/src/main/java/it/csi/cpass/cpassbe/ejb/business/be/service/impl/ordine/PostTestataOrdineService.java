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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine;

import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PostTestataOrdineRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PostTestataOrdineResponse;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoOrdineEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Saves an TestataOrdine
 */
public class PostTestataOrdineService extends BaseOrdineService<PostTestataOrdineRequest, PostTestataOrdineResponse> {

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
	public PostTestataOrdineService(ConfigurationHelper configurationHelper, ExternalHelperLookup externalHelperLookup, TestataOrdineDad testataOrdineDad, DecodificaDad decodificaDad,SettoreDad settoreDad) {
		super(configurationHelper, testataOrdineDad, settoreDad);
		this.externalHelperLookup = externalHelperLookup;
		this.decodificaDad = decodificaDad;
	}

	@Override
	protected void checkServiceParams() {
		testataOrdine = request.getTestataOrdine();
		checkNotNull(testataOrdine, "testataOrdine", Boolean.TRUE);
	}

	@Override
	protected void execute() {
		final UUID enteId = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte().getId();
		// Impostazione dello stato a bozza
		testataOrdine.setStato(isEntityPresent(() -> decodificaDad.getStatoOpt(StatoOrdineEnum.BOZZA.getCostante(), ConstantsCPassStato.TipoStatoEnum.ORDINE.getCostante()), "stato"));
		final List<ApiError> apiErrors = checkFornitoreValido(externalHelperLookup,testataOrdine,enteId);
		response.addApiErrors(apiErrors);
		if (apiErrors == null || apiErrors.size() == 0) {
			testataOrdine = testataOrdineDad.insertTestataOrdine(testataOrdine);
		}
		response.setTestataOrdine(testataOrdine);
	}

}
