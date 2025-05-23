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

import java.util.ArrayList;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PostControlliInvioInFirmaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PostControlliInvioInFirmaResponse;
import it.csi.cpass.cpassbe.ejb.business.be.utility.UtilityVerificheOrdine;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.error.CoreError;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

public class PostControlliInvioInFirmaService extends BaseOrdineService<PostControlliInvioInFirmaRequest, PostControlliInvioInFirmaResponse> {

	private final ExternalHelperLookup externalHelperLookup;
	private final SystemDad systemDad;
	private final RigaOrdineDad rigaOrdineDad;
	private final ImpegnoDad impegnoDad;
	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 */
	public PostControlliInvioInFirmaService (ConfigurationHelper configurationHelper, ExternalHelperLookup externalHelperLookup, TestataOrdineDad testataOrdineDad,RigaOrdineDad rigaOrdineDad, ImpegnoDad impegnoDad, SystemDad systemDad, SettoreDad settoreDad) {
		super(configurationHelper, testataOrdineDad, settoreDad);
		this.externalHelperLookup = externalHelperLookup;
		this.rigaOrdineDad = rigaOrdineDad;
		this.impegnoDad = impegnoDad;
		this.systemDad = systemDad;
	}


	@Override
	protected void checkServiceParams() {
		final TestataOrdine ordine = request.getTestataOrdine();
		checkNotNull(ordine, "ordine");
	}

	@Override
	protected void execute() {
		Boolean ris = Boolean.TRUE;
		final Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		final Ente ente = settoreCorrente.getEnte();
		final List<ApiError> apiErrors = new ArrayList<>();
		final TestataOrdine testataOrdine = testataOrdineDad.getTestataOrdineModel(request.getTestataOrdine().getId(),ente.getId());
		// Verifiche di completezza
		apiErrors.addAll(UtilityVerificheOrdine.checkCompletezza(testataOrdine, rigaOrdineDad, impegnoDad,settoreDad));

		try {
			// Verifiche di validità
			apiErrors.addAll(UtilityVerificheOrdine.checkValidita(testataOrdine, externalHelperLookup, settoreDad, rigaOrdineDad, impegnoDad,ente.getId()));
		}catch(final Exception e) {
			final ApiError errore = CoreError.GENERIC_ERROR.getError("error", "Collegamento al sistema contabile non funzionante contattare l'assistenza");
			generaException( errore );
		}



		// Verifiche di congruenza
		apiErrors.addAll(UtilityVerificheOrdine.checkCongruenza(testataOrdine));
		// Verifiche di consistenza
		apiErrors.addAll(UtilityVerificheOrdine.checkConsistenza(testataOrdine, rigaOrdineDad, impegnoDad));
		// Verifiche sui totali riga
		apiErrors.addAll(UtilityVerificheOrdine.checkTotaliRiga(testataOrdine, systemDad, rigaOrdineDad, ente.getId()));
		if(apiErrors.size()>0) {
			ris = Boolean.FALSE;
		}
		separaMessaggiErrorePerTipo(apiErrors);
		response.setControllo(ris);
		response.setApiErrors(apiErrors);
	}



}
