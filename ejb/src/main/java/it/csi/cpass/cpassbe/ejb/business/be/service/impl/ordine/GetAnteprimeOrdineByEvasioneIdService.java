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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.GetAnteprimeOrdineByEvasioneIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.GetAnteprimeOrdineByEvasioneIdResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.AnteprimaOrdineSuEvasione;

public class GetAnteprimeOrdineByEvasioneIdService extends BaseOrdineService<GetAnteprimeOrdineByEvasioneIdRequest, GetAnteprimeOrdineByEvasioneIdResponse> {

	RigaOrdineDad rigaOrdineDad;


	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 * @param testataEvasioneDad  the testataEvasione DAD
	 */
	public GetAnteprimeOrdineByEvasioneIdService(ConfigurationHelper configurationHelper, TestataOrdineDad testataOrdineDad, RigaOrdineDad rigaOrdineDad,SettoreDad settoreDad) {
		super(configurationHelper, testataOrdineDad, settoreDad);
		this.rigaOrdineDad = rigaOrdineDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getId(), "id");
	}

	@Override
	protected void execute() {
		// TestataEvasione testataEvasione = testataEvasioneDad.getTestataEvasione(request.getId()).orElseThrow(() -> new
		// NotFoundException("testataEvasione"));
		final List<TestataOrdine> listaTestataOrdine = testataOrdineDad.findTestateOrdineByEvasioneId(request.getId());
		final List<AnteprimaOrdineSuEvasione> listaAnteprime = new ArrayList<>();

		for(final TestataOrdine ordine : listaTestataOrdine) {
			final AnteprimaOrdineSuEvasione anteprima = new AnteprimaOrdineSuEvasione();

			anteprima.setId(ordine.getId());
			anteprima.setAnno(ordine.getAnno());
			anteprima.setNumero(ordine.getNumero());
			anteprima.setTotaleConIva(ordine.getTotaleConIva());
			anteprima.setDataAutorizzazione(ordine.getDataAutorizzazione());
			anteprima.setDescrizione(ordine.getDescrizione());
			anteprima.setIdEvasione(request.getId());

			anteprima.setTotaleSuEvasione(getImportoSuEvasioneByOrdine(ordine, request.getId()));

			listaAnteprime.add(anteprima);
		}

		response.setListaTestataOrdine(listaAnteprime);
	}

	private BigDecimal getImportoSuEvasioneByOrdine(TestataOrdine ordine, UUID idEvasione) {

		BigDecimal result = BigDecimal.ZERO;

		final List<RigaOrdine> righeOrdine = testataOrdineDad.findRigheByOrdineAndEvasioneId(ordine.getId(), request.getId());

		for(final RigaOrdine riga : righeOrdine) {
			result = result.add(riga.getImportoTotale());
		}

		return result;
	}

}
