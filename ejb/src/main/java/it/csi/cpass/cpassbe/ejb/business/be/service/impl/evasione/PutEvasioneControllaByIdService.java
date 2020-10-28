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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.evasione;

import java.util.ArrayList;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SubimpegnoEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.UtilityVerificheEvasione;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.PutEvasioneControllaByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.PutEvasioneControllaByIdResponse;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.error.MsgTypeEnum;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.TestataEvasione;

public class PutEvasioneControllaByIdService extends BaseTestataEvasioneService<PutEvasioneControllaByIdRequest, PutEvasioneControllaByIdResponse> {

	private final ExternalHelperLookup externalHelperLookup;
	private final SettoreDad settoreDad;
	private final SystemDad systemDad;
	private final RigaEvasioneDad rigaEvasioneDad;
	private final ImpegnoEvasioneDad impegnoEvasioneDad;
	private final SubimpegnoEvasioneDad subimpegnoEvasioneDad;

	/**
	 * Constructor
	 * 
	 * @param configurationHelper
	 * @param testataEvasioneDad
	 * @param impegnoDad
	 * @param decodificaDad
	 */
	public PutEvasioneControllaByIdService(ConfigurationHelper configurationHelper, ExternalHelperLookup externalHelperLookup,
			TestataEvasioneDad testataEvasioneDad, RigaEvasioneDad rigaEvasioneDad, ImpegnoEvasioneDad impegnoEvasioneDad,
			SubimpegnoEvasioneDad subimpegnoEvasioneDad, SystemDad systemDad, SettoreDad settoreDad) {
		super(configurationHelper, testataEvasioneDad);
		this.externalHelperLookup = externalHelperLookup;
		this.rigaEvasioneDad = rigaEvasioneDad;
		this.impegnoEvasioneDad = impegnoEvasioneDad;
		this.subimpegnoEvasioneDad = subimpegnoEvasioneDad;
		this.systemDad = systemDad;
		this.settoreDad = settoreDad;
	}

	@Override
	protected void checkServiceParams() {
	}

	@Override
	protected void execute() {
		TestataEvasione testataEvasione = testataEvasioneDad.getTestataEvasioneModel(request.getId());
		response.setTestataEvasione(testataEvasione);

		// TestataEvasione testataEvasione = request.getTestataEvasione(); // l'oggetto non è completo
		List<ApiError> apiErrors = new ArrayList<ApiError>();

		// Verifiche di completezza
		if (isThereError(apiErrors, UtilityVerificheEvasione.checkCompletezza(testataEvasione, rigaEvasioneDad, impegnoEvasioneDad))) {
			return;
		}

		// Verifiche di validità
		if (isThereError(apiErrors, UtilityVerificheEvasione.checkValidita(testataEvasione, externalHelperLookup, settoreDad, rigaEvasioneDad,
				impegnoEvasioneDad, subimpegnoEvasioneDad))) {
			return;
		}

		// Verifiche di consistenza
		if (isThereError(apiErrors, UtilityVerificheEvasione.checkConsistenza(testataEvasione, rigaEvasioneDad, impegnoEvasioneDad))) {
			return;
		}

		// Verifiche di congruenza
		if (isThereError(apiErrors, UtilityVerificheEvasione.checkCongruenza(testataEvasione, request.getControllaEvasione(), systemDad))) {
			return;
		}

		// Verifiche sulla fattura collegata
		if (isThereError(apiErrors, UtilityVerificheEvasione.checkFatturaCollegata(testataEvasione, externalHelperLookup, systemDad))) {
			return;
		}

		// Verifiche di congruenza dei fornitori degli impegni con il fornitore della fattura
		if (isThereError(apiErrors, UtilityVerificheEvasione.checkCongruenzaFornitoriImpegniFattura(testataEvasione, request.getControllaEvasione(),
				rigaEvasioneDad, impegnoEvasioneDad, systemDad))) {
			return;
		}
	}

	private boolean isThereError(List<ApiError> apiErrors, List<ApiError> apiErrorsToAdd) {
		apiErrors.addAll(apiErrorsToAdd);
		if (apiErrors.size() > 0) {
			separaMessaggiErrorePerTipo(apiErrors);
			if (response.getApiErrors().get(0).getType().equals(MsgTypeEnum.ERROR.getCostante())) {
				return true;
			}
		}
		return false;
	}

}
