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

import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.UtilityVerificheOrdine;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PutOrdineControllaByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PutOrdineControllaByIdResponse;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.CpassThreadLocalContainer;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;

public class PutOrdineControllaByIdService extends BaseTestataOrdineService<PutOrdineControllaByIdRequest, PutOrdineControllaByIdResponse> {

	private final ExternalHelperLookup externalHelperLookup;
	private final SettoreDad settoreDad;
	private final SystemDad systemDad;
	private final RigaOrdineDad rigaOrdineDad;
	private final ImpegnoDad impegnoDad;

	/**
	 * Constructor
	 * 
	 * @param configurationHelper
	 * @param testataOrdineDad
	 * @param impegnoDad
	 * @param decodificaDad
	 */
	public PutOrdineControllaByIdService(ConfigurationHelper configurationHelper, ExternalHelperLookup externalHelperLookup, TestataOrdineDad testataOrdineDad,
			RigaOrdineDad rigaOrdineDad, ImpegnoDad impegnoDad, SystemDad systemDad, SettoreDad settoreDad) {
		super(configurationHelper, testataOrdineDad);
		this.externalHelperLookup = externalHelperLookup;
		this.rigaOrdineDad = rigaOrdineDad;
		this.impegnoDad = impegnoDad;
		this.systemDad = systemDad;
		this.settoreDad = settoreDad;
	}

	@Override
	protected void checkServiceParams() {
	}

	@Override
	protected void execute() {
		TestataOrdine testataOrdine = testataOrdineDad.getTestataOrdineModel(request.getId());
		// TestataOrdine testataOrdine = request.getTestataOrdine(); // l'oggetto non è completo
		List<ApiError> apiErrors = new ArrayList<ApiError>();
		
		Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		Ente ente = settoreCorrente.getEnte();

		// Verifiche di completezza
		apiErrors.addAll(UtilityVerificheOrdine.checkCompletezza(testataOrdine, rigaOrdineDad, impegnoDad));

		// Verifiche di validità
		apiErrors.addAll(UtilityVerificheOrdine.checkValidita(testataOrdine, externalHelperLookup, settoreDad, rigaOrdineDad, impegnoDad));

		// Verifiche di congruenza
		apiErrors.addAll(UtilityVerificheOrdine.checkCongruenza(testataOrdine));

		// Verifiche di consistenza
		apiErrors.addAll(UtilityVerificheOrdine.checkConsistenza(testataOrdine, rigaOrdineDad, impegnoDad));

		// Verifiche sui totali riga
		apiErrors.addAll(UtilityVerificheOrdine.checkTotaliRiga(testataOrdine, systemDad, rigaOrdineDad, ente.getId()));

		separaMessaggiErrorePerTipo(apiErrors);
		
		response.setTestataOrdine(testataOrdine);
	}

}
