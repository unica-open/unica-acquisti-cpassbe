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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.evasione;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SubimpegnoEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.PutEvasioneControllaByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.PutEvasioneControllaByIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.utility.UtilityVerificheEvasione;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.error.MsgTypeEnum;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.TestataEvasione;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

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
		final UUID enteId = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte().getId();
		// ANTO vedere riga 447 del file tabc-evasione-component su FE
		final Boolean perAutorizzazione = request.isPerAutorizzazione() != null ? request.isPerAutorizzazione() : false;

		final TestataEvasione testataEvasione = testataEvasioneDad.getTestataEvasioneModel(request.getId());
		response.setTestataEvasione(testataEvasione);

		// TestataEvasione testataEvasione = request.getTestataEvasione(); // l'oggetto non è completo
		final List<ApiError> apiErrors = new ArrayList<>();

		// Verifiche di completezza


		// Verifiche di validità


		// Verifiche di consistenza


		// Verifiche di congruenza
		// CPASS-387 - il controllo è stato posticipato nell'invio in contabilità
		//		if (isThereError(apiErrors, UtilityVerificheEvasione.checkCongruenza(testataEvasione, request.getControllaEvasione(), systemDad))) {
		//			return;
		//		}

		// Verifiche sulla fattura collegata
		if (isThereError(apiErrors, UtilityVerificheEvasione.checkCompletezza(testataEvasione, rigaEvasioneDad, impegnoEvasioneDad, perAutorizzazione)) || isThereError(apiErrors, UtilityVerificheEvasione.checkValidita(testataEvasione, externalHelperLookup, settoreDad, rigaEvasioneDad,impegnoEvasioneDad, subimpegnoEvasioneDad, enteId)) || isThereError(apiErrors, UtilityVerificheEvasione.checkConsistenza(testataEvasione, rigaEvasioneDad, impegnoEvasioneDad)) || isThereError(apiErrors, UtilityVerificheEvasione.checkFatturaCollegata(testataEvasione, externalHelperLookup, systemDad, perAutorizzazione,enteId))) {
			return;
		}

		// Verifiche di congruenza dei fornitori degli impegni con il fornitore della fattura
		if (isThereError(apiErrors, UtilityVerificheEvasione.checkCongruenzaFornitoriImpegniFattura(testataEvasione, request.getControllaEvasione(),rigaEvasioneDad, impegnoEvasioneDad, systemDad, perAutorizzazione, enteId))) {
			return;
		}
	}

	private boolean isThereError(List<ApiError> apiErrors, List<ApiError> apiErrorsToAdd) {
		apiErrors.addAll(apiErrorsToAdd);
		if (apiErrors.size() > 0) {
			separaMessaggiErrorePerTipo(apiErrors);
			if (response.getApiErrors().get(0).getType().equals(MsgTypeEnum.ERROR.getCostante())) {
				return Boolean.TRUE;
			}
		}
		return false;
	}

}
